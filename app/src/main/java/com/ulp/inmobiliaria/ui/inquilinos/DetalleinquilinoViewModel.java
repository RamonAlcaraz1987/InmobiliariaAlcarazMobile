package com.ulp.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliaria.models.Contrato;
import com.ulp.inmobiliaria.models.Inmueble;
import com.ulp.inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleinquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> contratoLiveData = new MutableLiveData<>();

    private MutableLiveData<Inmueble> inmuebleLiveData = new MutableLiveData<>();

    public DetalleinquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getContrato(){

        return contratoLiveData;

    }

    public LiveData<Inmueble> getInmueble(){
        return inmuebleLiveData;
    }

    public void recuperarDatosDelBundle(Bundle bundle) {
         if(bundle != null){
             Inmueble inmueble = (Inmueble) bundle.get("inmueble");
          if(inmueble != null) {
                 inmuebleLiveData.setValue(inmueble);
                 cargarContratoPorInmueble(inmueble);
             }
         }
    }

    public void cargarContratoPorInmueble(Inmueble inmueble){
        String token = ApiClient.leerToken(getApplication());
        ApiClient.InmoService api = ApiClient.getInmoService();

        Call<Contrato> call = api.getContratosPorInmueble("Bearer " + token, inmueble.getIdInmueble());
        call.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    contratoLiveData.setValue(response.body());
                }else{
                    Toast.makeText(getApplication(), "Error al obtener el contrato", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error al obtener el contrato", Toast.LENGTH_LONG).show();

            }
        });

    }



}