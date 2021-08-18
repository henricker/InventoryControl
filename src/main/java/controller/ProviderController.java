package controller;


import DAO.ProviderDAO;
import model.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProviderController {

    private Connection connection;
    private ProviderDAO providerDAO;

    public ProviderController(Connection connection) {
        this.connection = connection;
        this.providerDAO = new ProviderDAO(this.connection);
    }

    public List<Provider> list() {
        try {
            List<Provider> providers = this.providerDAO.list();
            return providers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Provider provider) {
        try {
            this.providerDAO.add(provider);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            this.providerDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, String name, String cnpj) {
        this.providerDAO.update(name, cnpj, id);
    }

}
