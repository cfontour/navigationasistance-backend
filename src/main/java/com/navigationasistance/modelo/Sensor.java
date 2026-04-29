package com.navigationasistance.modelo;

public class Sensor {

    private String sensorId;
    private String sensorNombre;
    private String sensorDescripcion;
    private String sensorlat;
    private String sensorlng;

    public Sensor() {
    }

    public Sensor(String sensorId, String sensorNombre, String sensorDescripcion, String sensorlat, String sensorlng) {
        this.sensorId = sensorId;
        this.sensorNombre = sensorNombre;
        this.sensorDescripcion = sensorDescripcion;
        this.sensorlat = sensorlat;
        this.sensorlng = sensorlng;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorNombre() {
        return sensorNombre;
    }

    public void setSensorNombre(String sensorNombre) {
        this.sensorNombre = sensorNombre;
    }

    public String getSensorDescripcion() {
        return sensorDescripcion;
    }

    public void setSensorDescripcion(String sensorDescripcion) {
        this.sensorDescripcion = sensorDescripcion;
    }

    public String getSensorlat() {
        return sensorlat;
    }

    public void setSensorlat(String sensorlat) {
        this.sensorlat = sensorlat;
    }

    public String getSensorlng() {
        return sensorlng;
    }

    public void setSensorlng(String sensorlng) {
        this.sensorlng = sensorlng;
    }
}