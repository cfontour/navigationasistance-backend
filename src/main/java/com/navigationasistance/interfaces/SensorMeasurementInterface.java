package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.SensorMeasurement;

public interface SensorMeasurementInterface {

    List<SensorMeasurement> listar();

    SensorMeasurement listarClave(String devEui);

    int add(SensorMeasurement obj);

    int upd(SensorMeasurement obj);

    int del(String devEui);
}