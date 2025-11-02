package com.ulp.inmobiliaria.models;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable {

    private int idContrato;
    private Date fechaInicio;

    private Date fechaFinalizacion;

    private Boolean estado;

    private Double montoAlquiler;

    private int idInquilino;

    private int idInmueble;

    private Inquilino inquilino;

    private Inmueble inmueble;

    public Contrato() {
    }

    public Contrato(int idContrato, Date fechaFinalizacion, Boolean estado, Date fechaInicio, int idInquilino, Double montoAlquiler, Inmueble inmueble, Inquilino inquilino, int idInmueble) {
        this.idContrato = idContrato;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.idInquilino = idInquilino;
        this.montoAlquiler = montoAlquiler;
        this.inmueble = inmueble;
        this.inquilino = inquilino;
        this.idInmueble = idInmueble;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        idInmueble = idInmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(Double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFin) {
        this.fechaFinalizacion = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
