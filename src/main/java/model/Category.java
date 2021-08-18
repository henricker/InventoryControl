package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private Integer id;
    private String name;

    private List<Product> products = new ArrayList<>();

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getId(), category.getId()) && Objects.equals(getName(), category.getName()) && Objects.equals(products, category.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), products);
    }
}
