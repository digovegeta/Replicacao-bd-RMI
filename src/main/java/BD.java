
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author sjkaz
 */
public class BD implements IBD{

    public BD(String nameDB) {
        createDB(nameDB);
        createTable();
    }
    
    @Override
    public void createDB(String nameDB)
    {
        this.nameDB = nameDB;
        try {
            String sql = "CREATE DATABASE " + nameDB + ";";
            conection();
            stmt.execute(sql);
            desconetion();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "BD-createDB: " + erro.getMessage());
        }        
    }
    @Override
    public void createTable()
    {
        try {
            String sql = "CREATE TABLE " + this.nameDB + ".mensagem (textoMensagem varchar(100));";
            conection();
            stmt.execute(sql);
            desconetion();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "BD-createTable: " + erro.getMessage());
        }
    }
    @Override
    public void insert(String msg)
    {
        try {
            conection();
            String sql = "INSERT INTO " + this.nameDB + ".Mensagem VALUES (\"" + msg + "\");";
            stmt.execute(sql);
            desconetion();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "BD-insert: " + erro.getMessage());
        }
    }
    @Override
    public String getAllData()
    {
        System.out.println("BD.getAllData()");
        try {
            conection();
            String sql = "SELECT * FROM " + this.nameDB + ".Mensagem;";
            ResultSet rs = stmt.executeQuery(sql);
            String result = "";
            int index = 0;
            while (rs.next())
            {
                String textoMensagem = rs.getString("textoMensagem");
                System.out.format("%s\n", textoMensagem);
                result += "-"+ textoMensagem ;
                index++;
            }
            desconetion();
            return index+result;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "BD-getAll: " + erro.getMessage());
        }
        return "0";
    }
    private void conection()
    {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "dbadmin");
            stmt = conn.createStatement();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "BD-createDB: " + erro.getMessage());
        }        
            
    }
    private void desconetion()
    {
        try{
            conn.close();
            stmt.close();        
            conn = null;
            stmt = null;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "BD-insert: " + erro.getMessage());
        }
    }
    @Override
    public void dropDB()
    {
        try {            
            conection();
            stmt.execute("drop database " + this.nameDB + ";");
        } catch (SQLException e) {
        }
            
    }
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstm;
    private String nameDB = "";
}
