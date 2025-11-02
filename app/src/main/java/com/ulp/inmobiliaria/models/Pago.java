package com.ulp.inmobiliaria.models;

import java.io.Serializable;
import java.util.Date;

public class Pago implements Serializable {
    private int idPago;
    private int idContrato;
    private double monto;
    private Date fechaPago;

    private String detalle;

    private Boolean estado;

    private Contrato contrato;

    public Pago() {
    }

    public Pago(Boolean estado, Contrato contrato, String detalle, Date fechaPago, double monto, int idContrato, int idPago) {
        this.estado = estado;
        this.contrato = contrato;
        this.detalle = detalle;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.idContrato = idContrato;
        this.idPago = idPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
}
