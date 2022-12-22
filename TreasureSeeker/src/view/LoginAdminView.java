package view;

import controller.ControlGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginAdminView extends JFrame implements ActionListener {
    public static void main(String[] args) {
        LoginAdminView lv = new LoginAdminView();
        lv.formLogin();
    }
    private JTextField fieldnama, fieldpw;
    private JLabel labeljudul, labelnama, labelpw;
    private JButton btnlogin, btnregister, btnReset;

public void formLogin() {
    setTitle("Login Admin");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(220,225);
    
    labeljudul = new JLabel(" LOGIN ADMIN");
    labelnama = new JLabel(" Username ");
    fieldnama = new JTextField(18);
    labelpw = new JLabel(" Password ");
    fieldpw = new JTextField(6);
    btnlogin = new JButton(" Login ");
    btnregister = new JButton(" Register ");
    
    btnReset = new JButton(" Reset ");
    
    setLayout(null);
    add(labeljudul);
    add(labelnama);
    add(fieldnama);
    add(labelpw);
    add(fieldpw);
    add(btnlogin);
    add(btnregister);
    add(btnReset);
    
    labeljudul.setBounds(85,10,120,20);
    labelnama.setBounds(10,45,120,20);
    fieldnama.setBounds(100,45,90,20);
    labelpw.setBounds(10,70,120,20);
    fieldpw.setBounds(100,70,90,20);
    btnReset.setBounds(50,150,90,20);
    btnReset.setBackground(Color.BLACK);
    btnReset.setForeground(Color.white);
    btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnReset.addActionListener(this);
    btnlogin.setBounds(12,125,90,20);
    btnlogin.setBackground(Color.blue);
    btnlogin.setForeground(Color.white);
    btnlogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnlogin.addActionListener(this);
    btnregister.setBounds(106,125,90,20);
    btnregister.setBackground(Color.red);
    btnregister.setForeground(Color.white);
    btnregister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnregister.addActionListener(this);
    
    setResizable(false);
    setLocation(450,200);
    setVisible(true);
    

    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnlogin) {
            if (fieldnama.getText().equals("")) {
                setMessage("Username tidak boleh kosong");
                }
            if(fieldpw.getText().equals("")){
                setMessage("Password harus diisi");
                }
            else {
              
                  String[] data = {
                           fieldnama.getText(),
                           fieldpw.getText()
                    };
                  if(fieldnama.getText().toLowerCase().contains("admin".toLowerCase())){
                   dispose();
                   ControlGame cg = new ControlGame();
                   cg.cekAdmin(data);
                  }
                  else{
                  setMessage("INPUT SALAH! ANDA BUKAN ADMIN!");
                  dispose();
                  formLogin();
                        }
                }
        }
        
        if(e.getSource()==btnregister) {
          ControlGame cg = new ControlGame();
          cg.registrasi();
            dispose();
        }
        else if(e.getSource()==btnReset){
            reset();
        }
        
    }
    
    public void reset(){
        fieldnama.setText("");
        fieldpw.setText("");
        
    }

    public void setMessage(String message) {
        JOptionPane.showMessageDialog(this, message); 
    }
}