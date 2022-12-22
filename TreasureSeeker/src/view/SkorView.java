package view;

import controller.ControlGame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkorView extends JFrame implements ActionListener {
    private JTable table;
    private JButton btnBack;
    private String nama;
    public SkorView(String[][] data){
        try{
            nama = data[0][0];
            final String[] tableTitle = {"Username", "Skor"};
            setTitle("Data Skor");
            setSize(900,375);
            
            btnBack = new JButton(" Kembali ");
            table = new JTable(data,tableTitle);
            table.setBounds(30,40,400,600);
            JScrollPane sp=new JScrollPane(table);
            sp.setPreferredSize(new Dimension(500,80));
            this.getContentPane().add(BorderLayout.CENTER, sp);
            this.getContentPane().add(BorderLayout.SOUTH, btnBack);
            btnBack.addActionListener(this);
            btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            table.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation(200,150);
            setVisible(true);
        }
        catch (Exception e){
            System.out.println("Error : " + e);
    }
    
}
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnBack){
            System.exit(0);
        }
    }
}