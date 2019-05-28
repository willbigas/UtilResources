/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import dao.ProductDao;
import dao.SupplierDao;
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
import model.Product;
import model.Supplier;

/**
 * REST Web Service
 *
 * @author William
 */
@Path("servico/supplier")
public class SupplierApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoWS
     */
    public SupplierApi() {
    }

    /**
     * Retrieves representation of an instance of api.ServicoWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Supplier> suppliers;
        SupplierDao supplierDao = new SupplierDao();
        suppliers = supplierDao.pesquisarTodos();
        Gson gson = new Gson();
        return gson.toJson(suppliers);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("servico/supplier/produtoFornecedor/{id}")
    public String getProdutoDeUmFornecedor(@PathParam("id") Integer id) {
        List<Product> products;
        ProductDao productDao = new ProductDao();
        products = productDao.pesquisarProdutosPorFornecedor(id);
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
