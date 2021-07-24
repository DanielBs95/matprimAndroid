package com.daniel.appmatematicas.rest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class UsuarioResponse implements Serializable {

    @SerializedName("nombre_usuario")
    private String nombre_usuario;

    @SerializedName("sexo_usuario")
    private String sexo_usuario;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String nombre_usuario, String sexo_usuario, String email, String password) {
        this.nombre_usuario = nombre_usuario;
        this.sexo_usuario = sexo_usuario;
        this.email = email;
        this.password = password;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getSexo_usuario() {
        return sexo_usuario;
    }

    public void setSexo_usuario(String sexo_usuario) {
        this.sexo_usuario = sexo_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
