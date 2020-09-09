package xyz.ahbicj.reflection.orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager<T> {
    static <T> EntityManager<T> of(Class<T> clz) {
        return new H2EntityManager<>(clz);
    }

    void persist(T t) throws SQLException, IllegalAccessException;

    T find(Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
