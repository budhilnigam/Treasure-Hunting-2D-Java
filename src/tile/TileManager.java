package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum= new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap("./res/maps/world01.txt");
    }

    public void getTileImage(){
        try {
            tile[0]=new Tile();
            tile[0].image=ImageIO.read(new File("./res/tiles/grass.png"));

            tile[1]=new Tile();
            tile[1].image=ImageIO.read(new File("./res/tiles/wall.png"));

            tile[2]=new Tile();
            tile[2].image=ImageIO.read(new File("./res/tiles/water.png"));

            tile[3]=new Tile();
            tile[3].image=ImageIO.read(new File("./res/tiles/water.png"));//earth

            tile[4]=new Tile();
            tile[4].image=ImageIO.read(new File("./res/tiles/water.png"));//tree

            tile[5]=new Tile();
            tile[5].image=ImageIO.read(new File("./res/tiles/water.png"));//sand

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /*public void draw(Graphics2D g2){
        //*
        g2.drawImage(tile[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 0, gp.tileSize, gp.tileSize, null);
        
        g2.drawImage(tile[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 48, gp.tileSize, gp.tileSize, null);
        
        g2.drawImage(tile[1].image, 0, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 192, 96, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 144, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 48, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 144, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 192, gp.tileSize, gp.tileSize, null);
        //*
        int col=0;
        int row=0;
        int x=0;
        int y=0;
        while(col<gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize, null);
            x+=gp.tileSize;
            col++;
            if(col==gp.maxScreenCol){
                col=0;
                row++;
                x=0;
                y+=gp.tileSize;
            }
        }
    }*/
    public void loadMap(String filePath){
        try {
            FileInputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is,StandardCharsets.UTF_8));
            int col=0;
            int row=0;
            while(col<gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col<gp.maxScreenCol){
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt((numbers[col]));
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxScreenCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;
        while(worldCol<gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum=mapTileNum[worldCol][worldRow];
            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
            if(worldCol!=gp.maxScreenCol){
                worldCol=0;
                worldRow++;
            }
        }
    }  
}
