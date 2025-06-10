package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Respaldo;
import com.navigationasistance.modelo.Rutas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutasInterface extends JpaRepository<Rutas, Integer> {
    List<Rutas> listar();
    Rutas listarId(Integer id);
    int add(Rutas r);
    int upd(Rutas r);
    int del(Integer id);
}
