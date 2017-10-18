package com.example.diana.municipiosnarino.datosApi;


import com.example.diana.municipiosnarino.models.Municipios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by diana on 12/10/17.
 */

public interface SitiosService {
    @GET("pfet-63uw.json")
    Call<List<Municipios>> obtenerListadeSitios();
}
