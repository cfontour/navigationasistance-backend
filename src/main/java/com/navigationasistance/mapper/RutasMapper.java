package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Rutas;

import java.util.HashMap;
import java.util.Map;

public class RutasMapper {

    public static Map<String, Object> toMap(Rutas r) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", r.getId());
        map.put("color", r.getColor());
        return map;
    }

    public static Rutas fromMap(Map<String, Object> map) {
        Rutas r = new Rutas();
        if (map.get("id") != null) {
            r.setId((Integer) map.get("id"));
        }
        r.setColor((String) map.get("color"));
        return r;
    }
}
