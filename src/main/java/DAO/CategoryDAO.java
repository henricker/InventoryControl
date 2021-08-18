package DAO;


import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(Category category) throws SQLException {
        String sql = "INSERT INTO category (name) VALUES(?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.execute();

            try (ResultSet solve = ps.getGeneratedKeys()) {
                while (solve.next()) {
                    category.setId(solve.getInt(1));
                }
            }
        }
    }

    public List<Category> list() throws SQLException {
        String sql = "SELECT * FROM category";
        List<Category> listCategory = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try (ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    String name = solve.getString("name");

                    listCategory.add(new Category(id, name));
                }
            }
        }
        return listCategory;
    }

    public void delete (Integer id) throws SQLException {
        String sql = "DELETE FROM category WHERE id = ?";
        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Category deleted with id: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String name, Integer id) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Update category with id: " + id);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Category> listWithProducts() throws SQLException {
//        Map<Integer, Category> categories = new TreeMap();
//
//        String sql =
//                "SELECT category.id, category.name, product.id, product.name, product.description " +
//                        "FROM category INNER JOIN product ON product.category_id = category.id;";
//
//        try(PreparedStatement ps = this.connection.prepareStatement(sql)) {
//            ps.execute();
//
//            try(ResultSet solve = ps.getResultSet()) {
//                while(solve.next()) {
//                    if(!categories.containsKey(solve.getInt(1)))
//                        categories.put(solve.getInt(1), new Category(solve.getInt(1), solve.getString(2)));
//                    categories.get(solve.getInt(1)).addProduct(new Product(solve.getInt(3), solve.getString(4), solve.getString(5), solve.getDouble(6), solve.getInt(3), solve.getInt(7)));
//                }
//            }
//        }
//        return List.copyOf(categories.values());
//    }
}
