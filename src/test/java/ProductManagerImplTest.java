
import eetac.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.Assert.*;

public class ProductManagerImplTest {

    ProductManager pm;
    Producto producto1, producto2, producto3, producto4, producto5, producto6;

    LineaDePedido lp, lp2;

    @Before
    public void setUp(){
        pm = ProductManagerImpl.getInstance();
        this.pm = new ProductManagerImpl();

        pm.addUsuario("Willi");
        pm.addUsuario("Antenita");
        pm.addUsuario("Señora");

        producto1 = new Producto("Arma",100,5);
        producto2 = new Producto("Papel",200,6);
        producto3 = new Producto("Boli",150,3);
        producto4 = new Producto("Libreta",50,1);
        pm.addProducto("arma", 100, 5);
        pm.addProducto("papel", 200, 6);
        pm.addProducto("boli", 150, 50);
        pm.addProducto("libreta", 50, 1); /*por causas de la vida está así */

    }
    @After
    public void setDown(){
        pm=null;
    }

    @Test
    public void getInstance(){

    }

    @Test
    public void getProductosOrdenados(){
        List<Producto> productosOrdenados = this.pm.getProductosOrdenados();
        assertEquals(productosOrdenados.get(0).getNombre(),"Libreta");
        assertEquals(productosOrdenados.get(1).getNombre(),"Arma");
        assertEquals(productosOrdenados.get(2).getNombre(),"Boli");
        assertEquals(productosOrdenados.get(3).getNombre(),"Papel");


    }
    @Test
    public void realizarPedido(){
        LinkedList<LineaDePedido> lista = new LinkedList<LineaDePedido>();
        lp = new LineaDePedido(producto1, 4);
        lp2 = new LineaDePedido(producto2, 5);
        lista.add(lp);
        lista.add(lp2);
        Pedido pedido = new Pedido(lista);
        assertEquals(pedido.getListaPedido().size(), 2);



    }
}
