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
import java.awt.Rectangle;
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
    
    private final int BLOCKED = 1;
    private final int NORMAL = 0;
    
    public TileMap(String tilesetImage, String tileMapFile, double speed){
        
        super(0, 0);
        init();
        
        this.tileset = ImageLoader.getTileTab(ImageLoader.load("TileMap", tilesetImage), TILESIZE);
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
    
    
    public boolean getCollision(Rectangle input){
        
        for(int row=0; row<40; row++){
            
            for(int col=0; col<40; col++){
                
                if(tileMap[row][col].getType() == 1 && input.intersects(tileMap[row][col].getBounds()))
                    return true;
            }
        }
        return false;
    }
    
    private Tile[][] loadTileMap(String tileMapFile){
        
        int[][] intMap = new int[40][40];
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(new File("resources/TileMap/" + tileMapFile + ".txt")));
            
            String delimiter = "\\s+";
            int counter = 0;
            
            String line;
            
            while((line = br.readLine()) != null){
                
                String[] tokens = line.split(delimiter);
                
                for(int j=0; j<tokens.length; j++){
                    
                    int token = Integer.parseInt(tokens[j]);
                    
                    intMap[counter][j] = token;
                }
                counter++;
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
                
                Tile t = new Tile(posX + (col*TILESIZE), posY + (row*TILESIZE), tileset[intMap[row][col]], BLOCKED);
                tabT[row][col] = t;
            }
        }
        return tabT;
    }
}
