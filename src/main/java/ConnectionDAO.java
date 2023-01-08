import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConnectionDAO 
{    
    public Connection connectionBD(String BDName, String user, String pass )
    {
        Connection conn = null;
        if(pass == null)
            pass = "";
        try {
            String url = "jdbc:mysql://localhost:3306/" + BDName + "?user=" + user + "&passord=" + pass;
            conn = DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConnectionDAO: " + erro.getMessage());
        }        
        return conn;
    }
}
