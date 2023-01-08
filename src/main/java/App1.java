
import java.rmi.RemoteException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

public class App1 {

    public static void main(String[] args) throws RemoteException {
       RmiServer rmiServer34 = new RmiServer(3234);
       App.separador(200);
       RmiServer rmiServer36 = new RmiServer(3236);
       App.separador(200);
       RmiServer rmiServer37 = new RmiServer(3237);
       App.separador(200);
       new RmiClientServer().sendMensage(3234, "deu certo");
       App.separador(30000);
       rmiServer34.bd.dropDB();
       rmiServer36.bd.dropDB();
       rmiServer37.bd.dropDB();
       App.separador(1000);
       System.exit(0);
    }
}
