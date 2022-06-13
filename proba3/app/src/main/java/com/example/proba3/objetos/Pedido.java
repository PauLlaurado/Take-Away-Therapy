package com.example.proba3.objetos;

import com.example.proba3.objetos.Medicamentos;

import java.util.ArrayList;

public class Pedido {

    String contractat, id, pagado, infermer, address, servicio;
    ArrayList<Medicamentos> arrayListmedicamentos;

    public Pedido() {
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfermer() {
        return infermer;
    }

    public void setInfermer(String infermer) {
        this.infermer = infermer;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getContractat() {
        return contractat;
    }

    public void setContractat(String contractat) {
        this.contractat = contractat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Medicamentos> getArrayListmedicamentos() {
        return arrayListmedicamentos;
    }

    public void setArrayListmedicamentos(ArrayList<Medicamentos> arrayListmedicamentos) {
        this.arrayListmedicamentos = arrayListmedicamentos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "contractat='" + contractat + '\'' +
                ", id='" + id + '\'' +
                ", pagado='" + pagado + '\'' +
                ", infermer='" + infermer + '\'' +
                ", address='" + address + '\'' +
                ", servicio='" + servicio + '\'' +
                ", arrayListmedicamentos=" + arrayListmedicamentos +
                '}';
    }
}
