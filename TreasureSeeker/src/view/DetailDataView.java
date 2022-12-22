package view;

import controller.ControlGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailDataView extends JFrame implements ActionListener {
    private JLabel labelJudul, labeltanggal, labelnama, labelskor, istanggal, 
            isnama, isskor;
    private JButton bKembali, bHapus;
    private String nama;
    
    public void openDetail(String[] data){
        this.nama = data[1];
        System.out.println("namanya " + nama);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,325);
        labelJudul = new JLabel(" Detail Info Pemain ");
        labeltanggal = new JLabel(" Terakhir Bermain : ");
        labelnama = new JLabel(" Username             : ");
        labelskor = new JLabel(" Skor Terakhir        : ");
        
        istanggal = new JLabel(data[0]);
        isnama = new JLabel(data[1]);
        isskor = new JLabel(data[2]);
        bKembali = new JButton("Kembali");
        bHapus = new JButton("Hapus");
        
        setLayout(null);
        add(labelJudul);
        add(labeltanggal);
        add(istanggal);
        add(labelnama);
        add(isnama);
        add(labelskor);
        add(isskor);
        add(bHapus);
        add(bKembali);
        
        labelJudul.setBounds(70, 10, 120, 20);
        labeltanggal.setBounds(10, 40, 120, 20);
        istanggal.setBounds(130, 40, 190, 20);
        labelnama.setBounds(12, 55, 120, 20);
        isnama.setBounds(130, 55, 190, 20);
        labelskor.setBounds(10, 70, 120, 20);
        isskor.setBounds(130, 70, 190, 20);
        
        bHapus.setBounds(140, 150, 90, 20);
        bHapus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bHapus.addActionListener(this);
        bHapus.setBackground(Color.red);
        bKembali.setBounds(140, 260, 90, 20);
        bKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bKembali.addActionListener(this);
        
        setResizable(false);
        setLocation(450,200);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bHapus){
            ControlGame cg = new ControlGame();
            dispose();
            cg.delete(nama);
            dispose();
        }
        else{
            ControlGame cg = new ControlGame();
            cg.lihatData();
            dispose();
        }
    }
}
