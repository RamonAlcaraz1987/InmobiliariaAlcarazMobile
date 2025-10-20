package com.ulp.inmobiliaria.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.SupportMapFragment;
import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment{

    private FragmentInicioBinding binding;

    private InicioViewModel mViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        mViewModel.getMutableMapaActual().observe(getViewLifecycleOwner(), new Observer<InicioViewModel.MapaActual>() {
            @Override
            public void onChanged(InicioViewModel.MapaActual mapaActual) {
                ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(mapaActual);

            }
        });

        mViewModel.obtenermapa();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}