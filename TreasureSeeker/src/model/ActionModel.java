package model;

import java.sql.*;
import javax.swing.*;
import database.Connector;
import controller.ControlGame;
import game.Game;
import java.util.Arrays;

/**
 *
 * @author ASUS
 */
public class ActionModel {
    private Connection connection;
    private Statement statement;

    public ActionModel(){
        Connector connect = new Connector();
        connection = connect.getConnection();
    }
    
    public void cekLogin(String [] data){
          try{
            String query = "SELECT * FROM admin WHERE uname = '"+data[0]+"' and password = '"+data[1]+"' ";
            statement=connection.createStatement();
            
            ResultSet s = statement.executeQuery(query);
            if(s.next()){
            JOptionPane.showMessageDialog(null, "Login Berhasil");   
            
            }else{
            JOptionPane.showMessageDialog(null, "Username atau Password salah!!");     
            connection.close();
            ControlGame cg = new ControlGame();
            cg.loginAdmin();
            }
            
            connection.close();
            ControlGame cg= new ControlGame();
            cg.lihatData();
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal login!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }

       public void cekUser(String [] data){
          try{
            String query = "SELECT * FROM pemain WHERE nama = '"+data[0]+"' and pw = '"+data[1]+"' ";
            statement=connection.createStatement();
            
            ResultSet s = statement.executeQuery(query);
            if(s.next()){
            JOptionPane.showMessageDialog(null, "Login Berhasil");                
            }else{
            JOptionPane.showMessageDialog(null, "Username atau Password salah!!");                
            }
            
            connection.close();
            
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal login!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    } 
       
       public void delete(String nama){
        try {
            String query = " delete from pemain where nama = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, nama);
            preparedStmt.execute();

            connection.close();

            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal! silakan hapus data barang dengan nama = " + nama);
        }
        ControlGame cg = new ControlGame();
        cg.lihatData();
    }
        public String[][] lihatData() {
        try{
            int row = 0;
            int numRows = numRows("pemain");
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][8];
            statement = connection.createStatement();
            String query = "select * from pemain";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("tanggal");
                data[row][1] = resultSet.getString("nama");
                data[row][2] = resultSet.getString("skor");
                row++;
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
        
    public String[][] lihatSkor() {
        try{
            int row = 0;
            int numRows = numRows("pemain");
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][8];
            statement = connection.createStatement();
            String query = "select * from pemain order by skor DESC";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[row][0] = resultSet.getString("nama");
                data[row][1] = resultSet.getString("skor");
                row++;
               
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    

    public String[] lihatData(String nama) {
                try{
            String[] data = new String[9];
            statement = connection.createStatement();
            String query = "select * from pemain where nama =  '" + nama + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[0] = resultSet.getString("tanggal");
                data[1] = resultSet.getString("nama");
                data[2] = resultSet.getString("skor");
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    
    public int numRows(String pemain){
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from pemain";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlData++;
            }
            return jmlData;

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
    
    
}