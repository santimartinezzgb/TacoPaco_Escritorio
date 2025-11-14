package org.example.appescritoriotacopaco;

public class Mesa {

    private String nombre;
    private boolean ocupada;

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

}
