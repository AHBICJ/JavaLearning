package xyz.ahbicj.reflection.util;

import xyz.ahbicj.reflection.annotation.Column;
import xyz.ahbicj.reflection.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Metamodel<T> {

    private final Class<T> clz;

    public static <T> Metamodel<T> of(Class<T> clz) {
        return new Metamodel<>(clz);
    }

    public Metamodel(Class<T> clz) {
        this.clz = clz;
    }

    public PrimaryKeyField getPrimaryKey() {

        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {

            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {
                return new PrimaryKeyField(field);
            }
        }

        throw new IllegalArgumentException("No primary key found in class " + clz.getSimpleName());
    }

    public List<ColumnField> getColumns() {

        List<ColumnField> columnFields = new ArrayList<>();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {

            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
            }
        }
        return columnFields;
    }

    private String buildQuestionMarksElement(List<String> columnNames) {
        return IntStream.range(0, columnNames.size())
                .mapToObj(idx -> "?").collect(Collectors.joining(", "));
    }

    private String buildColumnNames(List<String> columnNames) {
        return String.join(", ", columnNames);
    }

    private List<String> getColumnNamesWithPrimaryKey() {
        String primaryKeyColumnName = getPrimaryKey().getName();
        List<String> columnNames = getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
        columnNames.add(0, primaryKeyColumnName);
        return columnNames;
    }
    public String buildInsertRequest() {
        // insert into Person (id, name, age) values (?, ?, ?)
        List<String> columnNames = getColumnNamesWithPrimaryKey();
        String columnElement = buildColumnNames(columnNames);
        String questionMarksElement = buildQuestionMarksElement(columnNames);

        return "insert into " + this.clz.getSimpleName() +
                " (" + columnElement + ") values (" + questionMarksElement + ")";

    }
    public String buildSelectRequest() {
        // select id, name, age from Person where id = ?
        List<String> columnNames = getColumnNamesWithPrimaryKey();
        String columnElement = buildColumnNames(columnNames);
        return "select " + columnElement + " from " + this.clz.getSimpleName() +
                " where " + getPrimaryKey().getName() + " = ?";
    }



}
