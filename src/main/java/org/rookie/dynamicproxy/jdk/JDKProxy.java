package org.rookie.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        ProxyImpl proxy = new ProxyImpl(greeter);
        Hello proxyObject = (Hello) proxy.getProxy();
        proxyObject.hello("world");
    }

    private static class ProxyImpl implements InvocationHandler {

        private Object target;

        public ProxyImpl(Object target) {
            this.target = target;
        }

        public Object getProxy() {
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("hello".equals(method.getName()) && args.length == 1) {
                System.out.println("Before");
                method.invoke(target, args);
                System.out.println("After");
                return null;
            }
            return method.invoke(target, args);
        }
    }

    private static class Greeter implements Hello {
        @Override
        public void hello(String name) {
            System.out.println("Hello " + name);
        }
    }

    interface Hello {
        void hello(String name);
    }
}
