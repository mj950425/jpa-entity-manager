package persistence.entity.attribute.resolver;

import persistence.entity.attribute.GeneralAttribute;
import persistence.entity.attribute.IntegerTypeGeneralAttribute;

import java.lang.reflect.Field;

public class IntegerTypeGeneralAttributeResolver implements GeneralAttributeResolver {
    @Override
    public boolean support(Class<?> clazz) {
        return clazz == Integer.class;
    }

    @Override
    public GeneralAttribute resolve(Field field) {
        return IntegerTypeGeneralAttribute.of(field);
    }
}