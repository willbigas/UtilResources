/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import dao.CustomerDao;
import dao.ProductDao;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.Product;

/**
 * REST Web Service
 *
 * @author William
 */
@Path("servico/product")
public class ProductApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoWS
     */
    public ProductApi() {
    }

    /**
     * Retrieves representation of an instance of api.ServicoWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Product> products;
        ProductDao productDao = new ProductDao();
        products = productDao.pesquisarTodos();
        Gson gson = new Gson();
        return gson.toJson(products);
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
