package edu.upc.dsa.models;

import java.util.HashMap;

public class Pedido {
    private String idUsuario;
    // Un diccionario para guardar el Producto y qué Cantidad quiere
    private HashMap<Producto, Integer> listaProductos;

    public Pedido() {}

    public Pedido(String idUsuario) {
        this.idUsuario = idUsuario;
        this.listaProductos = new HashMap<>();
    }

    // Métod. para añadir productos al pedido
    public void añadirProducto(Producto p, int cantidad) {
        this.listaProductos.put(p, cantidad);
    }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public HashMap<Producto, Integer> getListaProductos() { return listaProductos; }
    public void setListaProductos(HashMap<Producto, Integer> listaProductos) { this.listaProductos = listaProductos; }
}
