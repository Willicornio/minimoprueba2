package eetac;
import org.graalvm.compiler.replacements.PEGraphDecoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Pedido {

    private List<LineaDePedido> listaPedido = null;
    Usuario usuario = null;
    private boolean pedidoRealizado = false;


    public Pedido (List<LineaDePedido> listaPedido){
        this.listaPedido = new LinkedList<LineaDePedido>(listaPedido);
    }
    public Pedido (){}

    public List<LineaDePedido> getListaPedido(){
        return this.listaPedido;
    }

    public void setListaPedido(List<LineaDePedido> pedido) {
        this.listaPedido = pedido;
    }


    public void addProducto(Producto producto, int num){

        this.listaPedido.add(new LineaDePedido(producto, num));
    }


    public boolean isPedidoRealizado(){
        return pedidoRealizado;
    }

    public void setPedidoRealizado(boolean pedidoRealizado) {
        this.pedidoRealizado = pedidoRealizado;
    }


    public void addUser (Usuario user){
        this.usuario = user;
    }

    public void getUsuario(){

        this.usuario=usuario;

    }

    public void setUsuario(Usuario usuario){

        this.usuario = usuario;

    }
}
