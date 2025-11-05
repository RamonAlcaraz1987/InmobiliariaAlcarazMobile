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
import com.ulp.inmobiliaria.databinding.FragmentDetallePagosBinding;
import com.ulp.inmobiliaria.models.Pago;

import java.util.List;

public class DetallePagosFragment extends Fragment {

    private DetallePagosViewModel mViewModel;

    private FragmentDetallePagosBinding binding;


    public static DetallePagosFragment newInstance() {
        return new DetallePagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetallePagosViewModel.class);
        binding = FragmentDetallePagosBinding.inflate(inflater, container, false);
        mViewModel.recuperarDatosDelBundle(getArguments());

        mViewModel.getPago().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagoAdapter adapter = new PagoAdapter(pagos, getContext(), getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rvPagos.setLayoutManager(glm);
                binding.rvPagos.setAdapter(adapter);
            }
        });



        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






}