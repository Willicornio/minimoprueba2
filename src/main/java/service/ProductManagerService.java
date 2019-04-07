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
        private ProductManager pm;

        public ProductManagerService() {
            this.pm = ProductManagerImpl.getInstance();
        }


        @GET
        @ApiOperation(value = "Lista productos")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "exito")

        })
        @Path("/{productoorden}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response productosOrdenados() {
            List<Producto> listaproducto = new ArrayList<>();
            listaproducto = this.pm.getProductosOrdenados();
            GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(listaproducto) {
            };
            return Response.status(201).entity(entity).build();
        }



        @POST
        @ApiOperation(value = "Realizar pedido")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "exito")

        })
        @Path("/{realizarpedido}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response realizarPedido(@PathParam("user") String user, Pedido p){

            pm.realizarPedido(user, p);
            return Response.status(201).build();

        }

        @POST
        @ApiOperation(value = "Agregar prodcuto")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "exito")

        })
        @Path("/{agregarpedido}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response agregarProducto( Producto p){
            String nombre = p.getNombre();
            double precio = p.getPrecio();
            int ventas = p.getNumeroVentas();

            pm.addProducto(nombre, ventas, precio);
            return Response.status(201).build();

        }


    }



