package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modelo.RutasPuntos;

import java.util.HashMap;
import java.util.Map;

public class RutasPuntosMapper {

    public static Map<String, Object> toMap(RutasPuntos p) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", p.getId());
        map.put("ruta_id", p.getRuta().getId());
        map.put("secuencia", p.getSecuencia());
        map.put("latitud", p.getLatitud());
        map.put("longitud", p.getLongitud());
        return map;
    }

    public static RutasPuntos fromMap(Map<String, Object> map, Rutas ruta) {
        RutasPuntos p = new RutasPuntos();
        if (map.get("id") != null) {
            p.setId((Integer) map.get("id"));
        }
        p.setRuta(ruta);
        p.setSecuencia((Integer) map.get("secuencia"));
        p.setLatitud((Double) map.get("latitud"));
        p.setLongitud((Double) map.get("longitud"));
        return p;
    }
}
