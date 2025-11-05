package com.ulp.inmobiliaria.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.inmobiliaria.R;
import com.ulp.inmobiliaria.models.Pago;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolderPago> {

    private List<Pago> listaPagosContrato;

    private Context context;

    private LayoutInflater layoutInflater;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public PagoAdapter(List<Pago> listaPagosContrato, Context context, LayoutInflater layoutInflater) {
        this.listaPagosContrato = listaPagosContrato;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public PagoAdapter.ViewHolderPago onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.pagos, parent, false);

        return new ViewHolderPago(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.ViewHolderPago holder, int position) {
        Pago pago = listaPagosContrato.get(position);
        holder.fechaPago.setText(sdf.format(pago.getFechaPago()));
        holder.monto.setText(String.valueOf(pago.getMonto()));
        holder.detalle.setText(pago.getDetalle());
        holder.codigoContrato.setText(String.valueOf(pago.getIdContrato()));
        holder.codigo.setText(String.valueOf(pago.getIdPago()));

    }

    @Override
    public int getItemCount() {
        return listaPagosContrato.size();
    }

    public class ViewHolderPago extends RecyclerView.ViewHolder{

        private EditText fechaPago;
        private EditText monto;
        private EditText detalle;
        private EditText codigoContrato;
        private EditText codigo;

        public ViewHolderPago(@NonNull View itemView) {
            super(itemView);
            fechaPago = itemView.findViewById(R.id.etFechaPago);
            monto = itemView.findViewById(R.id.etImportePago);
            detalle = itemView.findViewById(R.id.etDetallePago);
            codigoContrato = itemView.findViewById(R.id.etCodigoContratoPago);
            codigo = itemView.findViewById(R.id.etCodigoPago);
        }

    }
}
