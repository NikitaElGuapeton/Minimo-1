package edu.upc.dsa.manager;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import java.util.List;

public interface ProductManager {
    // 1. Listado de productos ordenado (ascendentemente) por precio
    List<Producto> getProductosPorPrecio();

    // 2. Realizar un pedido
    void realizarPedido(Pedido pedido);

    // 3. Servir un pedido (en orden de llegada)
    Pedido servirPedido();

    // 4. Listado de pedidos de un usuario que ya hayan sido realizados
    List<Pedido> getPedidosPorUsuario(String idUsuario);

    // 5. Listado de productos ordenado (descendentemente) por número de ventas
    List<Producto> getProductosPorVentas();

    // Métodos extra para pruebas
    void addProductoAlCatalogo(Producto p);
    void clear();
}