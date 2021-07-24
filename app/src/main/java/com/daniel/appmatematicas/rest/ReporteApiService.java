package com.daniel.appmatematicas.rest;

import com.daniel.appmatematicas.util.Reporte;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReporteApiService {

    @GET("api/v1/reporte/")
    Call<List<Reporte>> getMovie();


    @POST("/api/v1/reporte/")
    Call<ReporteRequest> saveNota(@Body ReporteRequest request);


    @POST("/api/v1/usuario/")
    Call<UsuarioResponse> saveNota(@Body UsuarioResponse request);


    @GET("/api/v1/tema/")
    Call<List<TemaResponse>> listTema();

}
