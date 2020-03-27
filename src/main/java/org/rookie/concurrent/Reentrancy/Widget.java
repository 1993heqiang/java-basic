package org.rookie.concurrent.Reentrancy;

public class Widget {
    public Object obj = new Object();
    public void doSomething(){
        synchronized (obj){
            System.out.println("Parent Method");
        }
    }
}
