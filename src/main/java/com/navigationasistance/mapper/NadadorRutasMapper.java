package com.navigationasistance.mapper;

import com.navigationasistance.modelo.NadadorRutas;

import java.util.HashMap;
import java.util.Map;

public class NadadorRutasMapper {

    public static Map<String, Object> toMap(NadadorRutas nr) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", nr.getId());
        map.put("ruta_id", nr.getRuta().getId());
        map.put("usuario_id", nr.getUsuarioId());
        return map;
    }

    public static NadadorRutas fromMap(Map<String, Object> map) {
        NadadorRutas nr = new NadadorRutas();
        if (map.get("id") != null) {
            nr.setId((Integer) map.get("id"));
        }
        // El campo ruta debe ser seteado aparte (por lógica de repositorio)
        nr.setUsuarioId((String) map.get("usuario_id"));
        return nr;
    }
}
