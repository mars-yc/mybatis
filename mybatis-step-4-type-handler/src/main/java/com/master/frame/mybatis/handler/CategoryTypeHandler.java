package com.master.frame.mybatis.handler;

import com.master.frame.mybatis.domain.Category;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryTypeHandler extends BaseTypeHandler<Category> {

    private final Map<String, Category> keyCategoriesMap = new HashMap<>();

    public CategoryTypeHandler() {
        for(Category category : Category.values()) {
            keyCategoriesMap.put(category.getDescription(), category);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Category parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getDescription());
    }

    @Override
    public Category getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if(rs.wasNull())
            return null;
        return Category.getCategory(rs.getString(columnName));
    }

    @Override
    public Category getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if(rs.wasNull())
            return null;
        return Category.getCategory(rs.getString(columnIndex));
    }

    @Override
    public Category getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if(cs.wasNull())
            return null;
        return Category.getCategory(cs.getString(columnIndex));
    }
}