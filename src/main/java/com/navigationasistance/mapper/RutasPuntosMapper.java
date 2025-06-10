package com.navigationasistance.mapper;

import com.navigationasistance.modelo.RutasPuntos;
import com.navigationasistance.modeloDAO.RutasPuntosDAO;
import org.springframework.stereotype.Component;

@Component
public class RutasPuntosMapper {

    public RutasPuntosDAO toDAO(RutasPuntos rp) {
        RutasPuntosDAO dao = new RutasPuntosDAO();
        dao.setId(rp.getId());
        dao.setRutaId(rp.getRutaId()); // ⚠️ Ojo: sacamos el id desde el objeto Ruta
        dao.setSecuencia(rp.getSecuencia());
        dao.setLatitud(rp.getLatitud());
        dao.setLongitud(rp.getLongitud());
        return dao;
    }

    public RutasPuntos toModel(RutasPuntosDAO dao) {
        RutasPuntos rp = new RutasPuntos();
        rp.setId(dao.getId());
        rp.setSecuencia(dao.getSecuencia());
        rp.setLatitud(dao.getLatitud());
        rp.setLongitud(dao.getLongitud());

        // Creamos una instancia mínima de Rutas solo con el id
        com.navigationasistance.modelo.Rutas ruta = new com.navigationasistance.modelo.Rutas();
        ruta.setId(dao.getRutaId());
        rp.setRutaId(ruta.getId());

        return rp;
    }
}
