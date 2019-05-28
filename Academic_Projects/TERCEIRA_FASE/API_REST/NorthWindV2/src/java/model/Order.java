package model;

import java.time.LocalDateTime;


/**
 *
 * @author William
 */
public class Order {
    
    private Integer id;
    private Integer employeeId;
    private Integer customerId;
    private LocalDateTime orderDate;
    private LocalDateTime shippedDate;
    private Integer shipperId;
    private String shipName;
    private String shipAdress;
    private String shipCity;
    private String shipStateProvince;
    private String shipZipPostalCode;
    private String shipCountryRegion;
    private Double shippingFee;
    private Double taxes;
    private String paymentType;
    private LocalDateTime paidDate;
    private String note;
    private Double taxRate;
    private Integer taxStatusId;
    private Integer statusId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAdress() {
        return shipAdress;
    }

    public void setShipAdress(String shipAdress) {
        this.shipAdress = shipAdress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipStateProvince() {
        return shipStateProvince;
    }

    public void setShipStateProvince(String shipStateProvince) {
        this.shipStateProvince = shipStateProvince;
    }

    public String getShipZipPostalCode() {
        return shipZipPostalCode;
    }

    public void setShipZipPostalCode(String shipZipPostalCode) {
        this.shipZipPostalCode = shipZipPostalCode;
    }

    public String getShipCountryRegion() {
        return shipCountryRegion;
    }

    public void setShipCountryRegion(String shipCountryRegion) {
        this.shipCountryRegion = shipCountryRegion;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getTaxStatusId() {
        return taxStatusId;
    }

    public void setTaxStatusId(Integer taxStatusId) {
        this.taxStatusId = taxStatusId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", employeeId=" + employeeId + ", customerId=" + customerId + ", orderDate=" + orderDate + ", shippedDate=" + shippedDate + ", shipperId=" + shipperId + ", shipName=" + shipName + ", shipAdress=" + shipAdress + ", shipCity=" + shipCity + ", shipStateProvince=" + shipStateProvince + ", shipZipPostalCode=" + shipZipPostalCode + ", shipCountryRegion=" + shipCountryRegion + ", shippingFee=" + shippingFee + ", taxes=" + taxes + ", paymentType=" + paymentType + ", paidDate=" + paidDate + ", note=" + note + ", taxRate=" + taxRate + ", taxStatusId=" + taxStatusId + ", statusId=" + statusId + '}';
    }
    
    
    
    
}
