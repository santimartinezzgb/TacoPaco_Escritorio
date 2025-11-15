package org.example.appescritoriotacopaco;

public class Mesa {

    private String nombre;
    public boolean ocupada;
    public boolean a_pagar;

    public Mesa() {}

    // Constructor con parámetros
    public Mesa(String nombre, boolean ocupada) {
        this.nombre = nombre;
        this.ocupada = ocupada;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public boolean mesaPagada(){
        return a_pagar;
    }

}
