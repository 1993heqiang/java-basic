package com.example.others;

import com.example.others.entity.IHello;
import com.example.others.entity.Person2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ReflectTests {

    @Test
    void proxyTest() {
        Person2 person2 = new Person2();
        MyInvocationHandler ih = new MyInvocationHandler();
        IHello proxyObj = (IHello) Proxy.newProxyInstance(person2.getClass().getClassLoader(), person2.getClass().getInterfaces(), ih);
        Assertions.assertEquals("Hello", proxyObj.sayHello());
    }

    private static class MyInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("sayHello")) {
                return "Hello";
            }
            return method.invoke(proxy, args);
        }
    }
}
