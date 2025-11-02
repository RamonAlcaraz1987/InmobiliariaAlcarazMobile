package com.ulp.inmobiliaria.ui.contratos;

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
import com.ulp.inmobiliaria.databinding.FragmentDetalleContratoBinding;
import com.ulp.inmobiliaria.models.Contrato;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel mViewModel;
    private FragmentDetalleContratoBinding binding;



    public static DetalleContratoFragment newInstance() {
        return new DetalleContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        mViewModel.recuperarDatosDelBundle(getArguments());

        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                binding.etCodigoContrato.setText(String.valueOf(contrato.getIdContrato()));
                binding.etFechaInicioContrato.setText(sdf.format(contrato.getFechaInicio()));
                binding.etFechaFinContrato.setText(sdf.format(contrato.getFechaFinalizacion()));
                binding.etMontoContrato.setText(String.valueOf(contrato.getMontoAlquiler()));
                binding.etInmuebleDireccionContrato.setText(contrato.getInmueble().getDireccion());
                binding.etInquilinoContrato.setText(contrato.getInquilino().getNombre() + "  " + contrato.getInquilino().getApellido());
            }
        });












        return binding.getRoot();
    }





}