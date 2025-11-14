package org.example.appescritoriotacopaco;

public class Pedido {

    private String _id;
    private double precioTotal;
    private String fecha;
    private String mesa; // campo imprescindible

    public Pedido() {}

    public Pedido(double precioTotal, String fecha, String mesa) {
        this.precioTotal = precioTotal;
        this.fecha = fecha;
        this.mesa = mesa;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public String getMesa() {
        return mesa;
    }

    public String getFecha() {
        return fecha;
    }
}
