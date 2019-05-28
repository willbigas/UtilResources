package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.Customer;
import model.Order;
import model.Product;
import model.Supplier;

/**
 *
 * @author William
 */
public class ProductDao extends Dao {

    public ProductDao() {
        super();
    }

    public List<Product> pesquisarProdutosPorFornecedor(Supplier supplier) {
        String querySelectComTermo = "SELECT * FROM PRODUCTS WHERE supplier_ids = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, supplier.getId());
            ResultSet result = stmt.executeQuery();
            List<Product> lista = new ArrayList<>();
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setSupplierId(result.getInt("supplier_ids"));
                product.setProductCode(result.getString("product_code"));
                product.setProductName(result.getString("product_name"));
                product.setStandardCost(result.getDouble("standard_cost"));
                product.setListPrice(result.getDouble("list_price"));
                product.setReorderLevel(result.getInt("reorder_level"));
                product.setTargetLevel(result.getInt("target_level"));
                product.setQuantity_per_unity(result.getString("quantity_per_unit"));
                product.setDiscontinued(result.getInt("discontinued"));
                product.setMinimumReorderQuantity(result.getInt("minimum_reorder_quantity"));
                product.setCategory(result.getString("category"));
                lista.add(product);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    
     public List<Product> pesquisarProdutosPorFornecedor(int id) {
        String querySelectComTermo = "SELECT * FROM PRODUCTS WHERE supplier_ids = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            List<Product> lista = new ArrayList<>();
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setSupplierId(result.getInt("supplier_ids"));
                product.setProductCode(result.getString("product_code"));
                product.setProductName(result.getString("product_name"));
                product.setStandardCost(result.getDouble("standard_cost"));
                product.setListPrice(result.getDouble("list_price"));
                product.setReorderLevel(result.getInt("reorder_level"));
                product.setTargetLevel(result.getInt("target_level"));
                product.setQuantity_per_unity(result.getString("quantity_per_unit"));
                product.setDiscontinued(result.getInt("discontinued"));
                product.setMinimumReorderQuantity(result.getInt("minimum_reorder_quantity"));
                product.setCategory(result.getString("category"));
                lista.add(product);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
     
     public List<Product> pesquisarTodos() {
        String querySelectComTermo = "SELECT * FROM PRODUCTS";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            ResultSet result = stmt.executeQuery();
            List<Product> lista = new ArrayList<>();
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setSupplierId(result.getInt("supplier_ids"));
                product.setProductCode(result.getString("product_code"));
                product.setProductName(result.getString("product_name"));
                product.setStandardCost(result.getDouble("standard_cost"));
                product.setListPrice(result.getDouble("list_price"));
                product.setReorderLevel(result.getInt("reorder_level"));
                product.setTargetLevel(result.getInt("target_level"));
                product.setQuantity_per_unity(result.getString("quantity_per_unit"));
                product.setDiscontinued(result.getInt("discontinued"));
                product.setMinimumReorderQuantity(result.getInt("minimum_reorder_quantity"));
                product.setCategory(result.getString("category"));
                lista.add(product);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

//    public Customer pesquisarPorId(int id) {
//        String querySelectPorId = "SELECT * FROM CUSTOMERS WHERE ID = ?";
//        try {
//            PreparedStatement stmt = conexao.prepareStatement(querySelectPorId);
//            stmt.setInt(1, id);
//            ResultSet result = stmt.executeQuery();
//            if (result.next()) {
//                Customer customer = new Customer();
//                customer.setId(result.getInt("id"));
//                customer.setCompany(result.getString("company"));
//                customer.setLastName(result.getString("last_name"));
//                customer.setFirstName(result.getString("first_name"));
//                customer.setEmailAdress(result.getString("email_address"));
//                customer.setJobTitle(result.getString("job_title"));
//                customer.setBusinessPhone(result.getString("business_phone"));
//                customer.setHomePhone(result.getString("home_phone"));
//                customer.setMobilePhone(result.getString("mobile_phone"));
//                customer.setFaxNumber(result.getString("fax_number"));
//                customer.setAdress(result.getString("address"));
//                customer.setCity(result.getString("city"));
//                customer.setStateProvince(result.getString("state_province"));
//                customer.setZipPostalCode(result.getString("zip_postal_code"));
//                customer.setCountryRegion(result.getString("country_region"));
//                customer.setWebPage(result.getString("web_page"));
//                customer.setNotes(result.getString("notes"));
//                return customer;
//            } else {
//                return null;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//    }

}
