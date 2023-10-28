package persistence.entity.attribute.id;

import fixtures.TestEntityFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import persistence.sql.infra.H2SqlConverter;

import java.lang.reflect.Field;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Nested
@DisplayName("StringTypeIdAttribute 클래스의")
class StringTypeIdAttributeTest {
    TestEntityFixtures.EntityWithStringId sample
            = new TestEntityFixtures.EntityWithStringId("test id", "test_nick_name", 29);

    @Nested
    @DisplayName("of 메소드는")
    class of {

        @Test
        @DisplayName("StringTypeIdAttribute를 반환한다.")
        void notThrow() throws NoSuchFieldException {
            Field field = sample.getClass().getDeclaredField("id");
            StringTypeIdAttribute stringTypeIdAttribute = new StringTypeIdAttribute(field);
            Assertions.assertAll(
                    () -> assertThat(stringTypeIdAttribute.getColumnName()).isEqualTo("id"),
                    () -> assertThat(stringTypeIdAttribute.getFieldName()).isEqualTo("id"),
                    () -> assertThat(stringTypeIdAttribute.getGenerationType()).isNull()
            );
        }
    }

    @Nested
    @DisplayName("prepareDDL 메소드는")
    class prepareDDL {

        @Test
        @DisplayName("DDL을 반환한다")
        void notThrow() throws NoSuchFieldException {
            Field field = sample.getClass().getDeclaredField("id");

            StringTypeIdAttribute stringTypeIdAttribute = new StringTypeIdAttribute(field);
            String ddl = stringTypeIdAttribute.prepareDDL(new H2SqlConverter());

            assertThat(ddl).isEqualTo("id VARCHAR(255)");
        }
    }
}
