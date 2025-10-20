package com.ulp.inmobiliaria.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.models.Inmueble;

import org.jspecify.annotations.NonNull;
import com.bumptech.glide.Glide;


import java.util.List;

public  class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolderInmueble> {
    private List<Inmueble> listaInmuebles;
    private Context context;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(List<Inmueble> listaInmuebles, Context context, LayoutInflater layoutInflater) {
        this.listaInmuebles = listaInmuebles;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolderInmueble onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inmueble, parent, false);
        return new ViewHolderInmueble(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInmueble holder, int position) {
        Inmueble inmueble = listaInmuebles.get(position);
        holder.direccion.setText(inmueble.getDireccion());
        holder.valor.setText(String.valueOf(inmueble.getValor()));
        Glide.with(context)
                .load("https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/" +  inmueble.getImagen())
                .placeholder(null)
                .error("null")
                .into(holder.imagen);

        ((ViewHolderInmueble)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController((Activity)context, R.id.nav_host_fragment_content_main).navigate(R.id.detalleFragment, bundle);

            }
        });
    }


    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }


    public class ViewHolderInmueble extends RecyclerView.ViewHolder {

        private TextView direccion, valor;
        private ImageView imagen;
        public ViewHolderInmueble(@NonNull View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccion);
            valor = itemView.findViewById(R.id.tvValor);
            imagen = itemView.findViewById(R.id.ivInmueble);
        }


    }


}


