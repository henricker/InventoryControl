package model;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double sale_price;
    private Double cost_price;
    private Integer inventory_min;
    private Integer categoryId;
    private Integer providerId;
    private Integer quantity;

    public Product(String name, String description, Double sale_price, Double cost_price, Integer categoryId, Integer providerId, Integer inventory_min) {
        this.name = name;
        this.description = description;
        this.sale_price = sale_price;
        this.cost_price = cost_price;
        this.categoryId = categoryId;
        this.inventory_min = inventory_min;
        this.providerId = providerId;
    }

    public Product(Integer id, String name, String description, Double sale_price, Double cost_price, Integer categoryId, Integer providerId, Integer quantity, Integer inventory_min) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sale_price = sale_price;
        this.cost_price = cost_price;
        this.categoryId = categoryId;
        this.providerId = providerId;
        this.quantity = quantity;
        this.inventory_min = inventory_min;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSale_price() {
        return sale_price;
    }

    public void setSale_price(Double sale_price) {
        this.sale_price = sale_price;
    }

    public Double getCost_price() {
        return cost_price;
    }

    public void setCost_price(Double cost_price) {
        this.cost_price = cost_price;
    }

    public Integer getInventory_min() {
        return inventory_min;
    }

    public void setInventory_min(Integer inventory_min) {
        this.inventory_min = inventory_min;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getSale_price(), product.getSale_price()) && Objects.equals(getCost_price(), product.getCost_price()) && Objects.equals(getInventory_min(), product.getInventory_min()) && Objects.equals(getCategoryId(), product.getCategoryId()) && Objects.equals(getProviderId(), product.getProviderId()) && Objects.equals(getQuantity(), product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getSale_price(), getCost_price(), getInventory_min(), getCategoryId(), getProviderId(), getQuantity());
    }

    @Override
    public String toString() {
        if(this.name.equals("Select"))
            return "Select";
        return "{ id: " + this.id + ", name: " + this.name + ", quantity: " + this.quantity + "}";
    }
}
