package com.ulp.inmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.databinding.FragmentContratosBinding;
import com.ulp.inmobiliaria.databinding.FragmentInquilinosBinding;
import com.ulp.inmobiliaria.models.Inmueble;

import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel mViewModel;

    private FragmentContratosBinding binding;


    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);
        binding = FragmentContratosBinding.inflate(inflater, container, false);

        mViewModel.getListaInmueblesVigentes().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                ContratoAdapter adapter = new ContratoAdapter(inmuebles, getContext(), getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                binding.rvContratos.setLayoutManager(glm);
                binding.rvContratos.setAdapter(adapter);


            }
        });

        mViewModel.cargarInmuebleConContrato();
        return binding.getRoot();




    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }



}