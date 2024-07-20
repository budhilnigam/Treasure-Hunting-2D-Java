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

    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){    
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
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
            worldY-=speed;
        } else if(keyH.downPressed){
            direction="down";
            worldY+=speed;
        } else if(keyH.leftPressed){
            direction="left";
            worldX-=speed;
        } else if(keyH.rightPressed){
            direction="right";
            worldX+=speed;
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
