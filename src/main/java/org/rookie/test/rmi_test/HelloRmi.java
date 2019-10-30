package org.rookie.test.rmi_test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloRmi extends Remote {
    String helloRmi() throws RemoteException;
}
