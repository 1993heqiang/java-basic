package com.example.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NoVisibility {

    public static void main(String[] args) {
        try {
            System.out.println();
            ClassLoader classLoader = new MyClassLoader();
            Class clazz = classLoader.loadClass("HelloWorld");
            Object o = clazz.newInstance();
            Method method = clazz.getMethod("hello", String.class);
            method.invoke(o, "world");
            Method main = clazz.getMethod("main", String[].class);
            Object a = new String[]{};
            main.invoke(o, a);
            System.out.println("--------------------------------------------------------");
            for (Method m : clazz.getDeclaredMethods()) {
                System.out.println(m.getName() + "(" + m.getParameterCount() + ")");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    public static void test(Object... args) {
        for (Object s : args) {
            System.out.println(s);
        }
    }

}
