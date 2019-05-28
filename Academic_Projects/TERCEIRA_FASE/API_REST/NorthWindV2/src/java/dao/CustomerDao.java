package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author William
 */
public class CustomerDao extends Dao {

    public CustomerDao() {
        super();
    }
    
    public List<Customer> pesquisarPorNome(String termo) {
        String querySelectComTermo = "SELECT * FROM CUSTOMERS WHERE (first_name like ? or last_name like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Customer> lista = new ArrayList<>();
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setCompany(result.getString("company"));
                customer.setLastName(result.getString("last_name"));
                customer.setFirstName(result.getString("first_name"));
                customer.setJobTitle(result.getString("job_title"));
                customer.setBusinessPhone(result.getString("business_phone"));
                customer.setFaxNumber(result.getString("fax_number"));
                customer.setAdress(result.getString("address"));
                customer.setCity(result.getString("city"));
                customer.setStateProvince(result.getString("state_province"));
                customer.setZipPostalCode(result.getString("zip_postal_code"));
                customer.setCountryRegion(result.getString("country_region"));
                lista.add(customer);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    
    public List<Customer> listarTodos() {
        String querySelectComTermo = "SELECT * FROM CUSTOMERS";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            ResultSet result = stmt.executeQuery();
            List<Customer> lista = new ArrayList<>();
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setCompany(result.getString("company"));
                customer.setLastName(result.getString("last_name"));
                customer.setFirstName(result.getString("first_name"));
                customer.setJobTitle(result.getString("job_title"));
                customer.setBusinessPhone(result.getString("business_phone"));
                customer.setFaxNumber(result.getString("fax_number"));
                customer.setAdress(result.getString("address"));
                customer.setCity(result.getString("city"));
                customer.setStateProvince(result.getString("state_province"));
                customer.setZipPostalCode(result.getString("zip_postal_code"));
                customer.setCountryRegion(result.getString("country_region"));
                lista.add(customer);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    
    
    public Customer pesquisarPorId(int id) {
        String querySelectPorId = "SELECT * FROM CUSTOMERS WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectPorId);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setCompany(result.getString("company"));
                customer.setLastName(result.getString("last_name"));
                customer.setFirstName(result.getString("first_name"));
                customer.setJobTitle(result.getString("job_title"));
                customer.setBusinessPhone(result.getString("business_phone"));
                customer.setFaxNumber(result.getString("fax_number"));
                customer.setAdress(result.getString("address"));
                customer.setCity(result.getString("city"));
                customer.setStateProvince(result.getString("state_province"));
                customer.setZipPostalCode(result.getString("zip_postal_code"));
                customer.setCountryRegion(result.getString("country_region"));
                return customer;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    

}
