package org.example.appescritoriotacopaco;

import com.google.gson.annotations.SerializedName;

public class Pedido {

    @SerializedName("precioTotal")
    public double precioTotal;

    public Pedido() {}

    public Pedido(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Pedido{precioTotal=" + precioTotal + "}";
    }
}
