package com.daniel.appmatematicas.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class Reporte implements Serializable {

    @SerializedName("id_reporte")
    @Expose
    private String id_reporte;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("fecha")
    @Expose
    private Date fecha;

    @SerializedName("nota")
    @Expose
    private String nota;

    public Reporte() {
    }

    public Reporte(String id_reporte, String nombre, Date fecha, String nota) {
        this.id_reporte = id_reporte;
        this.nombre = nombre;
        this.fecha = fecha;
        this.nota = nota;
    }

    public String getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(String id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id_reporte='" + id_reporte + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", nota='" + nota + '\'' +
                '}';
    }
}
