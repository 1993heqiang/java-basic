package org.rookie.tools.javassist;

public class InnerClass {
    public class PublicClass{
        public void hello(){
            System.out.println(String.valueOf("Hello Public Inner Class"));
        }
    }

    private class PrivateClass{
        public void hello(){
            System.out.println(String.valueOf("Hello Private Inner Class"));
        }
    }

    public void publicClassHello(){
        PublicClass publicClass = new PublicClass();
        publicClass.hello();
    }
    public void privateClassHello(){
        PrivateClass privateClass = new PrivateClass();
        privateClass.hello();
    }
}
