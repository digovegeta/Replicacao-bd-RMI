import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import jdk.nashorn.internal.objects.NativeArray;
 
public class RmiServer extends java.rmi.server.UnicastRemoteObject implements IServer, IClient
{
    //Construtor
    public RmiServer(int port) throws RemoteException
    {
        thisPort = port;
        nameServer = "rmiServer-" + thisPort;
        createNewServer();
        startPing();
    }
    //Interface
    @Override
    public boolean ping()
    {
        return true;
    }
    @Override
    public void sendMensage(String msg)
    {
        if(nameServer.equals(serverPadrao)){
            query(msg);
            for (String server : list) 
                if(!server.equals(serverPadrao))
                    new RmiClientServer().sendMensageServer(server, msg);
        }
        else                
            new RmiClientServer().sendMensage(portaPadrao, msg);
    }
    @Override
    public void addServer(String nameServer)
    {
        list.add(nameServer);
        String listTemp = listToString();
        for (String server : list)
            if(!server.equals(serverPadrao))
                new RmiClientServer().sendServer(server, this.list.size() + listTemp);
        System.out.println("list: " + list.size());
    }
    @Override
    public void sendMensageServer(String msg)
    {
        query(msg);
    }
    @Override
    public void receiveServer(String list)
    {
        this.list.clear();
        int index = Integer.parseInt(list.split(codigo)[0]);
        String nameServer = "";
        for(int i = 0; i < index; i++){
            nameServer = list.split(codigo)[i+1];
            this.list.add(nameServer);
            System.out.println(this.nameServer + ": list " + this.list.get(i) + " - "+ this.list.size());
        }
    }
    
    //Metodos da classe
    public void pong(String server)
    {
        System.out.println("server " + server + " não foi encontrado!");
        remove(server);
    }
    public void query(String msg)
    {
        System.out.println(nameServer + " query: " + msg);
    }
    public void remove(String nameServer)
    {
        System.out.println("server " + this.nameServer + " removeu " + nameServer );
        if(list.contains(nameServer))
            list.remove(nameServer);
        String listTemp = listToString();
        receiveServer(this.list.size() + listTemp);
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
            System.out.println(nameServer + " - Conected");
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
    private void setListToPing()
    {
        ping.setList(list);
    }
    private void setNameServerPing()
    {
        ping.setNameServer(nameServer);
    }
    private String listToString()
    {
        String listTemp = "";
        for (int i = 0; i < list.size(); i++)
            listTemp += codigo + list.get(i);
        return listTemp;
    }
    private void startPing()
    {
        Ping ping = new Ping(this);
        ping.setList(list);
        ping.setNameServer(nameServer);
        new Thread(ping).start();
    }
    
    //Variaveis
    int thisPort, portaPadrao = 3232;
    String thisAddress, codigo = "ASA", serverPadrao = "rmiServer-3232";
    Registry registry;
    private String nameServer;
    private ArrayList<String> list = new ArrayList<>();
    private Ping ping; 
}