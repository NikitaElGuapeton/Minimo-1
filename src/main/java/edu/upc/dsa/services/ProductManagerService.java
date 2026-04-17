package edu.upc.dsa.services;

import edu.upc.dsa.manager.ProductManager;
import edu.upc.dsa.manager.ProductManagerImpl;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;

// Estas son las librerías estándar que usa la UPC (JAX-RS)
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

// 1. LA RUTA PRINCIPAL DE ESTE SERVICIO
@Path("/tienda")
public class ProductManagerService {

    // Conectamos el camarero con el cocinero (nuestro Singleton)
    private ProductManager manager;

    public ProductManagerService() {
        this.manager = ProductManagerImpl.getInstance();

        // Metemos unos datos de prueba por si el catálogo está vacío al arrancar el servidor
        if (manager.getProductosPorPrecio().isEmpty()) {
            manager.addProductoAlCatalogo(new Producto("Bocadillo", 4.5));
            manager.addProductoAlCatalogo(new Producto("Coca-Cola", 2.0));
        }
    }

    // --- OPERACIÓN 1: Obtener productos por precio ---
    @GET
    @Path("/productos/precio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosPorPrecio() {
        List<Producto> productos = this.manager.getProductosPorPrecio();
        // Truco de Java para devolver listas por internet
        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(200).entity(entity).build(); // 200 significa "OK"
    }

    // --- OPERACIÓN 2: Realizar un pedido ---
    @POST
    @Path("/pedidos/nuevo")
    @Consumes(MediaType.APPLICATION_JSON) // Consume porque el usuario nos envía el pedido
    public Response realizarPedido(Pedido p) {
        this.manager.realizarPedido(p);
        return Response.status(201).build(); // 201 significa "Creado"
    }

    // --- OPERACIÓN 3: Servir un pedido ---
    @GET // Usamos GET o PUT para procesar acciones
    @Path("/pedidos/servir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response servirPedido() {
        Pedido pedidoServido = this.manager.servirPedido();
        if (pedidoServido == null) {
            return Response.status(404).build(); // 404 significa "No encontrado" (cola vacía)
        }
        return Response.status(200).entity(pedidoServido).build();
    }

    // --- OPERACIÓN 4: Obtener pedidos de un usuario ---
    // Usamos {id} para que la URL sea dinámica, ej: /tienda/pedidos/usuario/Usuario1
    @GET
    @Path("/pedidos/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidosPorUsuario(@PathParam("id") String idUsuario) {
        List<Pedido> historial = this.manager.getPedidosPorUsuario(idUsuario);
        GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(historial) {};
        return Response.status(200).entity(entity).build();
    }

    // --- OPERACIÓN 5: Obtener productos por ventas ---
    @GET
    @Path("/productos/ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosPorVentas() {
        List<Producto> productos = this.manager.getProductosPorVentas();
        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(200).entity(entity).build();
    }
}