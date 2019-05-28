package model;

/**
 *
 * @author William
 */
public class Product {

    private Integer supplierId;
    private Integer id;
    private String productCode;
    private String productName;
    private Double standardCost;
    private Double listPrice;
    private Integer reorderLevel;
    private Integer targetLevel;
    private String quantity_per_unity;
    private Integer discontinued;
    private Integer minimumReorderQuantity;
    private String category;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(Double standardCost) {
        this.standardCost = standardCost;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Integer getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(Integer targetLevel) {
        this.targetLevel = targetLevel;
    }

    public String getQuantity_per_unity() {
        return quantity_per_unity;
    }

    public void setQuantity_per_unity(String quantity_per_unity) {
        this.quantity_per_unity = quantity_per_unity;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Integer discontinued) {
        this.discontinued = discontinued;
    }

    public Integer getMinimumReorderQuantity() {
        return minimumReorderQuantity;
    }

    public void setMinimumReorderQuantity(Integer minimumReorderQuantity) {
        this.minimumReorderQuantity = minimumReorderQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "supplierId=" + supplierId + ", id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", standardCost=" + standardCost + ", listPrice=" + listPrice + ", reorderLevel=" + reorderLevel + ", targetLevel=" + targetLevel + ", quantity_per_unity=" + quantity_per_unity + ", discontinued=" + discontinued + ", minimumReorderQuantity=" + minimumReorderQuantity + ", category=" + category + '}';
    }

}
