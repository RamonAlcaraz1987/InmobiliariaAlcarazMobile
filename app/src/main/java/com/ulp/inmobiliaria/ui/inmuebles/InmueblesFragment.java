package com.ulp.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ulp.inmobiliaria.MainActivity;
import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.databinding.FragmentInmueblesBinding;
import com.ulp.inmobiliaria.models.Inmueble;

import java.util.List;

public class InmueblesFragment extends Fragment {

        private InmueblesViewModel mViewModel;

    private FragmentInmueblesBinding binding;



    public static InmueblesFragment newInstance() {
        return new InmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);


    mViewModel.getListaInmuebles().observe(getViewLifecycleOwner(),new Observer<List<Inmueble>>() {
        @Override
        public void onChanged(List<Inmueble> inmuebles) {
            InmuebleAdapter adapter = new InmuebleAdapter(inmuebles, getContext(), getLayoutInflater());
            GridLayoutManager glm=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
            binding.listaInmuebles.setLayoutManager(glm);
            binding.listaInmuebles.setAdapter(adapter);
        }
    });

        binding.fabNuevoInmueble.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.crearFragment);
        });


        mViewModel.getInmuebles();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}



