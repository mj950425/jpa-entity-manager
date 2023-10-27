package persistence.entity.attribute;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import persistence.entity.attribute.id.IdAttribute;
import persistence.entity.attribute.id.LongTypeIdAttribute;
import persistence.entity.attribute.id.StringTypeIdAttribute;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AttributeParser {

    private AttributeParser() {
    }

    public static IdAttribute parseIdAttribute(Field field) {
        if (field.getType().equals(String.class)) {
            return StringTypeIdAttribute.of(field);
        }
        if (field.getType().equals(Long.class)) {
            return LongTypeIdAttribute.of(field);
        }
        if (field.getType().equals(Integer.class)) {
            return LongTypeIdAttribute.of(field);
        }
        throw new IllegalStateException(String.format("[%s] 알수없는 필드 타입입니다.", field.getType()));
    }

    public static IdAttribute parseIdAttribute(Class<?> clazz) {
        Field id = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("[%s] 엔티티에 @Id가 없습니다", clazz.getSimpleName())));
        return parseIdAttribute(id);
    }

    public static List<GeneralAttribute> parseGeneralAttributes(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class)
                        && !field.isAnnotationPresent(Id.class))
                .map(AttributeParser::parseGeneralAttribute)
                .collect(Collectors.toList());
    }

    public static GeneralAttribute parseGeneralAttribute(
            Field field
    ) {
        if (field.getType().equals(String.class)) {
            return StringTypeGeneralAttribute.of(field);
        }
        if (field.getType().equals(Integer.class)) {
            return IntegerTypeGeneralAttribute.of(field);
        }
        if (field.getType().equals(Long.class)) {
            return LongTypeGeneralAttribute.of(field);
        }
        throw new IllegalStateException(String.format("[%s] 필드의 타입을 알 수 없습니다.", field.getName()));
    }
}
