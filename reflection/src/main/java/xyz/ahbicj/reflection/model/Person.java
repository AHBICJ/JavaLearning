package xyz.ahbicj.reflection.model;


import xyz.ahbicj.reflection.annotation.Column;
import xyz.ahbicj.reflection.annotation.PrimaryKey;

public class Person {

    @PrimaryKey(name = "s_id")
    private long id;

    @Column(name = "s_name")
    private String name;

    @Column
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person of(String name, int age) {
        return new Person(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
