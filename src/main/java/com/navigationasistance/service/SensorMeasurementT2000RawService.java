package com.navigationasistance.service;

import com.navigationasistance.interfaces.SensorMeasurementT2000RawInterface;
import com.navigationasistance.modelo.SensorMeasurement;
import com.navigationasistance.modelo.SensorMeasurementT2000Raw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorMeasurementT2000RawService implements SensorMeasurementT2000RawInterface {

    @Autowired
    private SensorMeasurementT2000RawInterface dao;

    @Override
    public List<SensorMeasurementT2000Raw> listar() {
        return dao.listar();
    }

    @Override
    public SensorMeasurementT2000Raw listarClave(String devEui) {
        return dao.listarClave(devEui);
    }

    @Override
    public int add(SensorMeasurementT2000Raw s) {
        return dao.add(s);
    }

    @Override
    public int delAll() {
        return dao.delAll();
    }
}