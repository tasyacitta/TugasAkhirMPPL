/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 *
 * @author user
 */
public class Treasure implements Runnable {
    private int x;
    private int y;
    private int xArah;
    private int yArah;
    Image treasure;
    
    Treasure() {
        x = 360;
        y = 50;
        Random rng = new Random();
        xArah = -1 + rng.nextInt(3);
        yArah = -1 + rng.nextInt(3);
        if (xArah == 0 && yArah == 0) {
            xArah = 1;
            yArah = 1;
        }
    }
    
    public void run() {
        while (true) {
            move();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.err.println("Error: Thread Interrupted.");
            }
        }
    }
    
    private void move() {
        x += xArah;
        y += yArah;
        if (x < 0)
            xArah = 1;
        if (x > 630)
            xArah = -1;
        if (y < 0)
            yArah = 1;
        if (y > 470)
            yArah = -1;
    }
    
    
   
    
    
    public void draw(Graphics g) {
        Image treasure = new ImageIcon(this.getClass().getResource("treasure.png")).getImage();
            g.drawImage(treasure, x, y, null);
    }
    
    Rectangle getHitbox() {
        return new Rectangle(x, y, 10, 10);
    }
}
    

