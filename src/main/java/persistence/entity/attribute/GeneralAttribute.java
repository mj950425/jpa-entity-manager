package persistence.entity.attribute;

import persistence.sql.ddl.converter.SqlConverter;

abstract public class GeneralAttribute {
    abstract public String prepareDDL(SqlConverter sqlConverter);

    abstract public String getColumnName();

    abstract public String getFieldName();
}
