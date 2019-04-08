package service;
import eetac.ProductManager;
import eetac.ProductManagerImpl;
import eetac.*;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;

import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;

import javax.ws.rs.core.GenericEntity;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;



    @Api (value = "/pedidos", description = "Servicio de pedidos")
    @Path("/pedidos")
    public class ProductManagerService {
        final static Logger log = Logger.getLogger(ProductManagerService.class.getName());
        private ProductManager pm; //La façada

        public ProductManagerService() {
            this.pm = ProductManagerImpl.getInstance(); //instancia de la implentación
        }

//ejemplo para un get:

        @GET
        @ApiOperation(value = "Lista productos")
        //posibles respuestas que darás :
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "exito")

        })
        //camino url:
        @Path("/{productoorden}")
        //siempre es así:
        @Produces(MediaType.APPLICATION_JSON)
        public Response productosOrdenados() {  //->Te inventas una respuesta llamada así, como es un get no pide nada
            List<Producto> listaproducto = new ArrayList<>();  //->creas lista
            listaproducto = this.pm.getProductosOrdenados(); // -> copias la lista ordenada en tu lista
            GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(listaproducto) {//->esto siempre es así, no sé porque
            };
            return Response.status(201).entity(entity).build(); //-> haces un build con la respuesta y la lista
        }

//ejemplo post:
        @POST
        @ApiOperation(value = "Agregar prodcuto")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "exito"),
                @ApiResponse(code = 404, message = "fatal") //En este caso tenemos 2 respuestas

        })
        @Path("/{agregarpedido}")
        @Produces(MediaType.APPLICATION_JSON)  //como un get hasta aquí
        //El post necesita que le des algo, en este caso un producto p
        public Response agregarProducto(Producto p) {
            try {
                String nombre = p.getNombre();//se sacan los parametros que tiene el objeto p, ojo, eso se hace porque la función addProducto es :
                // public void addProducto (String nombre, double precio, int numerovntas{}, si fuese public void addProducto (Producto p) {}
                //no haria falta coger los valores
                double precio = p.getPrecio();
                int ventas = p.getNumeroVentas();

                pm.addProducto(nombre, ventas, precio);//->si fuese el segundo caso, se pondría addProdcuto(p)
                return Response.status(201).build();

            } catch (Exception e) { //-> tenemos una expeción, siempre asi
                e.printStackTrace();
                return Response.status(404).build();//-> solo enviarás el status, nada más, es un post
            }


        }
    }



