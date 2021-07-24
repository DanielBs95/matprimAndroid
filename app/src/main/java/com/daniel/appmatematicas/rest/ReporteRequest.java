package com.daniel.appmatematicas.rest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ReporteRequest implements Serializable {


    @SerializedName("nombre")
    private String nombre;

    @SerializedName("nota")
    private String nota;

    @SerializedName("motivacion")
    private String motivacion;

    @SerializedName("satisfaccion")
    private String satisfaccion;


    @SerializedName("cantidad")
    private String cantidad;



    public ReporteRequest() {
    }

    public ReporteRequest(String nombre, String nota) {
        this.nombre = nombre;
        this.nota = nota;
    }


    public ReporteRequest(String nombre, String nota, String motivacion, String satisfaccion, String satisfaccion1) {
        this.nombre = nombre;
        this.nota = nota;
        this.motivacion = motivacion;
        this.satisfaccion = satisfaccion;
        this.satisfaccion = satisfaccion1;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public String getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(String satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }


}
