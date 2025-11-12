package org.example.appescritoriotacopaco;

import com.google.gson.annotations.SerializedName;

public class Mesa {
    @SerializedName("nombre")
    public String nombre;

    @SerializedName("ocupada")
    public boolean ocupada;

    public Mesa() {}

    public Mesa(String nombre, boolean ocupada) {
        this.nombre = nombre;
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Mesa{nombre='" + nombre + "', ocupada=" + ocupada + "}";
    }
}