package com.navigationasistance.service;

import com.navigationasistance.interfaces.SensorMeasurementInterface;
import com.navigationasistance.modelo.SensorMeasurement;
import com.navigationasistance.modeloDAO.SensorMeasurementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorMeasurementService implements SensorMeasurementInterface {

    @Autowired
    SensorMeasurementDAO dao;

    @Override
    public List<SensorMeasurement> listar() {
        return dao.listar();
    }

    @Override
    public List<SensorMeasurement> listarClave(String devEui) {
        return dao.listarClave(devEui);
    }

    public List<SensorMeasurement> listarFlujo() {
        return dao.listarFlujo();
    }

    @Override
    public SensorMeasurement listarUltimoPorDevEuiYMeasurementName(String devEui, String measurementName) {
        return dao.listarUltimoPorDevEuiYMeasurementName(devEui, measurementName);
    }

    @Override
    public int add(SensorMeasurement obj) {
        return dao.add(obj);
    }

    @Override
    public int upd(SensorMeasurement obj) {
        return dao.upd(obj);
    }

    @Override
    public int del(String devEui) {
        return dao.del(devEui);
    }
}