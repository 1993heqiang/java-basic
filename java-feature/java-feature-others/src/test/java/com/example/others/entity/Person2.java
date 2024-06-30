package com.example.others.entity;

public class Person2 implements IHello {
    private String name = "123";
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String sayHello() {
        return "Hello Person2";
    }
}
