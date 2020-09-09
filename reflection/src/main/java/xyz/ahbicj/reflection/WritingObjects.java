package xyz.ahbicj.reflection;

import xyz.ahbicj.reflection.model.Person;
import xyz.ahbicj.reflection.orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class WritingObjects {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        EntityManager<Person> entityManage =  EntityManager.of(Person.class);

//        Person linda = new Person("Linda",31);
//        Person james = new Person("James",24);
//        Person susan = new Person("Susan",34);
//        Person john = new Person("john",33);
//
//        entityManage.persist(linda);
//        entityManage.persist(james);
//        entityManage.persist(susan);
//        entityManage.persist(john);

        Person linda = entityManage.find(1L);
        Person james = entityManage.find(2L);
        Person susan = entityManage.find(3L);
        Person john = entityManage.find(4L);

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);

    }
}
