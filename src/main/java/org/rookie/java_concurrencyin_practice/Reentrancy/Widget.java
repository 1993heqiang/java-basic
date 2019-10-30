package org.rookie.java_concurrencyin_practice.Reentrancy;

public class Widget {
    public Object obj = new Object();
    public void doSomething(){
        synchronized (obj){
            System.out.println("Parent Method");
        }
    }
}
