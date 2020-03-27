package org.rookie.tools.javassist;

public class AnonymousClass {
    public Runnable methodReturnAnonymousClass(){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(String.valueOf("Hello run()"));
            }
        };
    }
    public void methodContainAnonymousClass(){
        Object obj = new Object() {
            @Override
            public String toString() {
                System.out.println(String.valueOf("Hello toString()"));
                return "OK";
            }
        };
        obj.toString();
    }
    public void hello(){
        new Thread(methodReturnAnonymousClass()).start();
        methodContainAnonymousClass();
    }
}
