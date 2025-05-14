package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Zona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZonaRowMapper implements RowMapper<Zona> {
    @Override
    public Zona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Zona zona = new Zona();
        zona.setZona(rs.getString("zona"));
        zona.setLato(rs.getString("lato"));
        zona.setLngo(rs.getString("lngo"));
        zona.setLatd(rs.getString("latd"));
        zona.setLngd(rs.getString("lngd"));
        zona.setNomo(rs.getString("nomo"));
        zona.setNomd(rs.getString("nomd"));
        return zona;
    }
}
