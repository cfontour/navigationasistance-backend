package com.navigationasistance.service;

import com.navigationasistance.interfaces.SensorInterface;
import com.navigationasistance.modelo.Sensor;
import com.navigationasistance.modeloDAO.SensorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService implements SensorInterface {

    @Autowired
    SensorDAO dao;

    @Override
    public List<Sensor> listar() {
        return dao.listar();
    }

    @Override
    public Sensor listarId(String sensorId) {
        return dao.listarId(sensorId);
    }

    @Override
    public int addSensor(Sensor s) {
        return dao.addSensor(s);
    }

    @Override
    public int updSensor(Sensor s) {
        return dao.updSensor(s);
    }

    @Override
    public int delSensor(String sensorId) {
        return dao.delSensor(sensorId);
    }
}