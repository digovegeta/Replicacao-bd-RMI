
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws RemoteException {
       RmiServer rmiServer32 = new RmiServer(3232);
       separador(200);
       new RmiClient().sendMensage(3232,"Amor");
       separador(200);
       RmiServer rmiServer31 = new RmiServer(3231);
       separador(200);
       RmiServer rmiServer30 = new RmiServer(3230);
       separador(500);
       new RmiClient().sendMensage(3231,"Bahia");
       separador(500);
       new RmiClient().sendMensage(3230,"Carnaval");
       separador(500);
       new RmiClient().sendMensage(3235,"Diogo");
       
       separador(60000);
       rmiServer32.bd.dropDB();
       rmiServer31.bd.dropDB();
       rmiServer30.bd.dropDB();
       separador(10000);
       System.exit(0);
    }
    public static void separador(int time)
    {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException ex) {}
        System.out.println("--------------------------------------------------------------------------------------");
    }
}
