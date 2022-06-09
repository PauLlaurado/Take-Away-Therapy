package com.example.proba3;

import java.io.Serializable;

public class Medicamentos implements Serializable {
    public String nombre, decripcion, precio, cantidad;

    public Medicamentos(String name, String descr, String preu, String cant) {
        this.nombre = name;
        this.decripcion = descr;
        this.precio = preu;
        this.cantidad = cant;
    }


    public Medicamentos() {
    }

    public String getNombre() {
        return nombre;    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Medicamentos{" +
                "name='" + nombre + '\'' +
                ", descr='" + decripcion + '\'' +
                ", preu=" + precio +
                ", cant=" + cantidad +
                '}';
    }
}
