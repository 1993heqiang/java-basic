package org.rookie.concurrent.Reentrancy;

public class LoggingWidget extends Widget {
    @Override
    public void doSomething() {
        synchronized (obj){
            System.out.println("Child Method");
            super.doSomething();
        }
    }
    public static void main(String[] args){
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }
}
