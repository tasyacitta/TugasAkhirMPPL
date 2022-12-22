package model;

import database.Connector;
import controller.ControlGame;
import main.Main;
import javax.swing.*;
import java.sql.*;
import java.util.Date;



public class ModelRegis {
    private Connection connection;
    private Statement statement;
    
    public ModelRegis(){
        Connector connector = new Connector();
        connection = connector.getConnection();
        }
 
    public void inputRegis(String[] data){
        try{
            Timestamp date = new Timestamp(new Date().getTime()+7*60*60*1000); //+7jam konversi ke millisecond
            String query = " insert into pemain ( tanggal, nama, skor) values ( ?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setTimestamp (1, date);
            ps.setString (2, data[0]);
            ps.setString (3, data[1]);
         
            ps.executeUpdate();
            
            connection.close();
            JOptionPane.showMessageDialog(null, "Skor Anda Berhasil Direkam");
            
            ControlGame cg = new ControlGame();
            cg.lihatSkor();
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
        public void inputAdmin(String[] data){
        try{
            String query = " insert into admin (uname, password) values ( ?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString (1, data[0]);
            ps.setString (2, data[1]);
            ps.executeUpdate();
            
            connection.close();
            JOptionPane.showMessageDialog(null, "Input Berhasil");
            
            ControlGame cg = new ControlGame();
            cg.loginAdmin();
//            cg.login();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
