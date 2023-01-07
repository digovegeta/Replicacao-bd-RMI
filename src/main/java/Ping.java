
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author digo_
 */
public class Ping implements Runnable{

    public Ping(RmiServer instance) {
        this.instance = instance;
    }
    
    @Override
    public void run() {
        while (true) {
            App.separador(5000);
            for(int i = 0; i < list.size(); i++){
                server = list.get(i);                 
                if(!server.equals(nameServer)){
                    if(new RmiClientServer().ping(server))
                        System.out.println(nameServer + " ping - " + server);
                    else
                        instance.pong(server);
                }
            }
        }
    }
    public void setNameServer(String nameServer){
        this.nameServer = nameServer;
    }
    public void setList(ArrayList<String> list){
        this.list = list;
    }
    
    public String server = "", nameServer = "";
    private ArrayList<String> list = new ArrayList<>();
    private RmiServer instance;

}
