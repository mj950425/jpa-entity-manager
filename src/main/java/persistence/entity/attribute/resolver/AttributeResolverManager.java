package persistence.entity.attribute.resolver;

import java.util.Arrays;
import java.util.List;

public class AttributeResolverManager {
    public static final List<IdAttributeResolver> ID_ATTRIBUTE_RESOLVERS = Arrays.asList(
            new StringTypeIdAttributeResolver(),
            new LongTypeIdAttributeResolver()
    );

    public static final List<GeneralAttributeResolver> GENERAL_ATTRIBUTE_RESOLVERS = Arrays.asList(
            new StringTypeGeneralAttributeResolver(),
            new LongTypeGeneralAttributeResolver(),
            new IntegerTypeGeneralAttributeResolver()
    );
}