package view;

import controller.ControlGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Pemain extends JFrame implements ActionListener {
    private JTextField fieldnama;
    private JLabel labeljudul, labelnama, labelskor,labelskor2;
    private JButton btntambahData, btnReset;

public void inputPemain(String skor) {
    setTitle("Input Data");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(220,225);
    
    labeljudul = new JLabel(" Input Data ");
    labelnama = new JLabel(" Nama Pemain ");
    fieldnama = new JTextField(18);
    labelskor = new JLabel(" Skor ");
    labelskor2 = new JLabel(skor);
    btntambahData = new JButton(" Tambah ");
    
    btnReset = new JButton(" Reset ");
    
    setLayout(null);
    add(labeljudul);
    add(labelnama);
    add(fieldnama);
    add(labelskor);
    add(labelskor2);
    add(btntambahData);
    add(btnReset);
    
    labeljudul.setBounds(85,10,120,20);
    labelnama.setBounds(10,45,120,20);
    fieldnama.setBounds(100,45,90,20);
    labelskor.setBounds(10,70,120,20);
    labelskor2.setBounds(100,70,90,20);
    btnReset.setBounds(50,150,90,20);
    btnReset.setBackground(Color.BLACK);
    btnReset.setForeground(Color.white);
    btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnReset.addActionListener(this);
    btntambahData.setBounds(12,125,180,20);
    btntambahData.setBackground(Color.blue);
    btntambahData.setForeground(Color.white);
    btntambahData.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btntambahData.addActionListener(this);
    
    

   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    setResizable(false);
    setVisible(true);
    

    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btntambahData) {
            if (fieldnama.getText().equals("")) {
                setMessage("Nama Pemain tidak boleh kosong");
                }
            else {
              
                  String[] data = {
                           fieldnama.getText(),
                           labelskor2.getText()
                    };
                  
                  ControlGame cg = new ControlGame();
                  dispose();
                  cg.inputRegis(data);
                        
                }
        }
        
        else if(e.getSource()==btnReset){
            reset();
        }
        
    }
    
    public void reset(){
        fieldnama.setText("");
    }

    public void setMessage(String message) {
        JOptionPane.showMessageDialog(this, message); 
    }
}