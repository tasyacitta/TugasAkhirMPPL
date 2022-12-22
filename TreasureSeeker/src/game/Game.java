package game;

import controller.ControlGame;
import view.*;
import java.awt.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.lang.*;
import main.Main;
/**
 *
 * @author user
 */
public class Game extends JFrame implements Runnable {  
    private int x;
    private int y;
    private int xArah;
    private int yArah;
    private ArrayList<Bomb> bomb;
    private ArrayList<Treasure> treasure;
    private boolean Pemain;
    Image gambar;
    private Image background;
    private Image background2;
    Dimension size; 
    AudioClip soundTrack;
    AudioClip soundTrack2;
    AudioClip soundTrack3;
    int skor = 0;
    private boolean bool=true;
    private JButton btn1;
    
     public void run() {
        
        while (bool) {
            move();
           
            
            for (int i = 0; i < treasure.size(); i++) {
                Rectangle treasureHitbox = treasure.get(i).getHitbox();
                Rectangle playerHitbox = getPlayerHitbox();
                if (treasureHitbox.intersects(playerHitbox))
                    treasure.remove(i);
                    
                if(treasureHitbox.intersects(playerHitbox)){
                    skor +=10;
                    
                if(treasureHitbox.intersects(playerHitbox)){
                    soundTrack2 = Applet.newAudioClip(getClass().getResource("Crunch.wav"));
                     soundTrack2.play();
                    }
                                       
                }
            }
            for (int i = 0; i < bomb.size(); i++) {
                Rectangle musuhHitbox = bomb.get(i).getHitbox();
                Rectangle playerHitbox = getPlayerHitbox();
                if (musuhHitbox.intersects(playerHitbox))
                    Pemain = false ;
                if (Pemain==false){
                    bomb.removeAll(bomb);
                    treasure.removeAll(treasure);
                    
                    soundTrack3 = Applet.newAudioClip(getClass().getResource("gameover.wav"));
                    soundTrack3.play();
                }
            }
                              
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
    }
    
    private void move() {
        
        x += xArah;
        y += yArah;
        if (x < 0)
            x = 0;
        if (y < 20)
            y = 20;
        if (x > 580)
            x = 580;
        if (y > 440)
            y = 440;
        
    }
    
       
    
    public Game() {
       
        setPreferredSize(size);
        setSize(640, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setVisible(true);
        setTitle("Fortune Hunter");
        x = 320;
        y = 240;
        xArah = 0;
        yArah = 0;
        addKeyListener(new KeyListener());
        
        bomb = new ArrayList<Bomb>();
        treasure = new ArrayList<Treasure>(); 
        Pemain = true;
        size = new Dimension();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        background = new ImageIcon(this.getClass().getResource("hutan.jpg")).getImage();
        
        size.width = background.getWidth(null);
        size.height = background.getHeight(null);
        setPreferredSize(size);
        soundTrack = Applet.newAudioClip(getClass().getResource("sound.wav"));
        soundTrack.loop();
              
    }
    
    @Override
    public void paint(Graphics g) {
        Image dbImg = createImage(getWidth(), getHeight());
        Graphics dbg = dbImg.getGraphics();
        draw(dbg);
        g.drawImage(dbImg, 0, 0, this);   
        
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, null);
        
        if (Pemain) {
            Image hunter = new ImageIcon(this.getClass().getResource("hunter.png")).getImage();
            g.drawImage(hunter, x, y, this);
        
            
            g2d.setFont(new Font("Arial",Font.BOLD,18));
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("SCORE : " + Integer.toString(skor),20,50);
            g2d.setFont(new Font("Arial",Font.BOLD,18));
                    g2d.setColor(Color.YELLOW);
                    g2d.drawString("Admin  Press SHIFT"  ,440,460);
                    
                    
        }
        else{
        soundTrack.stop();
        bool=false;
        
        g2d.setFont(new Font("Arial",Font.BOLD,50));
		g2d.setColor(Color.RED);
	g2d.setFont(new Font("Arial",Font.BOLD,40));
		g2d.setColor(Color.RED);
                g2d.drawString("SCORE : " + Integer.toString(skor),200,400);
        gameOver();

        
        }
        
        for (int i = 0; i < bomb.size(); i++) {
            Bomb e = bomb.get(i);
            e.draw(g);
        }
        for (int i = 0; i < treasure.size(); i++) {
            Treasure e = treasure.get(i);
            e.draw(g);
             
        }
        
        repaint();
        
    }
    
    public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		ControlGame cg = new ControlGame();
                String sk= Integer.toString(skor);
                cg.inputPemain(sk);  
	}
    
    public void admin() {
                soundTrack.stop();
                bool=false;
                dispose();
                int result = JOptionPane.showConfirmDialog(this, "LOGIN ADMIN?", "LOGIN ADMIN", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                  ControlGame cg = new ControlGame();
                  cg.loginAdmin();  
                }
                else{
                    main(null);
                    dispose();
                }
	}
    
    public static Image loadImage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        return icon.getImage();
    }
     
    private void Lawanmusuh() {
        Bomb e = new Bomb();
        bomb.add(e);
        Thread t = new Thread(e);
        t.start();
    }
    private void munculMakanan() {
        Treasure e = new Treasure();
        treasure.add(e);
       Thread t = new Thread(e);
        t.start();
        
    }
     
    
    private Rectangle getPlayerHitbox() {
        return new Rectangle(x, y, 50, 42);
        
    }

    public static void main(String[] args) {
        Game gh = new Game();
        Thread t = new Thread(gh);
        t.start();
        while (true) {
            if (gh.bomb.size() < 15 && gh.Pemain) {
                gh.Lawanmusuh();
            }
            if (gh.treasure.size() < 15 && gh.Pemain) {
                gh.munculMakanan();
            }
            
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
        
        
        
    }
    
    
    
    private class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    yArah = -2;
                    break;
                case KeyEvent.VK_DOWN:
                    yArah = 2;
                    break;
                case KeyEvent.VK_LEFT:
                    xArah = -2;
                    break;
                case KeyEvent.VK_RIGHT:
                    xArah = 2;
                    break;
                case KeyEvent.VK_SHIFT:
                  admin();             
                    break;   
                default:
                    break;
                
            }
        }
        
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    yArah = 0;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    xArah = 0;
                    break;
                case KeyEvent.VK_SHIFT:
                                      
                    break;   
                default:
                    break;
            }
        }
        
         
    }
    
}