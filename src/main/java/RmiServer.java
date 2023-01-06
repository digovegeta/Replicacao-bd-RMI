import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import jdk.nashorn.internal.objects.NativeArray;
 
public class RmiServer extends java.rmi.server.UnicastRemoteObject implements IServer, IClient
{
    public RmiServer(int port) throws RemoteException
    {
        thisPort = port;
        nameServer = "rmiServer-" + thisPort;
        createNewServer();
    }
    @Override
    public void addServer(String nameServer){
        list.add(nameServer);
        System.out.println("list: " + list.size());
    }
    @Override
    public void sendMensage(String msg){
        for (String server : list) {
            if(server.equals(serverPadrao))
                query(msg);
            else
                new RmiClientServer().sendMensageServer(portaPadrao, msg);
        }
    }
    @Override
    public void sendMensageServer(String msg){
        query(msg);
    }
    public void receiveMessage(String x) throws RemoteException
    {
            System.out.println("Mensagem recebida \"" + x + "\" na porta: " + nameServer);
    }
    
    public void query(String msg)
    {
        System.out.println(msg);
    }
    private void localizarAddress() 
    {
        try
        {
            System.out.println("Procurando Address");
            thisAddress = (InetAddress.getLocalHost()).toString();
            System.out.println("Address encontrado: " + thisAddress);
        }
        catch(Exception e)
        {
            System.err.println( "Address não foi encontrado." + e.getMessage());
        }
    }
    private void createNewServer()
    {
        localizarAddress();
        try
        {
            registry = LocateRegistry.createRegistry(thisPort);
            registry.rebind(nameServer, this);
            System.out.println(nameServer + " - " + "Conected");
        }
        catch(RemoteException e)
        {
            System.err.println("Erro: " + e.getMessage());
        }
        if(!nameServer.equals(serverPadrao))
            new RmiClientServer().addServer(portaPadrao, nameServer);
        else
            addServer(nameServer);
    }
    int thisPort, portaPadrao = 3232;
    String thisAddress, codigo = "$#$", serverPadrao = "rmiServer-3232";
    Registry registry;
    private String nameServer;
    private ArrayList<String> list =  new ArrayList<>();
}