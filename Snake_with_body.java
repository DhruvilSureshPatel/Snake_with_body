package some;

//Author : DHRUVIL SURESH PATEL
import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Snake_with_body extends JFrame implements KeyListener {

    int width, height, xpos, ypos, fx, fy, score = 0, time = 150 , ten = 10;
    char pressed = 'd', prev = 'd';
    ArrayList<Integer> xbody = new ArrayList<>() , ybody = new ArrayList<>();

    Snake_with_body(int h, int w) {
        width = w;
        height = h;
    }
    
    boolean isOut(int x , int y){
        for(int i=0 ; i<xbody.size() ; i++){
            int xx = xbody.get(i).intValue() , yy = ybody.get(i).intValue();
            if(x==xx && y==yy){
                return true;
            }
        }
        
        return false;
    }
    
    public void paint(Graphics g){
        Dimension d = getSize();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, d.width, d.height);
        
        g.setColor(Color.BLACK);
        g.drawString("#" , fx , fy);
        
        int oldx = xbody.get(0).intValue() , oldy = ybody.get(0).intValue() , x = oldx , y = oldy;
        if (pressed == 'd') {
            if (prev == 'a') {
                pressed = 'a';
                x -= ten;
            } else {
                x += ten;
            } 
        } else if (pressed == 'w') {
            if (prev == 's') {
                pressed = 's';
                y += ten;
            } else {
                y -= ten;
            }
        } else if (pressed == 'a') {
            if (prev == 'd') {
                pressed = 'd';
                x += ten;
            } else {
                x -= ten;
            }
        } else if (pressed == 's') {
            if (prev == 'w') {
                pressed = 'w';
                y -= ten;
            } else {
                y += ten;
            }
        }
        
        if (x >= width || x < 0 || y < 0 || y >= height || isOut(x , y)) {
            x = width / 2;
            y = height / 2;
            pressed = 't';
            JOptionPane.showMessageDialog(null, "Your Score is " + score);
            System.exit(0);
        }
        
        xbody.set(0 , x);
        ybody.set(0 , y);
        
        for(int i=1 ; i<xbody.size() ; i++){
            int newx = xbody.get(i).intValue() , newy = ybody.get(i).intValue();
            xbody.set(i , oldx);
            ybody.set(i , oldy);
            oldx = newx;
            oldy = newy;
        }
        
        for(int i=0 ; i<xbody.size() ; i++){
            g.drawString("@", xbody.get(i).intValue() , ybody.get(i).intValue());
        }
        
        if (x == fx && y == fy) {
            score++;
            makeFood();
            g.drawString("#", fx, fy);
            xbody.add(oldx);
            ybody.add(oldy);
        }
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Snake_without_body.class.getName()).log(Level.SEVERE, null, ex);
        }

        prev = pressed;
        repaint();
    }

    int random(int i, int j) {
        Random r = new Random();
        int num = r.nextInt((j - i) + 1) + i;
        return num;
    }
    
    boolean check(int x , int y){
        for(int i=0 ; i<xbody.size() ; i++){
            int xx = xbody.get(i).intValue() , yy = ybody.get(i).intValue();
            if(x==xx || y==yy){
                return false;
            }
        }
        
        return true;
    }

    void makeFood() {
        int x = random(0, height / 10 - 2) * 10, y = random(0, width / 10 - 2) * 10;
        while(!check(x , y)){
            x = random(0, height / 10) * 10;
            y = random(0, width / 10) * 10;
        }

        fx = x;
        fy = y;
    }
    
    void initialize() {
        xbody.add(0);
        ybody.add(0);
        fx = 2;
        fy = 2;
    }
    
    void run(Snake_with_body f){
        f.setSize(f.height, f.width);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(f);
        f.initialize();
        f.makeFood();
    }

    public void keyTyped(KeyEvent e) {
        pressed = e.getKeyChar();
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}
