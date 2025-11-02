package com.ulp.inmobiliaria.ui.inmuebles;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.databinding.FragmentCrearBinding;
import com.ulp.inmobiliaria.models.Inmueble;
import com.ulp.inmobiliaria.models.Propietario;

public class CrearFragment extends Fragment {

    private CrearViewModel cViewModel;

    private FragmentCrearBinding binding;

    private Intent intent;

    private ActivityResultLauncher<Intent> arl;







    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cViewModel = new ViewModelProvider(this).get(CrearViewModel.class);
        binding = FragmentCrearBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        cViewModel.getUriMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivCrearInmueble.setImageURI(uri);
            }
        });


        abrirGaleria();
        binding.btnGuardarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cargarInmueble();












            }
        });


        return view;
    }


        private void cargarInmueble(){

        String direccion = binding.etCrearDireccion.getText().toString();
        String tipo = binding.etCrearTipo.getText().toString();
        String uso = binding.etCrearUso.getText().toString();

        String valor = (binding.etCrearPrecio.getText().toString());
        String ambientes = (binding.etCrearAmbientes.getText().toString());
        boolean disponible = binding.cbCrearDisponible.isChecked();
        String imagen = binding.ivCrearInmueble.toString();
        double latitud = 0;
        double longitud = 0;
        String superficie =binding.etCrearSuperficie.getText().toString();
        int idPropietario = 0;
        cViewModel.guardarInmueble(direccion,tipo,uso,superficie,valor,ambientes,disponible,imagen,latitud,longitud,idPropietario);










        }
        private void abrirGaleria(){
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    cViewModel.recibirFoto(result);
                    Log.d("AgregarInmuebleFragment", "result; " + result);
                }
            });

         }

         public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }



    }




