package com.navigationasistance.controler;

import com.navigationasistance.modelo.UsuariocaPuntosControl;
import com.navigationasistance.service.UsuariocaPuntosControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuariocapuntoscontrol")
public class UsuariocaPuntosControlControler {

    @Autowired
    private UsuariocaPuntosControlService service;

    @PostMapping("/agregar")
    public int agregar(@RequestBody UsuariocaPuntosControl u) {
        System.out.println("🟡 Recibido en controller:");
        System.out.println("nadadorrutaId: " + u.getNadadorrutaId());
        System.out.println("puntoControl: " + u.getPuntoControl());
        System.out.println("fechaHora: " + u.getFechaHora());
        // CORREGIDO: Manejar posible null antes de llamar a toString()
        System.out.println("Ruta_id: " + (u.getRutaId() != null ? u.getRutaId().toString() : "null"));
        return service.save(u);
    }

    @GetMapping("/listar")
    public List<UsuariocaPuntosControl> listar() {
        return service.listar();
    }

    @GetMapping("/listarGrupo/{idGrupo}")
    public List<UsuariocaPuntosControl> listarGrupo(@PathVariable("idGrupo") String idGrupo) {
        return service.listarGrupo(idGrupo);
    }

    @GetMapping("/listarPorNadadorrutaId/{id}")
    public List<UsuariocaPuntosControl> listarPorNadadorrutaId(@PathVariable("id") String id) {
        return service.listarPorNadadorrutaId(id);
    }

    @GetMapping("/listarPorRutaIdnadadorRutaId/{rutaId}/{nadadorrutaId}") // <-- CAMBIO AQUÍ
    public List<UsuariocaPuntosControl> listarPorRutaIdnadadorRutaId(
            @PathVariable("rutaId") Integer rutaId,
            @PathVariable("nadadorrutaId") String nadadorrutaId) { // <-- CAMBIO AQUÍ
        return service.listarPorRutaIdnadadorRutaId(rutaId, nadadorrutaId);
    }

    @GetMapping("/listarPorRutaId/{rutaId}")
    public List<UsuariocaPuntosControl> listarPorRutaId(@PathVariable("rutaId") Integer rutaId) {
        return service.listarPorRutaId(rutaId);
    }

    @DeleteMapping("/eliminarUsuariocaPuntos/{nadadorruta_id}")
    public int eliminarUsuariocaPuntos(@PathVariable("nadadorruta_id") String nadadorruta_id) {
        return service.deleteUsuarioRutasPuntos(nadadorruta_id);
    }

}
