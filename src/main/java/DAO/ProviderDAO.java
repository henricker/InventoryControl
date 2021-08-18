package DAO;

import model.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProviderDAO {
    private Connection connection;

    public ProviderDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(Provider provider) throws SQLException {
        String sql = "INSERT INTO provider (name, cnpj) VALUES(?, ?)";
        try (PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, provider.getName());
            ps.setString(2, provider.getCnpj());
            ps.execute();

            try (ResultSet solve = ps.getGeneratedKeys()) {
                while (solve.next()) {
                    provider.setId(solve.getInt(1));
                }
            }
        }
    }

    public List<Provider> list() throws SQLException {
        String sql = "SELECT * FROM provider";
        List<Provider> listProvider = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try (ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    String name = solve.getString("name");
                    String cnpj = solve.getString("cnpj");

                    listProvider.add(new Provider(id, name, cnpj));
                }
            }
        }
        return listProvider;
    }

    public void delete (Integer id) throws SQLException {
        String sql = "DELETE FROM provider WHERE id = ?";
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

    public void update(String name, String cnpj, Integer id) {
        String sql = "UPDATE provider SET name = ?, cnpj = ? WHERE id = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, cnpj);
            ps.setInt(3, id);
            ps.execute();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                while(rs.next()) {
                    System.out.println("Update provider with id: " + id);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
