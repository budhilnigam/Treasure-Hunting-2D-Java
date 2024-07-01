package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-2.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-3.png"));
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
        
        /*int radius[] = {118,40,90,40};
        int nPoints = 16;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];
        int max = 16;
        for (double current=0.0; current<nPoints; current++)
        {
            int i = (int) current;
            double a = Math.cos(current*((2*Math.PI)/max))*radius[i % 4];
            double b = Math.sin(current*((2*Math.PI)/max))*radius[i % 4];

            X[i] = (int) a+x;
            Y[i] = (int) b+y;
        }

        g2.setColor(Color.getColor("000"));
        //g2.fillPolygon(X, Y, nPoints);
        Polygon p = new Polygon();
        p.npoints=3;
        for(int i=0;i<3;i++){
            p.xpoints[i]=x+(i-1)*gp.tileSize/2;
            p.ypoints[i]=y+(i%2)*gp.tileSize/2;
        }
        g2.fillPolygon(p);*/
        /*g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/
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
