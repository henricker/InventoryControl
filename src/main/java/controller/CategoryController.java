package controller;

import model.Category;
import DAO.CategoryDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryController {

    private Connection connection;
    private CategoryDAO categoryDAO;

    public CategoryController(Connection connection) {
        this.connection = connection;
        this.categoryDAO = new CategoryDAO(this.connection);
    }

    public List<Category> list() {
        try {
            List<Category> categories = this.categoryDAO.list();
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Category category) {
        try {
            this.categoryDAO.add(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            this.categoryDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, String name) {
        this.categoryDAO.update(name, id);
    }

}
