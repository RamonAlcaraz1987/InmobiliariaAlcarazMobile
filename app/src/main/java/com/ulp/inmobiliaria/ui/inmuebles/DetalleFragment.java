package com.ulp.inmobiliaria.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.databinding.FragmentDetalleBinding;
import com.ulp.inmobiliaria.models.Inmueble;

public class DetalleFragment extends Fragment {

    private Inmueble inmueble;

    private FragmentDetalleBinding binding;

    private DetalleViewModel mViewModel;

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inmueble  = new Inmueble();
        mViewModel = new ViewModelProvider(this).get(DetalleViewModel.class);
        binding= FragmentDetalleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mViewModel.getmInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onChanged(Inmueble inmueble) {

                        binding.etDetalleCodigo.setText(String.valueOf(inmueble.getIdInmueble()));
                        binding.etDetalleDireccion.setText(inmueble.getDireccion());
                        binding.etDetalleAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
                        binding.etDetalleUso.setText(inmueble.getUso());
                        binding.etDetallePrecio.setText(String.valueOf(inmueble.getValor()));
                        binding.cbDisponibleInmueble.setChecked(inmueble.isDisponible());
                        binding.etDetalleTipo.setText(inmueble.getTipo());
                        Glide.with(getContext())
                                .load("https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" +  inmueble.getImagen())
                                .placeholder(null)
                                .error("null")
                                .into(binding.ivDetalleInmueble);

                    }
                });


            binding.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inmueble.setDisponible(binding.cbDisponibleInmueble.isChecked());
                    mViewModel.actualizarInmueble(inmueble);
                }
            });

            mViewModel.recuperarInmueble(getArguments());

            return view;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;


        }
    }


