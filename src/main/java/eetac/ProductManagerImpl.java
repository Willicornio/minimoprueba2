package eetac;
import java.util.*;

import java.util.List;

import java.util.concurrent.PriorityBlockingQueue;
import sun.awt.image.ImageWatched;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    public static ProductManager instance;
    private List<Producto> productos;
    private LinkedList<Pedido> pedidos;
    private HashMap<String, Usuario> usuarios;
    private Queue<Pedido> historialPedidos = new LinkedList<Pedido>();




    public static ProductManager getInstance(){
        if(instance == null) instance = new ProductManagerImpl();
        return instance;
    }

    public ProductManagerImpl(){
        productos = new ArrayList<Producto>();
        pedidos = new LinkedList<Pedido>();
        usuarios = new HashMap<String, Usuario>();
    }

    public void addUsuario(String user){
        usuarios.put(user, new Usuario(user));
    }
    public void addProducto(Producto producto){
        this.productos.add(producto);
    }


    public Usuario getUsuario (String user){
        return this.usuarios.get(user);
    }

    public Queue<Pedido> getHistorial (){

        return  this.historialPedidos;

    }

    public List<Producto> getProductosOrdenados(){
        List<Producto> lista = new ArrayList<Producto>(this.productos);
//ascedente
        Collections.sort(lista, new Comparator<Producto>(){
            public int compare( Producto p1, Producto p2){
                return  (int)(p1.getPrecio()-p2.getPrecio());
            }
        });
        return lista;
    }

    public List<Producto> getProductosTop(){
        List<Producto> lista = new ArrayList<Producto>(this.productos);
        Collections.sort(lista, new Comparator<Producto>(){
            //descendente por número de ventas
            public int compare(Producto p1, Producto p2){
                return (int) ((-1)*(p1.getNumeroVentas()-p2.getNumeroVentas()));
            }
        });
        return lista;
    }

    public LinkedList<Pedido> getPedidosdeUser(String user){
        Usuario u = this.usuarios.get(user);
        LinkedList<Pedido> listapedidos = new LinkedList<Pedido>();
        for (Pedido pedido: u.consultaPedidos() ) {
            if (pedido.isPedidoRealizado()) {
                listapedidos.add(pedido);
            } else {
            }

        }
        return listapedidos;

        }

     public void realizarPedido(String user, Pedido p){
        Usuario u = this.usuarios.get(user);
        if (u!=null){
            u.addPedido(p);

        }
        else{
            this.usuarios.put(user, new Usuario(user));
            u = this.usuarios.get(user);
            u.addPedido(p);
        }
     }

     public void servirPedido(){
         Pedido p = null;

         p = this.historialPedidos.poll();

         p.setPedidoRealizado(true);



         //p.isPedidoRealizado(true);

     }


}




