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
    public void sendMensage(String msg)
    {
        /*if(nameServer.equals(serverPadrao)){
            query(msg);
            for (String server : list) 
                if(!server.equals(serverPadrao))
                    new RmiClientServer().sendMensageServer(server, msg);
        }
        else                
            new RmiClient().sendMensage(portaPadrao, msg);*/
    }
    @Override
    public void addServer(String nameServer)
    {
        list.add(nameServer);
        port.put(nameServer, nameServer.split("-")[1]);
        String listTemp = "";
        for (int i = 0; i < list.size(); i++){
            listTemp += codigo + list.get(i) ;
        }
        for (String server : list){
            if(!server.equals(serverPadrao)){
                new RmiClientServer().sendServer(server, this.list.size() + listTemp, port.get(server));
            }
        }
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
            this.port.put(nameServer, nameServer.split("-")[1]);
            System.out.println(this.nameServer + ": list " + this.list.get(i) + " - "+ this.list.size() + " port: " + port.get(this.list.get(i)));
        }
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
            System.out.println(nameServer + " - Conected");
            System.out.println("nameServer: " + nameServer.length());
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
    String thisAddress, codigo = "ASA", serverPadrao = "rmiServer-3232";
    Registry registry;
    private String nameServer;
    private ArrayList<String> list = new ArrayList<>();
    private HashMap<String, String> port = new HashMap<>();
}