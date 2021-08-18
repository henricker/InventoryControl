package controller;


import DAO.ProductDAO;
import model.Movement;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private ProductDAO productDAO;
    private Connection connection;

    public ProductController(Connection connection) {
        this.connection = connection;
        this.productDAO = new ProductDAO(this.connection);
    }

    public void save(Product product)  {
        try {
            this.productDAO.save(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> list()  {
        try {
            List<Product> listProducts = new ArrayList<Product>(this.productDAO.list());
            return listProducts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Integer id, String name, String description, Double cost_price, Double sale_price, Integer inventory_min, Integer category_id) {
        this.productDAO.update(id, name, description, cost_price, sale_price, inventory_min, category_id);
    }
    public Product show (Integer id) {
        return this.productDAO.show(id);
    }

    public void delete(Integer id) {
        this.productDAO.delete(id);
    }
}
