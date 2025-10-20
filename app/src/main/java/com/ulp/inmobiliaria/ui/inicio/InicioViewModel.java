package com.ulp.inmobiliaria.ui.inicio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;


public class InicioViewModel extends AndroidViewModel {

    private MutableLiveData<MapaActual> MutableMapaActual;

    public InicioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MapaActual> getMutableMapaActual() {
        if (MutableMapaActual == null) {
            MutableMapaActual = new MutableLiveData<MapaActual>();
        }
        return MutableMapaActual;
    }

   public void obtenermapa(){
        MapaActual mapa = new MapaActual();
        MutableMapaActual.setValue(mapa);


   }

   public class MapaActual implements OnMapReadyCallback {
       LatLng SANLUIS = new LatLng(-33.280576, -66.332482);



       @Override
       public void onMapReady(@NonNull GoogleMap googleMap) {
           googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
           googleMap.addMarker(new MarkerOptions().position(SANLUIS).title("San Luis"));

           CameraPosition cameraPosition = new CameraPosition.Builder().target(SANLUIS).zoom(12).bearing(45).tilt(70).build();
           CameraUpdate camaraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
           googleMap.animateCamera(camaraUpdate);


       }

   }


}