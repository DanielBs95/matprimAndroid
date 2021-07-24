package com.daniel.appmatematicas.rest;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class TemaResponse implements Serializable  {

    @SerializedName("id_tema")
    private Long id_tema;

    @SerializedName("titulo_tema")
    private String titulo_tema;

    @SerializedName("preguntas_tema")
    private String preguntas_tema;

    @SerializedName("posicion")
    private String posicion;


    public TemaResponse() {
    }

    public TemaResponse(TemaResponse obj) {
        new TemaResponse(obj.getId_tema(),obj.getTitulo_tema(),obj.getPreguntas_tema(),obj.getPosicion());

    }

    public TemaResponse(Long id_tema, String titulo_tema, String preguntas_tema, String posicion) {
        this.id_tema = id_tema;
        this.titulo_tema = titulo_tema;
        this.preguntas_tema = preguntas_tema;
        this.posicion = posicion;
    }

    public Long getId_tema() {
        return id_tema;
    }

    public void setId_tema(Long id_tema) {
        this.id_tema = id_tema;
    }

    public String getTitulo_tema() {
        return titulo_tema;
    }

    public void setTitulo_tema(String titulo_tema) {
        this.titulo_tema = titulo_tema;
    }

    public String getPreguntas_tema() {
        return preguntas_tema;
    }

    public void setPreguntas_tema(String preguntas_tema) {
        this.preguntas_tema = preguntas_tema;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
