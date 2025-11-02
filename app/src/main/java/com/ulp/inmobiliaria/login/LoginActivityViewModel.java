package com.ulp.inmobiliaria.login;

import android.app.Application;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
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
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ManejaShake shakeListener;

    private static final float SHAKE_THRESHOLD = 30.0f;

    private long ultimaActualizacion;
    private float ultimaAceleracionX;
    private float ultimaAceleraciony;
    private float ultimaAceleracionZ;


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
    public void comienzoDeteccionShake() {
        sensorManager = (SensorManager) getApplication().getSystemService(getApplication().SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            shakeListener = new ManejaShake();
            sensorManager.registerListener(shakeListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            ultimaActualizacion = System.currentTimeMillis();

        }else {
            mError.setValue("El dispositivo no tiene sensor de acelerometro");
        }





    }

    public void finDeteccionShake() {
        if (sensorManager != null && shakeListener != null) {
            sensorManager.unregisterListener(shakeListener);
        }
    }

    public void comenzarCall(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:2664553747"));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            getApplication().startActivity(callIntent);
        } catch (SecurityException e) {
            mError.setValue("No tienes permiso para llamar");
        } catch (Exception e){
            mError.setValue("Error al llamar");
        }


    }

    private class ManejaShake implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            long tiempoActual = System.currentTimeMillis();
           if((tiempoActual - ultimaActualizacion) > 100) {
               float x = event.values[0];
               float y = event.values[1];
               float z = event.values[2];

               float aceleracion = (float) Math.sqrt(x * x + y * y + z * z);
               float delta = Math.abs(aceleracion - (float) Math.sqrt(ultimaAceleracionX * ultimaAceleracionX + ultimaAceleraciony * ultimaAceleraciony + ultimaAceleracionZ));
               if (delta > SHAKE_THRESHOLD) {
                   comenzarCall();
               }
               ultimaAceleracionX = x;
               ultimaAceleraciony = y;
               ultimaAceleracionZ = z;
               ultimaActualizacion = tiempoActual;
               }
           }

           @Override
           public void onAccuracyChanged(Sensor sensor, int accuracy) {


           }





        }

}




