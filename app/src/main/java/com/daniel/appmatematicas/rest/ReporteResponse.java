package com.daniel.appmatematicas.rest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ReporteResponse implements Serializable {

    @SerializedName("id_reporte")
    private int id_reporte;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("nota")
    private String nota;

    public ReporteResponse(int id_reporte, String nombre, String fecha, String nota) {
        this.id_reporte = id_reporte;
        this.nombre = nombre;
        this.fecha = fecha;
        this.nota = nota;
    }

    public ReporteResponse() {
    }

    public int getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(int id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
