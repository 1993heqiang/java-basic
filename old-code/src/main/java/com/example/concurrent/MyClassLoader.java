package com.example.concurrent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream file = new FileInputStream("/home/heqiang/IdeaProjects/LeetCode/"+name+".class");
            byte[] data = new byte[file.available()];
            file.read(data);
            return defineClass(name,data,0,data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
