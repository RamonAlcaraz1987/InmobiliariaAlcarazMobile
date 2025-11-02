package com.ulp.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliaria.models.Inmueble;
import com.ulp.inmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {
   private MutableLiveData<List<Inmueble>> listaInmuebleVigentes = new MutableLiveData<>();

  public InquilinosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaInmueblesVigentes() {
        return listaInmuebleVigentes;
    }

    public void cargarInmueblesConContrato(){

      String token = ApiClient.leerToken(getApplication());
      ApiClient.InmoService api = ApiClient.getInmoService();

      Call<List<Inmueble>> call = api.getInmueblesContratosVigentes("Bearer " + token);
      call.enqueue(new Callback<List<Inmueble>>() {
          @Override
          public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response){
              if(response.isSuccessful()){
                  listaInmuebleVigentes.setValue(response.body());
              }else{
                  Toast.makeText(getApplication(), "Error al obtener los inmuebles", Toast.LENGTH_LONG).show();
              }


          }
          @Override
          public void onFailure(Call<List<Inmueble>> call, Throwable t) {
              Toast.makeText(getApplication(), "Error al obtener los inmuebles", Toast.LENGTH_LONG).show();
          }
      });
          }











    }





