package com.ulp.inmobiliaria.ui.contratos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.models.Inmueble;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolderContrato> {
    private List<Inmueble> listaInmuebleVigente;

    private Context context;

    private LayoutInflater layoutInflater;


    public ContratoAdapter(List<Inmueble> listaInmuebleVigente, Context context, LayoutInflater layoutInflater) {
        this.listaInmuebleVigente = listaInmuebleVigente;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ContratoAdapter.ViewHolderContrato onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inquilino, parent, false);

        return new ViewHolderContrato(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolderContrato holder, int position) {

        Inmueble inmueble = listaInmuebleVigente.get(position);
        holder.direccion.setText(inmueble.getDireccion());

        Glide.with(context)
                .load("https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" +  inmueble.getImagen())
                .placeholder(null)
                .error("null")
                .into(holder.imagen);
        holder.btnVerInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_main)
                        .navigate(R.id.detalleContratoFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaInmuebleVigente.size();
    }

    public class ViewHolderContrato extends RecyclerView.ViewHolder {

        private TextView direccion;
        private ImageView imagen;
        private Button btnVerInquilino;

        public ViewHolderContrato(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccionInquilino);
            imagen = itemView.findViewById(R.id.ivInmuebleInquilino);
            btnVerInquilino = itemView.findViewById(R.id.btnVerInquilino);
        }


    }



}


