package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.ZonaInterface;
import com.navigationasistance.mapper.ZonaRowMapper;
import com.navigationasistance.modelo.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZonaDAO implements ZonaInterface{

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Zona> listar() {
        String sql = "SELECT * FROM zonas";
        List<Zona> list = template.query(sql, new ZonaRowMapper());
        return list;
    }

    @Override
    public Zona listarZona(String zona) {
        String sql = "SELECT * FROM zonas WHERE zona = ?";
        return template.queryForObject(sql, new Object[]{zona}, new ZonaRowMapper());
    }


    @Override
    public Zona getZonaByZona(String zona) {
        String sql = "SELECT * FROM zona WHERE zona = ?";
        return template.queryForObject(sql, new Object[]{zona}, new ZonaRowMapper());
    }


    @Override
    public int addZona(Zona z) {
        String sql = "insert into zonas(zona, lato, lngo, latd, lngd, nomo, nomd) values(?,?,?,?,?,?,?)";
        return template.update(sql, z.getZona(), z.getLato(), z.getLngo(), z.getLatd(), z.getLngd(), z.getNomo(), z.getNomd());
    }

    @Override
    public int updZona(Zona z) {
        String sql="update zonas set lato=?, lngo=?, latd=?, lngd=?, nomo=?, nomd=? where zona=?";
        return template.update(sql, z.getLato(), z.getLngo(), z.getLatd(), z.getLngd(), z.getNomo(), z.getNomd(), z.getZona());
    }

    @Override
    public int delZona(String zona) {
        String sql="delete from zonas where zona=?";
        return template.update(sql,zona);
    }

}
