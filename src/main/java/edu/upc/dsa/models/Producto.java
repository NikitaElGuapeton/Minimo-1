package edu.upc.dsa.models;

public class Producto {
    private String nombre;
    private double precio;
    private int numeroVentas;

    // Constructor vacío (Parte 2)
    public Producto() {}

    // Constructor con datos
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.numeroVentas = 0; // Al crearlo, tiene 0 ventas
    }

    // Getters y Setters (Para poder leer y modificar los datos)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getNumeroVentas() { return numeroVentas; }
    public void setNumeroVentas(int numeroVentas) { this.numeroVentas = numeroVentas; }
}
