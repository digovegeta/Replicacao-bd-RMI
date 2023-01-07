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
    public boolean ping(String serverName){
       IServer rmiServer;
       Registry registry;
       String serverAddress = "192.168.0.18";
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress, Integer.parseInt(serverName.split("-")[1]));
           rmiServer = (IServer)(registry.lookup(serverName));
           return rmiServer.ping();
       }
       catch(RemoteException e)
       {
           if(930990596 == e.hashCode()){
               System.out.println("Servidor não encontrado");
               return false;
           }
       }
       catch(NotBoundException e)
       {
            return false;
       }
        return false;
    }
    public void sendMensage(int serverPort, String text){
        
       String s = "192.168.0.18";
       IClient rmiServer;
       Registry registry;
       String serverAddress = s;
       System.out.println("enviando mensagem \"" + text + "\" para " + serverAddress + ":" + serverPort);
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress,serverPort);
           rmiServer = (IClient)(registry.lookup("rmiServer-" + serverPort));
           rmiServer.sendMensage(text);
       }
       catch(RemoteException e)
       {           
           if(930990596 == e.hashCode()){
               System.out.println("Servidor não encontrado");
           }
       }
       catch(NotBoundException e){}
    }
}
