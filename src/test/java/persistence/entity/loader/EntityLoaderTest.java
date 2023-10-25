package persistence.entity.loader;

import jdbc.JdbcTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import persistence.DatabaseTest;
import persistence.entity.persister.EntityPersister;
import persistence.fixture.TestEntityFixture;
import persistence.sql.infra.H2SqlConverter;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


@Nested
@DisplayName("EntityLoader 클래스의")
public class EntityLoaderTest extends DatabaseTest {

    private final TestEntityFixture.SampleOneWithValidAnnotation sample =
            new TestEntityFixture.SampleOneWithValidAnnotation("민준", 29);

    @Nested
    @DisplayName("load 메소드는")
    class findById {
        @Nested
        @DisplayName("클래스정보와 아이디가 주어지면")
        public class withInstance {
            @Test
            @DisplayName("객체를 찾아온다.")
            void returnData() throws SQLException {
                setUpFixtureTable(TestEntityFixture.SampleOneWithValidAnnotation.class, new H2SqlConverter());
                TestEntityFixture.SampleOneWithValidAnnotation sample
                        = new TestEntityFixture.SampleOneWithValidAnnotation("민준", 29);
                JdbcTemplate jdbcTemplate = new JdbcTemplate(server.getConnection());
                EntityPersister entityPersister = new EntityPersister(jdbcTemplate);
                TestEntityFixture.SampleOneWithValidAnnotation inserted = entityPersister.insert(sample);


                EntityLoader entityLoader = new EntityLoader(jdbcTemplate);

                TestEntityFixture.SampleOneWithValidAnnotation retrieved =
                        entityLoader.load(TestEntityFixture.SampleOneWithValidAnnotation.class, inserted.getId().toString());

                assertThat(retrieved.toString()).isEqualTo("SampleOneWithValidAnnotation{id=1, name='민준', age=29}");
            }
        }
    }
}
