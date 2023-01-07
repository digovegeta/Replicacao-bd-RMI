
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class App1 {

    public static void main(String[] args) throws RemoteException {
       new RmiServer(3234);
       App.separador(200);
       new RmiServer(3236);
       App.separador(200);
       new RmiServer(3237);
       App.separador(20000);
       System.exit(0);
    }
}
