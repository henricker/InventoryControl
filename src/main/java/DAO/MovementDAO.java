package DAO;

import model.Movement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDAO {
    private Connection connection;

    public MovementDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Movement movement) {
        String sql = "INSERT INTO movement(product_id, quantity_movement, type) VALUES(?, ?, ?)";
        try(PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, movement.getProductId());
            ps.setInt(2, movement.getQuantityMovement());
            ps.setString(3, movement.getType());
            ps.execute();

            try(ResultSet solve = ps.getGeneratedKeys()) {
                while (solve.next()) {
                    movement.setId(solve.getInt(1));
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Movement> list() {
        String sql = "SELECT * FROM movement";
        List<Movement> listMovement = new ArrayList<>();

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.execute();

            try (ResultSet solve = ps.getResultSet()) {
                while (solve.next()) {
                    Integer id = solve.getInt("id");
                    Integer productId = solve.getInt("product_id");
                    Integer quantityMovement = solve.getInt("quantity_movement");
                    String type = solve.getString("type");
                    Double price = solve.getDouble("price");
                    Date dateMoviment = solve.getDate("date_movement");

                    listMovement.add(new Movement(id, productId, quantityMovement, Movement.TypesMovement.valueOf(type), price, dateMoviment));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listMovement;
    }
}
