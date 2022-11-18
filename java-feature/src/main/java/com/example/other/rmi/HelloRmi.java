package com.example.other.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloRmi extends Remote {
    String helloRmi() throws RemoteException;
}
