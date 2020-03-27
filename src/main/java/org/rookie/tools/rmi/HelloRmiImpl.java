package org.rookie.tools.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class HelloRmiImpl extends UnicastRemoteObject implements HelloRmi {

    protected HelloRmiImpl() throws RemoteException {
    }

    @Override
    public String helloRmi() throws RemoteException {
        return "Hello RMI";
    }

    public static void main(String[] args){

        try {
            HelloRmiImpl helloRmi = new HelloRmiImpl();
            LocateRegistry.createRegistry(6000);
            Naming.rebind("rmi://127.0.0.1:6000/HelloRmi",helloRmi);
            System.out.println("服务已启动");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
