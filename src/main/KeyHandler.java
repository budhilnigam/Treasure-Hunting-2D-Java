package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    public void keyTyped(KeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyChar());
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //System.out.println("Key Pressed: " + e.getKeyChar());
        if (code==KeyEvent.VK_W){
            upPressed=true;
        }
        if (code==KeyEvent.VK_S){
            downPressed=true;
        }
        if (code==KeyEvent.VK_A){
            leftPressed=true;
        }
        if (code==KeyEvent.VK_D){
            rightPressed=true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //System.out.println("Key Released: " + e.getKeyChar());
        if (code==KeyEvent.VK_W){
            upPressed=false;
        }
        if (code==KeyEvent.VK_S){
            downPressed=false;
        }
        if (code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if (code==KeyEvent.VK_D){
            rightPressed=false;
        }
    }
}
