package com.example.diana.municipiosnarino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.diana.municipiosnarino.datosApi.SitiosService;
import com.example.diana.municipiosnarino.models.Municipios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public static final String TAG = "DATOSCOLOMBIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        obtenerDatos();
    }

    public void obtenerDatos() {
        SitiosService service = retrofit.create(SitiosService.class);
        Call<List<Municipios>> sitioRespuestaCall = service.obtenerListadeSitios();
        sitioRespuestaCall.enqueue(new Callback<List<Municipios>>() {
            @Override
            public void onResponse(Call<List<Municipios>> call, Response<List<Municipios>> response) {
                if (response.isSuccessful()){
                    List lista = response.body();
                    for (int i=0; i<lista.size(); i++){
                        Municipios m = (Municipios) lista.get(i);
                        Log.i(TAG,"Nombre: " + m.getNombreMunicipio() + "Alcalde: " + m.getNombreAlcalde());
                    }
                }
                else{
                    Log.e(TAG," onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<Municipios>> call, Throwable t) {
                Log.e(TAG," onFailure: " + t.getMessage());
            }
        });
    }
}
