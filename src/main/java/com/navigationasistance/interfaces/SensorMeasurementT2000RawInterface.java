package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.SensorMeasurementT2000Raw;

import java.util.List;

public interface SensorMeasurementT2000RawInterface {

    public List<SensorMeasurementT2000Raw> listar();

    public int add(SensorMeasurementT2000Raw s);

    public int delAll();
}