package eetac;
import java.util.LinkedList;

public class Usuario {
    String nombre;
    private LinkedList<Pedido> pedidoLista;

    public Usuario (String nombre){
        this.nombre = nombre;
        this.pedidoLista = new LinkedList<Pedido>();
    }

    public Usuario (){ }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addPedido(Pedido p){
        this.pedidoLista.add(p);
    }

    public LinkedList<Pedido> consultaPedidos(){

        return this.pedidoLista;

    }

}
