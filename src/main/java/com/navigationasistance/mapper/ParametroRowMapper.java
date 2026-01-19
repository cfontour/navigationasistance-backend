package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Parametro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParametroRowMapper implements RowMapper<Parametro> {
    @Override
    public Parametro mapRow(ResultSet rs, int rowNum) throws SQLException {
        Parametro parametro = new Parametro();
        parametro.setClave(rs.getString("clave"));
        parametro.setValor(rs.getString("valor"));

        return parametro;
    }
}
