package database;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Connector {
    String url = "jdbc:mysql://localhost:3306/player?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String username = "root";
    String password = "";
    Connection connection;
    Statement statement;
    public Connector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Koneksi ke Database Gagal");
        }
    }
        public Connection getConnection(){
        return  connection;
    }
}

