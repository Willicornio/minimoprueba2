package eetac;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public interface ProductManager {

    List<Producto> getProductosOrdenados();
    //Listado de productos ordenado ascendentemente por precio

    void realizarPedido(String user, Pedido p);
    //Realizar un pedido que podrá tener varios productos por un usuario en concreto

    void servirPedido();

    LinkedList<Pedido> getPedidosdeUser(String user);

    List<Producto> getProductosTop();

    void addUsuario (String user);
    void addProducto (String nombre, int ventas, double precio);
    Queue<Pedido> getHistorial();
    Usuario getUsuario(String user);

}
