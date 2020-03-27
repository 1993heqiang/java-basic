package org.rookie.dynamicproxy.javassist;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class JavassistDynamicProxy implements MethodHandler {

    private static MethodFilter finalizeRemover = m -> !m.getName().equals("finalize");

    public static void main(String[] args) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setSuperclass(Greeter.class);
        proxyFactory.setFilter(finalizeRemover);
        Class clazz = proxyFactory.createClass();
        Greeter greeter = (Greeter) clazz.getConstructor().newInstance();
        ((Proxy) greeter).setHandler(new JavassistDynamicProxy());
        greeter.hello("World");
    }

    @Override
    public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Throwable {
        if("hello".equals(m.getName())&&args.length==1){
            System.out.println("before");
            Object result = proceed.invoke(self,args);
            System.out.println("after");
            return result;
        }
        return proceed.invoke(self, args);
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
