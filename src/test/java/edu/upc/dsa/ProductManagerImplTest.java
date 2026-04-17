package edu.upc.dsa;

import edu.upc.dsa.manager.ProductManagerImpl;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerImplTest {

    // Nuestro gestor
    ProductManagerImpl manager;

    // 1. @Before (setUp): Se ejecuta SIEMPRE ANTES de cada test. Sirve para preparar los datos.
    @Before
    public void setUp() {
        // Obtenemos la instancia del Singleton
        manager = ProductManagerImpl.getInstance();

        // Inicializamos la estructura de datos metiendo algunos productos en el catálogo
        manager.addProductoAlCatalogo(new Producto("Bocadillo", 4.5));
        manager.addProductoAlCatalogo(new Producto("Coca-Cola", 2.0));
    }

    // 2. @After (tearDown): Se ejecuta SIEMPRE DESPUÉS de cada test. Sirve para limpiar.
    @After
    public void tearDown() {
        // Liberamos los recursos (vaciamos las listas) para que un test no afecte a otro
        manager.clear();
    }

    // 3. Test para realizar un pedido
    @Test
    public void testRealizarPedido() {
        // Preparamos un pedido falso
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Bocadillo", 4.5));
        Pedido miPedido = new Pedido("Usuario1", lista);

        // Hacemos el pedido
        manager.realizarPedido(miPedido);

        // Para comprobar si ha funcionado, intentamos servirlo.
        // Si el usuario del pedido servido es "Usuario1", ¡ha funcionado!
        Pedido pedidoServido = manager.servirPedido();
        Assert.assertEquals("Usuario1", pedidoServido.getIdUsuario());
    }

    // 4. Test para servir un pedido
    @Test
    public void testServirPedido() {
        // Primero, alguien tiene que hacer un pedido
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Coca-Cola", 2.0));
        manager.realizarPedido(new Pedido("Usuario2", lista));

        // Ahora, lo servimos
        Pedido pedidoServido = manager.servirPedido();

        // Comprobaciones (Asserts) de que tot es correcto
        Assert.assertNotNull(pedidoServido); // Comprueba que no sea nulo
        Assert.assertEquals("Usuario2", pedidoServido.getIdUsuario()); // Comprueba que es de Usuario2

        // Comprobamos que se ha guardado en el historial del usuario
        List<Pedido> historial = manager.getPedidosPorUsuario("Usuario2");
        Assert.assertEquals(1, historial.size()); // El historial debe tener 1 pedido
    }
}