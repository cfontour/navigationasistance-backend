package com.navigationasistance.service;

import com.navigationasistance.interfaces.NadadorposicionInterface;
import com.navigationasistance.modelo.NadadorPosicion;
import com.navigationasistance.modeloDAO.NadadorposicionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NadadorposicionService implements NadadorposicionInterface {

    @Autowired
    NadadorposicionDAO dao;

    @Override
    public List<NadadorPosicion> listar() {
        return dao.listar();
    }

    @Override
    public NadadorPosicion getNadadorPosicion(String id) { return dao.getNadadorPosicion(id); }

    //@Override
    //public int addNadador(NadadorPosicion n) {
    //    return dao.addNadador(n);
    //}

    public int upsertNadador(NadadorPosicion n) {
        return dao.upsertNadador(n); // llama al método en el DAO (interface + implementación)
    }


    @Override
    public int updNadador(NadadorPosicion n) {
        return dao.updNadador(n);
    }

    @Override
    public int delNadador(String id) {
        return dao.delNadador(id);
    }

}