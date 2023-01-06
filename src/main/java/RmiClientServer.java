import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

public class RmiClientServer
{
    public void addServer(int serverPort, String text){
       String s = "192.168.0.18";
       IServer rmiServer;
       Registry registry;
       String serverAddress = s;
       System.out.println("enviando mensagem \"" + text + "\" para " + serverAddress + ":" + serverPort);
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress,serverPort);
           rmiServer = (IServer)(registry.lookup("rmiServer-" + serverPort));
           rmiServer.addServer(text);
       }
       catch(RemoteException e)
       {
           
       }
       catch(NotBoundException e)
       {
           
       }
    }

    public void sendMensageServer(int serverPort, String text){
       String s = "192.168.0.18";
       IServer rmiServer;
       Registry registry;
       String serverAddress = s;
       System.out.println("enviando mensagem \"" + text + "\" para " + serverAddress + ":" + serverPort);
       try
       {
           registry = LocateRegistry.getRegistry(serverAddress,serverPort);
           rmiServer = (IServer)(registry.lookup("rmiServer-" + serverPort));
           rmiServer.sendMensageServer(text);
       }
       catch(RemoteException e)
       {
           
       }
       catch(NotBoundException e)
       {
           
       }
    }
    
}
