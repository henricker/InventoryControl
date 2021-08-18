package controller;

import DAO.MovementDAO;
import model.Movement;

import java.sql.Connection;
import java.util.List;

public class MovementController {
    public Connection connection;
    public MovementDAO movementDAO;

    public MovementController(Connection connection) {
        this.connection = connection;
        this.movementDAO = new MovementDAO(connection);
    }

    public void save(Movement movement) {
        this.movementDAO.save(movement);
    }

    public List<Movement> list () {
        return this.movementDAO.list();
    }
}
