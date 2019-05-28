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

/**
 *
 * @author William
 */
public class OrderDao extends Dao {

    public OrderDao() {
        super();
    }

    public List<Order> pesquisarPedidosPorCliente(Customer customer) {
        String querySelectComTermo = "SELECT * FROM ORDERS WHERE customer_id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, customer.getId());
            ResultSet result = stmt.executeQuery();
            List<Order> lista = new ArrayList<>();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setEmployeeId(result.getInt("employee_id"));
                order.setCustomerId(result.getInt("customer_id"));
                order.setOrderDate(result.getTimestamp("order_date").toLocalDateTime());
                order.setShipperId(result.getInt("shipper_id"));
                order.setShipName(result.getString("ship_name"));
                order.setShipAdress(result.getString("ship_address"));
                order.setShipCity(result.getString("ship_city"));
                order.setShipStateProvince(result.getString("ship_state_province"));
                order.setShipZipPostalCode(result.getString("ship_zip_postal_code"));
                order.setShipCountryRegion(result.getString("ship_country_region"));
                order.setShippingFee(result.getDouble("shipping_fee"));
                order.setTaxes(result.getDouble("taxes"));
                order.setPaymentType(result.getString("payment_type"));
                order.setNote(result.getString("notes"));
                order.setTaxRate(result.getDouble("tax_rate"));
                order.setTaxStatusId(result.getInt("tax_status_id"));
                order.setStatusId(result.getInt("status_id"));
                lista.add(order);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    
    public List<Order> pesquisarPedidosPorCliente(int codigoCliente) {
        String querySelectComTermo = "SELECT * FROM ORDERS WHERE customer_id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, codigoCliente);
            ResultSet result = stmt.executeQuery();
            List<Order> lista = new ArrayList<>();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt("id"));
                order.setEmployeeId(result.getInt("employee_id"));
                order.setCustomerId(result.getInt("customer_id"));
                order.setOrderDate(result.getTimestamp("order_date").toLocalDateTime());
                order.setShipperId(result.getInt("shipper_id"));
                order.setShipName(result.getString("ship_name"));
                order.setShipAdress(result.getString("ship_address"));
                order.setShipCity(result.getString("ship_city"));
                order.setShipStateProvince(result.getString("ship_state_province"));
                order.setShipZipPostalCode(result.getString("ship_zip_postal_code"));
                order.setShipCountryRegion(result.getString("ship_country_region"));
                order.setShippingFee(result.getDouble("shipping_fee"));
                order.setTaxes(result.getDouble("taxes"));
                order.setPaymentType(result.getString("payment_type"));
                order.setNote(result.getString("notes"));
                order.setTaxRate(result.getDouble("tax_rate"));
                order.setTaxStatusId(result.getInt("tax_status_id"));
                order.setStatusId(result.getInt("status_id"));
                lista.add(order);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
