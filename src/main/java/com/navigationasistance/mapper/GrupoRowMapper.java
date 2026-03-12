package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Grupo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupoRowMapper implements RowMapper<Grupo> {
    @Override
    public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Grupo grupo = new Grupo();
        grupo.setGrupoid(rs.getString("grupoid"));
        grupo.setGruponombre(rs.getString("gruponombre"));
        grupo.setGrupodescripcion(rs.getString("grupodescripcion"));

        return grupo;
    }
}
