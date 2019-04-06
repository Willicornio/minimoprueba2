package eetac;
public class LineaDePedido extends Pedido{

    private int num;
    private Producto p;


    protected LineaDePedido ( Producto p, int num){
        this.p = p;
        this.num = num;

    }
    public int getNum(){
        return num;
    }
    public Producto getProducto(){
        return p;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setProducto(Producto p){
        this.p = p;
    }
}
