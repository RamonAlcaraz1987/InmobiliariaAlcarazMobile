package com.ulp.inmobiliaria.ui.logout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.inmobiliaria.request.ApiClient;

public class LogoutViewModel extends AndroidViewModel {

//    private MutableLiveData<Boolean> mostrarDialogo;
//    private MutableLiveData<Boolean> navegarLogin;
//    private MutableLiveData<Boolean> navegarInicio;
   public LogoutViewModel(@NonNull Application application) {     super(application);
   }

//    public LiveData <Boolean> getMostrarDialogo() {
//        if (mostrarDialogo == null) {
//            mostrarDialogo = new MutableLiveData<>();
//        }
//        return mostrarDialogo;
//    }
//
//    public LiveData <Boolean> getNavegarLogin(){
//        if(navegarLogin == null){
//            navegarLogin = new MutableLiveData<>();
//
//        }
//        return navegarLogin;
//    }
//    public LiveData<Boolean> getNavegarInicio() {
//        if(navegarInicio == null){
//         navegarInicio = new MutableLiveData<>();
//        }
//        return navegarInicio;
//    }
//
//    public void iniciarLogout(){
//        mostrarDialogo.setValue(true);
//    }
//    public void confirmarLogout(){
//        ApiClient.clearToken(getApplication());
//        navegarLogin.setValue(true);
//    }
//    public void cancelarLogout() {
//        navegarInicio.setValue(true);
//    }


}