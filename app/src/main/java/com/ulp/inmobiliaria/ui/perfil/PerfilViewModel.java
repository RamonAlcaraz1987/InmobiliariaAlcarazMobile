package com.ulp.inmobiliaria.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.inmobiliaria.models.Propietario;
import com.ulp.inmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {


   private MutableLiveData<Propietario> mPropietario = new MutableLiveData<>();
    private MutableLiveData<Boolean> mBandera = new MutableLiveData<>();
    private MutableLiveData<String> mNombreBoton = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getBandera() {
        return mBandera;
    }
    public LiveData<String> getNombreBoton() {
        return mNombreBoton;
    }

    public LiveData<Propietario> getPropietario() {
        return mPropietario;
    }


    public void habilitar (String nombreBoton,String nombre, String apellido, String dni, String telefono, String email) {

        if (nombreBoton.equals("Editar")) {

            mBandera.setValue(true);
            mNombreBoton.setValue("Guardar");
        }
        else {
            mBandera.setValue(false);
            mNombreBoton.setValue("Editar");
            Propietario nuevo = new Propietario();
            nuevo.setIdPropietario(mPropietario.getValue().getIdPropietario());
            nuevo.setNombre(nombre);
            nuevo.setApellido(apellido);
            nuevo.setDni(dni);
            nuevo.setTelefono(telefono);
            nuevo.setEmail(email);
            String token = ApiClient.leerToken(getApplication());
            ApiClient.InmoService inmoService;
            inmoService = ApiClient.getInmoService();
            Call<Propietario> call = inmoService.actualizarPropietario("Bearer " + token, nuevo);
            inmoService.actualizarPropietario("Bearer " + token,nuevo);
            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplication(), "Propietario Actualizado", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplication(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(getApplication(), "Error de conexion", Toast.LENGTH_SHORT).show();
                }
            });

            }


        }



    public void obtenerPerfil(){

        String token = ApiClient.leerToken(getApplication());

        ApiClient.InmoService inmoService;
        inmoService = ApiClient.getInmoService();

        Call<Propietario> call = inmoService.getPropietario("Bearer " + token);
        inmoService.getPropietario("Bearer " + token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    mPropietario.setValue(response.body());
                }
                else {
                    Toast.makeText(getApplication(), "Error al obtener el perfil", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });


    }
}