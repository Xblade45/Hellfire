/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import com.hellfire.entity.Sprite;
import com.hellfire.gamestate.ImageLoader;
import com.hellfire.main.Panel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Xblade45
 */
public class TileMap extends Sprite {
    
    private BufferedImage[] tileset;
    private Tile[][] tileMap;
    
    private final int TILESIZE = 64;
    
    private final int NORMAL = 0;
    private final int BLOCKED = 1;
    
    
    public TileMap(String tilesetImage, String tileMapFile, double speed){
        
        super(0, 0);
        init();
        
        this.tileset = ImageLoader.getTileTab(ImageLoader.load(TILEMAP, tilesetImage), TILESIZE);
        this.tileMap = loadTileMap(tileMapFile);
        this.speed = speed;
    }
    
    private void init(){
        
        this.posX = 0;
        this.posY = 0;
        this.dx = -1;
        this.dy = 0;
    }
    
    @Override
    public void update(){
        
        this.posX += dx*speed;
        this.posY += dy*speed;
        
        for(int row=0; row<40; row++){

            for(int col=0; col<40; col++){

                tileMap[row][col].setPosX(posX + (col*TILESIZE));
                tileMap[row][col].setPosY(posY + (row*TILESIZE));
            }
        }
    }
    
    public Tile getTile(int col, int row){
        return tileMap[row][col];
    }
    
    public void draw(Graphics g){
        
        for(int row=0; row<40; row++){

            for(int col=0; col<40; col++){

                if(col*TILESIZE+(int)posX+TILESIZE>=0 && col*TILESIZE+(int)posX < Panel.getP_WIDTH()+TILESIZE)
                    g.drawImage(tileMap[row][col].getImage(), col*TILESIZE+(int)posX, row*TILESIZE+(int)posY, null);
            }
        }
    }
    
    public boolean getCollision(Sprite sprite){
        
        boolean b = false;
        
        for(int row=0; row<40; row++){
            
            for(int col=0; col<40; col++){
                
                if(tileMap[row][col].getType() == BLOCKED && sprite.getBounds().intersects(tileMap[row][col].getBounds()))
                    b = true;
            }
        }
        return b;
    }
    
    private Tile[][] loadTileMap(String tileMapFile){
        
        int[][] intMap = new int[40][40];
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(new File("resources/" + TILEMAP + "/" + tileMapFile + ".txt")));
            
            String delimiter = "\\s+";
            
            int row = 0;
            String line;
            
            while((line = br.readLine()) != null){
                
                String[] tokens = line.split(delimiter);
                
                for(int j=0; j<tokens.length; j++){
                    
                    int token = Integer.parseInt(tokens[j]);
                    
                    intMap[row][j] = token;
                }
                row++;
            }
            br.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return convertIntToTileMap(intMap);
    }
    
    private Tile[][] convertIntToTileMap(int[][] intMap){
        
        Tile[][] tabT = new Tile [40][40];
        
        for(int row=0; row<40; row++){
            
            for(int col=0; col<40; col++){
                
                Tile t = new Tile(posX + (col*TILESIZE), posY + (row*TILESIZE), tileset[intMap[row][col]], intMap[row][col]);
                tabT[row][col] = t;
            }
        }
        return tabT;
    }
}
