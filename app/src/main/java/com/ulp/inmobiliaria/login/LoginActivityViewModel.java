package com.ulp.inmobiliaria.login;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.inmobiliaria.MainActivity;
import com.ulp.inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mError;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getError() {
        if (mError == null) {
            mError = new MutableLiveData<String>();
        }
        return mError;
    }

    public void logueo(String usuario, String clave)
    {
        if(usuario.isEmpty() || clave.isEmpty())
        {

            mError.setValue("Los campos no pueden estar vacios");

            return;

        }


       ApiClient.InmoService inmoService= ApiClient.getInmoService();
        Call<String> call= inmoService.loginForm(usuario,clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    String token =response.body();
                    ApiClient.guardarToken(getApplication(),token);
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);

                }
                else{
                    String errorMsg = "Error del servidor";
                    if (response.code() == 401) {
                        errorMsg = "Usuario o contraseña incorrectos";
                    } else if (response.code() == 404) {
                        errorMsg = "Endpoint no encontrado";
                    } else if (response.code() >= 500) {
                        errorMsg = "Error interno del servidor";
                    }
                    mError.setValue(errorMsg);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mError.setValue("Error de conexion");
                Log.d("error",t.getMessage());

            }


        });






    }
}
