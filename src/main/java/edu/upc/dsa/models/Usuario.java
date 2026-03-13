package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private List<Pedido> pedidosServidos; // Su historial de pedidos

    public Usuario() {}

    public Usuario(String idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.pedidosServidos = new ArrayList<>(); // Empieza con el historial vacío
    }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Pedido> getPedidosServidos() { return pedidosServidos; }
    public void setPedidosServidos(List<Pedido> pedidosServidos) { this.pedidosServidos = pedidosServidos; }
}
