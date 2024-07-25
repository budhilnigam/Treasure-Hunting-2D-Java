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
            /*File f1 = new File("./res/player/pixil-frame-0.png");
            File f2 = new File("./res/player/pixil-frame-1.png");
            File f3 = new File("./res/player/pixil-frame-2.png");
            File f4 = new File("./res/player/pixil-frame-3.png");
            
            down1 = ImageIO.read(f1);
            down2 = ImageIO.read(f2);
            down3 = ImageIO.read(f3);
            down4 = ImageIO.read(f4);*/
            down1 = ImageIO.read(new File("./res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("./res/player/boy_down_2.png"));
            up1 = ImageIO.read(new File("./res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("./res/player/boy_up_2.png"));
            left1 = ImageIO.read(new File("./res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("./res/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("./res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("./res/player/boy_right_2.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
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
        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum==1){
                spriteNum=2;
            } else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image =null;
        switch (direction) {
            case "down":
                if(spriteNum==1){
                    image = down1;
                } else if(spriteNum==2){
                    image = down2;
                }
                break;
            case "up":
                if(spriteNum==1){
                    image = up1;
                } else if(spriteNum==2){
                    image = up2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                } else if(spriteNum==2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                } else if(spriteNum==2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
