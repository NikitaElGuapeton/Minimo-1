package edu.upc.dsa.manager;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import java.util.*;

public class ProductManagerImpl implements ProductManager {

    // 1. Logger para las trazas
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    // 2. Patrón SINGLETON: Instancia única privada
    private static ProductManagerImpl instance;

    // 3. Estructuras de datos
    private List<Producto> listaProductos; // Catálogo
    private Queue<Pedido> colaPedidos;     // Pedidos pendientes
    private HashMap<String, Usuario> mapaUsuarios; // Usuarios del sistema

    // Constructor privado (Singleton)
    private ProductManagerImpl() {
        this.listaProductos = new ArrayList<>();
        this.colaPedidos = new LinkedList<>();
        this.mapaUsuarios = new HashMap<>();
    }

    // Métod. para obtener la instancia (Singleton)
    public static ProductManagerImpl getInstance() {
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }


    @Override
    public List<Producto> getProductosPorPrecio() {
        logger.info("Iniciando getProductosPorPrecio()");
        // Lógica pendiente de programar
        logger.info("Fin de getProductosPorPrecio()");
        return this.listaProductos;
    }

    @Override
    public void realizarPedido(Pedido pedido) {
        logger.info("Iniciando realizarPedido()");
        // Lógica pendiente de programar
        logger.info("Fin de realizarPedido()");
    }

    @Override
    public void servirPedido() {
        logger.info("Iniciando servirPedido()");
        // Lógica pendiente de programar
        logger.info("Fin de servirPedido()");
    }

    @Override
    public List<Pedido> getPedidosRealizadosPorUsuario(String idUsuario) {
        logger.info("Iniciando getPedidosRealizadosPorUsuario() para id: " + idUsuario);
        // Lógica pendiente de programar
        logger.info("Fin de getPedidosRealizadosPorUsuario()");
        return new ArrayList<>();
    }

    @Override
    public List<Producto> getProductosPorVentas() {
        logger.info("Iniciando getProductosPorVentas()");
        // Lógica pendiente de programar
        logger.info("Fin de getProductosPorVentas()");
        return this.listaProductos;
    }
}