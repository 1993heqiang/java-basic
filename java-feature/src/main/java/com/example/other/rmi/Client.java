package com.example.other.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args){
        try {
            HelloRmi helloRmi = (HelloRmi) Naming.lookup("rmi://127.0.0.1:6000/HelloRmi");
            System.out.println(helloRmi.helloRmi());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
