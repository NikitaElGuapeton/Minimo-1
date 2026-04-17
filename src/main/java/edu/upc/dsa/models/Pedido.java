package edu.upc.dsa.models;

import java.util.HashMap;
import java.util.List;

public class Pedido {
    private String idUsuario;
    private List<Producto> listaProductos;

    public Pedido() {}

    public Pedido(String idUsuario, List<Producto> listaProductos) {
        this.idUsuario = idUsuario;
        this.listaProductos = listaProductos;
    }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public List<Producto> getListaProductos() { return listaProductos; }
    public void setListaProductos(List<Producto> listaProductos) { this.listaProductos = listaProductos; }
}
