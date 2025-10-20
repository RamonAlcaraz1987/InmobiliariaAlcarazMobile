package com.ulp.inmobiliaria.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliaria.models.Inmueble;
import com.ulp.inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mInmueble;
    public DetalleViewModel(@NonNull Application application) {
        super(application);
    }

  public LiveData<Inmueble> getmInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<Inmueble>();
        }
        return mInmueble;
    }



    public void recuperarInmueble(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
       if(inmueble != null) {
           mInmueble.setValue(inmueble);
       }

    }

    public void actualizarInmueble(Inmueble inmueble) {
        ApiClient.InmoService api = ApiClient.getInmoService();
        String token = ApiClient.leerToken(getApplication());
        inmueble.setIdInmueble(mInmueble.getValue().getIdInmueble());
        Call<Inmueble> call = api.actualizarInmueble("Bearer " + token, inmueble);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Inmueble actualizado correctamente", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplication(), "Error al actualizar el inmueble", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error al actualizar el inmueble", Toast.LENGTH_LONG).show();

            }
        });




    }








}