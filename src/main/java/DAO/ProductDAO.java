package DAO;

import model.Movement;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, description, cost_price, sale_price, inventory_min, category_id, provider_id) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getCost_price());
            ps.setDouble(4, product.getSale_price());
            ps.setInt(5, product.getInventory_min());
            ps.setInt(6, product.getCategoryId());
            ps.setInt(7, product.getProviderId());
            ps.execute();

            try(ResultSet solve = ps.getGeneratedKeys()) {
                while(solve.next()) {
                    product.setId(solve.getInt(1));
                }
            }
        }
    }

    public List<Product> list() throws SQLException {
        String sql = "SELECT * FROM product";
        List<Product> listProduct = new ArrayList<>();

        try(PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try(ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    String name = solve.getString("name");
                    String description = solve.getString("description");
                    Double cost_price = solve.getDouble("cost_price");
                    Double sale_price = solve.getDouble("sale_price");
                    Integer inventory_min = solve.getInt("inventory_min");
                    Integer quantity = solve.getInt("quantity");
                    Integer category_id = solve.getInt("category_id");
                    Integer provider_id = solve.getInt("provider_id");
                    listProduct.add(new Product(id, name, description, sale_price, cost_price, category_id, provider_id, quantity, inventory_min));
                }
            }
        }
        return listProduct;
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM product WHERE id = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Product deleted with id: " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, String name, String description, Double cost_price, Double sale_price, Integer inventory_min, Integer category_id) {
        String sql = "UPDATE product SET name = ?, description = ?, cost_price = ?, sale_price = ?, inventory_min = ?, category_id = ? WHERE id = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, cost_price);
            ps.setDouble(4, sale_price);
            ps.setInt(5, inventory_min);
            ps.setInt(6, category_id);
            ps.setInt(7, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Updated product with id: " + id);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Product show (Integer idSearch) {
        String sql = "SELECT * FROM product WHERE id = ?";
        Product product = null;
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, idSearch);
            ps.execute();

            try (ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    String name = solve.getString("name");
                    String description = solve.getString("description");
                    Double cost_price = solve.getDouble("cost_price");
                    Double sale_price = solve.getDouble("sale_price");
                    Integer inventory_min = solve.getInt("inventory_min");
                    Integer quantity = solve.getInt("quantity");
                    Integer category_id = solve.getInt("category_id");
                    Integer provider_id = solve.getInt("provider_id");

                    product = new Product(id, name, description, sale_price, cost_price, category_id, provider_id, quantity, inventory_min);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

}