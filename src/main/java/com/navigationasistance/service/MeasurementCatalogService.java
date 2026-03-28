package com.navigationasistance.service;

import com.navigationasistance.interfaces.MeasurementCatalogInterface;
import com.navigationasistance.modelo.MeasurementCatalog;
import com.navigationasistance.modeloDAO.MeasurementCatalogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementCatalogService implements MeasurementCatalogInterface {

    @Autowired
    MeasurementCatalogDAO dao;

    @Override
    public List<MeasurementCatalog> listar() {
        return dao.listar();
    }

    @Override
    public MeasurementCatalog listarClave(String measurementName) {
        return dao.listarClave(measurementName);
    }

    @Override
    public int add(MeasurementCatalog obj) {
        return dao.add(obj);
    }

    @Override
    public int upd(MeasurementCatalog obj) {
        return dao.upd(obj);
    }

    @Override
    public int del(String measurementName) {
        return dao.del(measurementName);
    }
}