package com.example;

//回调模式

interface Action {
    public void doSth();
}

public class special{
public static void main(String[] args) {
	
         repeat(5, new Action() {
             public void doSth() {
                 System.out.println("Hello, World");
             }
         }); 
} 
public static void repeat(int n, Action action) {
        for (int i = 0; i < n; i++) {  action.doSth(); } 
} 
}
