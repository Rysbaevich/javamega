package kg.itschool.crm.dao;

import kg.itschool.crm.dao.daoutil.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<Model> {
    Model save(Model model);
    Model findById(Long id);
    List<Model> findAll();

    default Connection getConnection() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/crm";
        final String USERNAME = "postgres";
        final String PASSWORD = "'";

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    default void close(AutoCloseable closeable) {
        try {
            Log.info(this.getClass().getSimpleName(), closeable.getClass().getSimpleName()," closing...");
            closeable.close();
            Log.info(this.getClass().getSimpleName(), closeable.getClass().getSimpleName()," closed...");
        } catch (Exception e) {
            Log.error(this.getClass().getSimpleName(), e.getStackTrace()[0].getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
    }
}
