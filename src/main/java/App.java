
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class App {

    public static void main(String[] args) throws RemoteException {
       new RmiServer(3232);
       separador();
       new RmiServer(3231);
       separador();
       new RmiServer(3230);
       separador();
       new RmiClient().sendMensage(3232,"Amor");
       separador();
       new RmiClient().sendMensage(3231,"Bahia");
       separador();
       new RmiClient().sendMensage(3230,"Carnaval");
       separador();
       System.exit(0);
    }
    private static void separador()
    {
        for(int i = 0; i < 30; i++){
           System.out.print("-");
       }
        System.out.println("");
    }
}
