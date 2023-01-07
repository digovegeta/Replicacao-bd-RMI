
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws RemoteException {
       new RmiServer(3232);
       separador(200);
       new RmiServer(3231);
       separador(200);
       new RmiServer(3230);
       separador(500);
       new RmiClient().sendMensage(3232,"Amor");
       separador(500);
       new RmiClient().sendMensage(3231,"Bahia");
       separador(500);
       new RmiClient().sendMensage(3230,"Carnaval");
       separador(500);
       new RmiClient().sendMensage(3235,"Diogo");
       separador(20000);
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
