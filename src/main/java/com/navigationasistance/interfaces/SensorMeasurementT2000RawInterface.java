package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.SensorMeasurement;
import com.navigationasistance.modelo.SensorMeasurementT2000Raw;

import java.util.List;

public interface SensorMeasurementT2000RawInterface {

    public List<SensorMeasurementT2000Raw> listar();

    SensorMeasurementT2000Raw listarClave(String devEui);

    public int add(SensorMeasurementT2000Raw s);

    public int delAll();
}