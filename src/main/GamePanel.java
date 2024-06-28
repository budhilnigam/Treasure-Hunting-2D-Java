package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale =3;//Scale of figures

    final int tileSize = originalTileSize*scale; //48 x 48 tile
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    int playerX=100;
    int playerY=100;
    int playerSpeed=4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run(){
        while(gameThread!=null){
            //System.out.println("The game loop is running");
            update();
            repaint();
        }
    }
    public void update(){
        if(keyH.upPressed){
            playerY-=playerSpeed;
        } else if(keyH.downPressed){
            playerY+=playerSpeed;
        } else if(keyH.leftPressed){
            playerX-=playerSpeed;
        } else if(keyH.rightPressed){
            playerX+=playerSpeed;
        }

        System.out.println("The game is updating");
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
