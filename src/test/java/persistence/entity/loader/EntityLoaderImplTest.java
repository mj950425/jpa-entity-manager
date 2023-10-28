package persistence.entity.loader;

import fixtures.TestEntityFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import persistence.DatabaseTest;
import persistence.entity.persister.SimpleEntityPersister;
import persistence.sql.infra.H2SqlConverter;

import static org.assertj.core.api.Assertions.assertThat;


@Nested
@DisplayName("EntityLoader 클래스의")
public class EntityLoaderImplTest extends DatabaseTest {

    private final TestEntityFixtures.SampleOneWithValidAnnotation sample =
            new TestEntityFixtures.SampleOneWithValidAnnotation("민준", 29);

    @Nested
    @DisplayName("load 메소드는")
    class findById {
        @Nested
        @DisplayName("클래스정보와 아이디가 주어지면")
        public class withInstance {
            @Test
            @DisplayName("객체를 찾아온다.")
            void returnData() {
                setUpFixtureTable(TestEntityFixtures.SampleOneWithValidAnnotation.class, new H2SqlConverter());
                TestEntityFixtures.SampleOneWithValidAnnotation sample
                        = new TestEntityFixtures.SampleOneWithValidAnnotation("민준", 29);

                EntityLoader entityLoader = new EntityLoaderImpl(jdbcTemplate);
                SimpleEntityPersister simpleEntityPersister = new SimpleEntityPersister(jdbcTemplate, entityLoader);

                TestEntityFixtures.SampleOneWithValidAnnotation inserted = simpleEntityPersister.insert(sample);

                EntityLoaderImpl entityLoaderImpl = new EntityLoaderImpl(jdbcTemplate);

                TestEntityFixtures.SampleOneWithValidAnnotation retrieved =
                        entityLoaderImpl.load(TestEntityFixtures.SampleOneWithValidAnnotation.class, inserted.getId().toString());

                assertThat(retrieved.toString()).isEqualTo("SampleOneWithValidAnnotation{id=1, name='민준', age=29}");
            }
        }
    }
}
