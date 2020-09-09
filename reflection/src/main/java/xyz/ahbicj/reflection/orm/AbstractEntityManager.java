package xyz.ahbicj.reflection.orm;

import xyz.ahbicj.reflection.util.ColumnField;
import xyz.ahbicj.reflection.util.Metamodel;
import xyz.ahbicj.reflection.util.PrimaryKeyField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public abstract class AbstractEntityManager<T> implements EntityManager<T> {

    private final Class<T> clz;

    private Connection connection;

    public AbstractEntityManager(Class<T> clz) {
        this.clz = clz;
    }

    private final AtomicLong idGenerator = new AtomicLong(0L);

    @Override
    public void persist(T t) throws SQLException, IllegalAccessException {
        Metamodel<T> metamodel = Metamodel.of(clz);
        String sql = metamodel.buildInsertRequest();
        try (PreparedStatement statement = prepareStatementWith(sql).andParameters(t)) {
            statement.executeUpdate();
        }
    }

    private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
        Connection connection = buildConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        return new PreparedStatementWrapper(statement);
    }

    public abstract Connection buildConnection() throws SQLException;

    private class PreparedStatementWrapper {
        private final PreparedStatement statement;

        public PreparedStatementWrapper(PreparedStatement statement) {
            this.statement = statement;
        }

        public PreparedStatement andParameters(T t) throws SQLException, IllegalAccessException {
            Metamodel<T> metamodel = Metamodel.of(clz);
            Class<?> primaryKeyType = metamodel.getPrimaryKey().getType();
            // we should handling all type
            if (primaryKeyType == long.class) {
                // usually it should delegate database to do this
                long id = idGenerator.incrementAndGet();
                statement.setLong(1, id);
                Field field = metamodel.getPrimaryKey().getField();
                field.setAccessible(true);
                field.set(t, id);
            }
            List<ColumnField> columns = metamodel.getColumns();
            int bound = columns.size();
            for (int idx = 0; idx < bound; idx++) {
                ColumnField columnField = columns.get(idx);
                Class<?> fieldType = columnField.getType();
                Field field = columnField.getField();
                field.setAccessible(true);
                Object value = field.get(t);
                if (fieldType == int.class) {
                    statement.setInt(idx + 2, (Integer) value);
                } else if (fieldType == String.class) {
                    statement.setString(idx + 2, (String) value);
                }
            }
            return statement;
        }

        public PreparedStatement andPrimaryKey(Object primaryKey) throws SQLException {
            if (primaryKey.getClass() == Long.class) {
                statement.setLong(1, (Long) primaryKey);
            }
            return statement;
        }
    }

    @Override
    public T find(Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Metamodel<T> metamodel = Metamodel.of(clz);
        String sql = metamodel.buildSelectRequest();
        try (PreparedStatement statement = prepareStatementWith(sql).andPrimaryKey(primaryKey)) {
            ResultSet resultSet = statement.executeQuery();
            return buildInstanceForm(resultSet);
        }
    }

    private T buildInstanceForm(ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        if (resultSet.next()){
            Metamodel<T> metamodel = Metamodel.of(clz);
            T t = clz.getConstructor().newInstance();
            PrimaryKeyField primaryKey = metamodel.getPrimaryKey();
            setFiled(t, resultSet, primaryKey.getField(), primaryKey.getName());
            for (ColumnField columnField : metamodel.getColumns()) {
                setFiled(t, resultSet, columnField.getField(), columnField.getName());
            }
            return t;
        }
        return null;
    }

    private void setFiled(T t, ResultSet resultSet, Field field, String keyName) throws SQLException, IllegalAccessException {
        Class<?> fieldType = field.getType();
        // cuz we use "(id int primary key " in database ...
        if (fieldType == long.class || fieldType == int.class) fieldType = Integer.class;
        field.setAccessible(true);
        field.set(t, resultSet.getObject(keyName, fieldType));
    }
}
