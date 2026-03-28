package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.MeasurementCatalog;

public interface MeasurementCatalogInterface {

    List<MeasurementCatalog> listar();

    MeasurementCatalog listarClave(String measurementName);

    int add(MeasurementCatalog obj);

    int upd(MeasurementCatalog obj);

    int del(String measurementName);
}