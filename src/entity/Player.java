package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.awt.Polygon;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){    
        x=100;
        y=100;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try{
            File f1 = new File("./res/player/pixil-frame-0.png");
            File f2 = new File("./res/player/pixil-frame-1.png");
            File f3 = new File("./res/player/pixil-frame-2.png");
            File f4 = new File("./res/player/pixil-frame-3.png");
            
            down1 = ImageIO.read(f1);
            down2 = ImageIO.read(f2);
            down3 = ImageIO.read(f3);
            down4 = ImageIO.read(f4);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed){
            direction="up";
            y-=speed;
        } else if(keyH.downPressed){
            direction="down";
            y+=speed;
        } else if(keyH.leftPressed){
            direction="left";
            x-=speed;
        } else if(keyH.rightPressed){
            direction="right";
            x+=speed;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image =null;
        switch (direction) {
            case "up":
                image = down1;
                break;
            case "down":
                image = down2;
                break;
            case "left":
                image = down3;
                break;
            case "right":
                image = down4;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
