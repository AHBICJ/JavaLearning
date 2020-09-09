package xyz.ahbicj.reflection.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManager {

    public H2EntityManager(Class clz) {
        super(clz);
    }

    @Override
    public Connection buildConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./reflection/db-files/db-reflect","sa","");
        return connection;
    }
}
