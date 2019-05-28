/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import dao.CustomerDao;
import dao.OrderDao;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Customer;
import model.Order;

/**
 * REST Web Service
 *
 * @author William
 */
@Path("servico/customer")
public class CustomerApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoWS
     */
    public CustomerApi() {
    }

    /**
     * Retrieves representation of an instance of api.ServicoWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Customer> customers;
        CustomerDao customerDao = new CustomerDao();
        customers = customerDao.listarTodos();
        Gson gson = new Gson();
        return gson.toJson(customers);
    }
    /**
     * Retrieves representation of an instance of api.ServicoWS
     *
     * @param codigo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("servico/customer/porId/{codigo}")
    public String getClientePorCodigo(@PathParam("codigo") int codigo) {
        Customer customer;
        CustomerDao customerDao = new CustomerDao();
        customer = customerDao.pesquisarPorId(codigo);
        Gson gson = new Gson();
        return gson.toJson(customer);
    }
    
     /**
     * Retrieves representation of an instance of api.ServicoWS
     *
     * @param nome
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("servico/customer/porNome/{nome}")
    public String getClientePorNome(@PathParam("nome") String nome) {
        List<Customer> customers;
        CustomerDao customerDao = new CustomerDao();
        customers = customerDao.pesquisarPorNome(nome);
        Gson gson = new Gson();
        return gson.toJson(customers);
    }
    
    /**
     * Retrieves representation of an instance of api.ServicoWS
     *
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("servico/customer/pedidoCliente/{id}")
    public String getPedidoDeUmCliente(@PathParam("id") Integer id) {
        List<Order> orders;
        OrderDao orderDao = new OrderDao();
        orders = orderDao.pesquisarPedidosPorCliente(id);
        Gson gson = new Gson();
        return gson.toJson(orders);
    }

    /**
     * PUT method for updating or creating an instance of ServicoWS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
