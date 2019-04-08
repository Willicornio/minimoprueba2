package eetac;
import org.apache.log4j.Logger;

import java.util.*;

import java.util.List;


/*Para el logger:
 primero te vas al pom y lo copias, luego te vas a la carpeta del proyecto del profe, copias el archivo
  log4j (está en source-main-recourses, y lo pegas en tu recurses)
  Ejemplo más abajo
  */

public class ProductManagerImpl implements ProductManager {

    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName()); //-> pones eso
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


    public void realizarPedido(String user, Pedido p) throws UsuarioNotFound{//---> ejemplo log
        log.info("Realizar pedido");//Poner info al inicio
        Usuario u = this.usuarios.get(user);
        String nombre;
        nombre = u.getNombre();
        if (u!=null){

            u.addPedido(p);
            log.info("Pedido realizado con exito al usuario" + nombre); //--> Ojo con esto, solo ponlo cuando la función es void, si fuese por ejemplo
            //la función de getUsuario, que devuelve un Usuario, se vuelve loquisimo y no va eso.

        }
        else{
            log.error("usuario no encontrado");  // para un error se pone así
            throw new UsuarioNotFound();
        }
    }
    public void addUsuario(String user){
        log.info("Añadiendo usuario"); //-> eso al principio de cada función.
        usuarios.put(user, new Usuario(user));
        Usuario u = usuarios.get(user);
        String nombre= u.getNombre();
        log.info("añadido el usuario" + nombre);
    }
    public void addProducto(String nombre, int ventas, double precio ){

        Producto producto = new Producto(nombre, precio, ventas);
        this.productos.add(producto);
    }


    public Usuario getUsuario (String user){

        Usuario b = this.usuarios.get(user);
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
        log.info("hola");
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



     public void servirPedido(){
         Pedido p = null;

         p = this.historialPedidos.poll();

         p.setPedidoRealizado(true);



         //p.isPedidoRealizado(true);

     }


}




