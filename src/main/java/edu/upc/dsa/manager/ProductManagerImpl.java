package edu.upc.dsa.manager;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import org.apache.log4j.Logger;
import java.util.*;

public class ProductManagerImpl implements ProductManager {

    // Inicializamos el Logger
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    // --- 1. EL TRUCO SINGLETON ---
    // Guardamos la única instancia que va a existir de forma privada
    private static ProductManagerImpl instance;

    public static ProductManagerImpl getInstance() {
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }


    // --- NUESTRAS ESTRUCTURAS DE DATOS ---
    private List<Producto> listaProductos;
    private Queue<Pedido> colaPedidos;
    private HashMap<String, List<Pedido>> historialPedidosUsuario;

    // --- EL ÚNICO CONSTRUCTOR ---
    private ProductManagerImpl() {
        // Inicializamos las estructuras vacías
        this.listaProductos = new ArrayList<>();
        this.colaPedidos = new LinkedList<>();
        this.historialPedidosUsuario = new HashMap<>();
    }


    // Metodo extra para añadir productos al catálogo
    public void addProductoAlCatalogo(Producto p) {
        log.info("Añadiendo producto al catálogo: " + p.getNombre());
        this.listaProductos.add(p);
    }

    // Metodo extra para limpiar los datos
    public void clear() {
        this.listaProductos.clear();
        this.colaPedidos.clear();
        this.historialPedidosUsuario.clear();
    }



    // --- 2. LOS MÉTODOS DE LA INTERFAZ ---

    @Override
    public List<Producto> getProductosPorPrecio() {
        log.info("Llamada a getProductosPorPrecio()");

        // Creamos una copia para no desordenar la lista original
        List<Producto> productosOrdenados = new ArrayList<>(this.listaProductos);

        // Ordena ascendentemente por precio
        productosOrdenados.sort(Comparator.comparingDouble(Producto::getPrecio));

        log.info("Fin getProductosPorPrecio(). Retornando " + productosOrdenados.size() + " productos.");
        return productosOrdenados;
    }

    @Override
    public void realizarPedido(Pedido p) {
        log.info("Llamada a realizarPedido() con usuario: " + p.getIdUsuario());

        // Añadir a la cola de pedidos pendientes
        this.colaPedidos.add(p);

        log.info("Fin realizarPedido(). Pedidos pendientes en cola: " + this.colaPedidos.size());
    }

    @Override
    public Pedido servirPedido() {
        log.info("Llamada a servirPedido()");

        // poll() saca el primer pedido de la cola. Si está vacía, devuelve null.
        Pedido pedidoServido = this.colaPedidos.poll();

        if (pedidoServido == null) {
            log.error("¡No hay pedidos para servir en la cola!");
            return null;
        }

        // Procesar las ventas: Sumar +1 a las ventas de cada producto de este pedido
        for (Producto producto : pedidoServido.getListaProductos()) {
            int ventasActuales = producto.getNumeroVentas();
            producto.setNumeroVentas(ventasActuales + 1);
        }

        // Guardar en el historial del usuario
        String idUsuario = pedidoServido.getIdUsuario();

        // Si el usuario no tiene historial aún, le creamos una lista vacía
        if (!this.historialPedidosUsuario.containsKey(idUsuario)) {
            this.historialPedidosUsuario.put(idUsuario, new ArrayList<>());
        }
        // Añadimos el pedido servido a su historial
        this.historialPedidosUsuario.get(idUsuario).add(pedidoServido);

        log.info("Fin servirPedido(). Se ha servido el pedido del usuario: " + idUsuario);
        return pedidoServido;
    }

    @Override
    public List<Pedido> getPedidosPorUsuario(String idUsuario) {
        log.info("Llamada a getPedidosPorUsuario() para usuario: " + idUsuario);

        List<Pedido> historial = this.historialPedidosUsuario.get(idUsuario);

        if (historial == null) {
            log.warn("El usuario " + idUsuario + " no tiene pedidos o no existe.");
            return new ArrayList<>(); // Devolvemos lista vacía para evitar errores
        }

        log.info("Fin getPedidosPorUsuario(). Pedidos encontrados: " + historial.size());
        return historial;
    }

    @Override
    public List<Producto> getProductosPorVentas() {
        log.info("Llamada a getProductosPorVentas()");

        List<Producto> productosOrdenados = new ArrayList<>(this.listaProductos);

        // Ordenar descendentemente (de mayor a menor) por ventas
        productosOrdenados.sort((p1, p2) -> Integer.compare(p2.getNumeroVentas(), p1.getNumeroVentas()));

        log.info("Fin getProductosPorVentas(). Retornando productos ordenados.");
        return productosOrdenados;
    }
}