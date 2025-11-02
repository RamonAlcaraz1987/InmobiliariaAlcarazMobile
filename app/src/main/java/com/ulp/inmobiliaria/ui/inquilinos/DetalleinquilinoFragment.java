package com.ulp.inmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.databinding.FragmentDetalleinquilinoBinding;
import com.ulp.inmobiliaria.models.Contrato;

public class DetalleinquilinoFragment extends Fragment {

    private DetalleinquilinoViewModel mViewModel;
    private FragmentDetalleinquilinoBinding binding;


    public static DetalleinquilinoFragment newInstance() {
        return new DetalleinquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleinquilinoViewModel.class);
        binding = FragmentDetalleinquilinoBinding.inflate(inflater, container, false);
        mViewModel.recuperarDatosDelBundle(getArguments());

        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                 {
                    binding.etCodigoInquilino.setText(String.valueOf(contrato.getInquilino().getIdInquilino()));
                    binding.etNombreInquilino.setText(contrato.getInquilino().getNombre());
                    binding.etApellidoInquilino.setText(contrato.getInquilino().getApellido());
                    binding.etDniInquilino.setText(contrato.getInquilino().getDni());
                    binding.etTelefonoInquilino.setText(contrato.getInquilino().getTelefono());
                    binding.etEmailInquilino.setText(contrato.getInquilino().getEmail());
                }
            }
        });





        return binding.getRoot();
    }



}