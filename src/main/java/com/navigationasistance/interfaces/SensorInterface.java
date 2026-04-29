package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Sensor;

import java.util.List;

public interface SensorInterface {

    public List<Sensor> listar();

    public Sensor listarId(String sensorId);

    public int addSensor(Sensor s);

    public int updSensor(Sensor s);

    public int delSensor(String sensorId);
}