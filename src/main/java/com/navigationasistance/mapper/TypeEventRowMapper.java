package com.navigationasistance.mapper;

import com.navigationasistance.modelo.TypeEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeEventRowMapper implements RowMapper<TypeEvent>{
    @Override
    public TypeEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
        TypeEvent typeEvent = new TypeEvent();
        typeEvent.setId(rs.getBigDecimal("id"));
        typeEvent.setType_nombre(rs.getString("type_nombre"));
        typeEvent.setType_importancia(rs.getBigDecimal("type_importancia"));

        return typeEvent;
    }

}
