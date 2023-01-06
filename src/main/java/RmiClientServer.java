import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.ArrayList;

public class RmiClientServer
{
    public void addServer(int serverPort, String text){
       IServer rmiServer;
       Registry registry;
       String serverAddress = "192.168.0.18";
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress, serverPort);
           rmiServer = (IServer)(registry.lookup("rmiServer-" + serverPort));
           rmiServer.addServer(text);
       }
       catch(RemoteException e){}
       catch(NotBoundException e){}
    }

    public void sendMensageServer(String serverName, String text){
       IServer rmiServer;
       Registry registry;
       String serverAddress = "192.168.0.18";
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress, Integer.parseInt(serverName.split("-")[1]));
           rmiServer = (IServer)(registry.lookup(serverName));
           rmiServer.sendMensageServer(text);
       }
       catch(RemoteException e){}
       catch(NotBoundException e){}
    }
    public void sendServer(String serverName, String list){
       IServer rmiServer;
       Registry registry;
       String serverAddress = "192.168.0.18";
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress, Integer.parseInt(serverName.split("-")[1]));
           rmiServer = (IServer)(registry.lookup(serverName));
           rmiServer.receiveServer(list);
       }
       catch(RemoteException e){}
       catch(NotBoundException e){}
    }

}
