package org.rookie.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method m, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("hello".equals(m.getName()) && args.length == 1) {
            System.out.println("before");
            Object result = methodProxy.invokeSuper(o, args);
            System.out.println("after");
            return result;
        }
        return m.invoke(o, args);
    }

    public static void main(String[] args) {
        Enhancer e = new Enhancer();
        e.setSuperclass(Greeter.class);
        e.setCallback(new CglibProxy());
        Greeter greeter = (Greeter) e.create();
        greeter.hello("World");
    }

    private static class Greeter {
        public Greeter() {

        }

        public String hello(String name) {
            System.out.println("Hello " + name);
            return name;
        }
    }
}
