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
public class SupplierDao extends Dao {

    public SupplierDao() {
        super();
    }

     
     public List<Supplier> pesquisarTodos() {
        String querySelectComTermo = "SELECT * FROM SUPPLIERS";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            ResultSet result = stmt.executeQuery();
            List<Supplier> lista = new ArrayList<>();
            while (result.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(result.getInt("id"));
                supplier.setCompany(result.getString("company"));
                supplier.setLastName(result.getString("last_name"));
                supplier.setFirstName(result.getString("first_name"));
                supplier.setJobTitle(result.getString("job_title"));
                lista.add(supplier);
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
