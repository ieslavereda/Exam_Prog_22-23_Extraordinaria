package es.ieslavereda.extraordinaria2023.model;

public abstract class Jugador {
    private String nombre;
    private String apellidos;
    protected float saldo;

    public Jugador(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.saldo = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String toString(){
        return nombre+" "+apellidos;
    }
}
