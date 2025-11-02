package com.ulp.inmobiliaria.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.ulp.inmobiliaria.models.Inmueble;
import com.ulp.inmobiliaria.request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import kotlinx.coroutines.selects.TrySelectDetailedResult;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearViewModel extends AndroidViewModel {

    private MutableLiveData<Uri> uriMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<Inmueble> mInmueble = new MutableLiveData<>();


    public CrearViewModel(@NonNull Application application) {

        super(application);


    }


    public LiveData<Uri> getUriMutableLiveData() {
        return uriMutableLiveData;
    }

    public LiveData<Inmueble> getmInmueble() {
        return mInmueble;
    }


    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();

            uriMutableLiveData.setValue(uri);

        }


    }

    public void guardarInmueble(String direccion, String tipo, String uso, String superficie, String valor, String ambientes, boolean disponible, String imagen, double latitud, double longitud, int idPropietario) {

        try {
            int superficiep = Integer.parseInt(superficie);
            double valorp = Double.parseDouble(valor);
            int ambientep = Integer.parseInt(ambientes);
            Inmueble inmueble = new Inmueble();
            inmueble.setDisponible(disponible);
            inmueble.setDireccion(direccion);
            inmueble.setTipo(tipo);
            inmueble.setUso(uso);
            inmueble.setIdPropietario(idPropietario);
            inmueble.setValor(valorp);
            inmueble.setAmbientes(ambientep);
            inmueble.setImagen(imagen);
            inmueble.setLatitud(latitud);
            inmueble.setLongitud(longitud);
            inmueble.setSuperficie(superficiep);
            //convertir la imagen en bits
            //inmueble.setImagen(new String(imagenBytes));
            byte[] imagenBytes = transformarImagen();
            if (imagenBytes.length == 0) {
                Toast.makeText(getApplication(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                return;
            }
            String inmuebleJson = new Gson().toJson(inmueble);
            RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagenBytes);
            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);
            ApiClient.InmoService api = ApiClient.getInmoService();
            String token = ApiClient.leerToken(getApplication());
            Call<Inmueble> llamada = api.CargarInmueble("Bearer " + token, imagenPart, inmuebleBody);
            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getApplication(), "Inmueble guardado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable throwable) {
                    Toast.makeText(getApplication(), "Error al guardar inmueble", Toast.LENGTH_SHORT).show();
                }
            });



           

        } catch (NumberFormatException e) {
            Toast.makeText(getApplication(), "Error al guardar el inmueble hay campos numericos incorrectos", Toast.LENGTH_SHORT).show();
            return;


        }


    }

    private byte[] transformarImagen() {

        try {
            Uri uri = uriMutableLiveData.getValue();

            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(), "Error al cargar la imagen", Toast.LENGTH_LONG).show();
            return new byte[]{};
        }






    }


}