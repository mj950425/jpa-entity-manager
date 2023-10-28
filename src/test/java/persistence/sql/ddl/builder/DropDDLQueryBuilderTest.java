package persistence.sql.ddl.builder;

import fixtures.TestEntityFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import persistence.entity.attribute.EntityAttribute;
import persistence.sql.ddl.converter.SqlConverter;
import persistence.sql.infra.H2SqlConverter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static persistence.sql.common.DDLType.DROP;

@Nested
@DisplayName("DropDDLQueryBuilder 클래스의")
public class DropDDLQueryBuilderTest {
    private final SqlConverter sqlConverter = new H2SqlConverter();

    @Nested
    @DisplayName("prepareStatement 메소드는")
    class prepareStatement {
        @Nested
        @DisplayName("유효한 엔티티 정보가 주어지면")
        class withValidEntity {
            @Test
            @DisplayName("DROP DDL을 리턴한다.")
            void returnDDL() {
                EntityAttribute entityAttribute = EntityAttribute.of(TestEntityFixtures.SampleTwoWithValidAnnotation.class);

                String dropDDL = DDLQueryBuilderFactory.createQueryBuilder(DROP)
                        .prepareStatement(entityAttribute, sqlConverter);

                assertThat(dropDDL).isEqualTo("DROP TABLE two;");
            }
        }
    }
}
