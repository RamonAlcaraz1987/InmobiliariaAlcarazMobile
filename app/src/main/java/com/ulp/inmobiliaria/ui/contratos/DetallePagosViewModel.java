package com.ulp.inmobiliaria.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliaria.models.Contrato;
import com.ulp.inmobiliaria.models.Pago;
import com.ulp.inmobiliaria.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePagosViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> contratoLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Pago>> pagoLiveData = new MutableLiveData<>();

    public DetallePagosViewModel(@NonNull Application application)
    {
        super(application);

    }

    public LiveData<Contrato> getContrato(){
        return contratoLiveData;
    }

    public LiveData<List<Pago>> getPago(){
        return pagoLiveData;
    }

    public void recuperarDatosDelBundle(Bundle bundle){
        if(bundle != null){
            Contrato contrato = (Contrato) bundle.get("contrato");
            if(contrato != null){
                contratoLiveData.setValue(contrato);
                cargarPagosPorContrato(contrato);

            }
        }
    }

    public void cargarPagosPorContrato(Contrato contrato){
         String token = ApiClient.leerToken(getApplication());
         ApiClient.InmoService api = ApiClient.getInmoService();

         Call <List<Pago>> call = api.getPagosPorContrato("Bearer " + token, contrato.getIdContrato());
         call.enqueue(new Callback<List<Pago>>(){
             @Override

             public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response){
                 if(response.isSuccessful()){
                     pagoLiveData.setValue(response.body());
                }else{
                     Toast.makeText(getApplication(), "Error al obtener los pagos", Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<List<Pago>> call, Throwable t) {
                 Toast.makeText(getApplication(), "Error al obtener los pagos", Toast.LENGTH_LONG).show();
             }
         });
         }
    }








