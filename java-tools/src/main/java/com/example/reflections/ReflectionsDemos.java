package com.example.reflections;

import com.base.biz.Person;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import static org.reflections.scanners.Scanners.MethodsAnnotated;
import static org.reflections.scanners.Scanners.MethodsReturn;
import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class ReflectionsDemos {

    public static void main(String[] args) {
        ReflectionsDemos reflectionsDemos = new ReflectionsDemos();
        reflectionsDemos.simple();
    }

    public void simple() {
        Reflections reflections = new Reflections("com.base.biz");
        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(Person.class).asClass());
        printClassInfo(subTypes);

        Set<Class<?>> annotated =
                reflections.get(SubTypes.of(TypesAnnotated.with(Deprecated.class)).asClass());
        printClassInfo(annotated);

        Set<Method> voidMethods =
                reflections.get(MethodsReturn.with(void.class).as(Method.class));
    }

    public void createInstance() {


    }

    private void printClassInfo(Collection<Class<?>> classes) {
        System.out.println("Size: " + classes.size());
        classes.forEach(clazz-> System.out.println(clazz.getSimpleName()));
        System.out.println();
    }

}
