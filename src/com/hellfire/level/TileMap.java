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
    private int[][] intMap;
    
    private final int TILESIZE = 64;
    
    private final int BLOCKED = 1;
    private final int NORMAL = 0;
    
    public TileMap(String tilesetImage, double speed){
        
        super(0, 0);
        
        this.speed = speed;
        
        tileset = ImageLoader.getTileTab(ImageLoader.load("TileMap", tilesetImage), TILESIZE);
        
        init();
    }
    
    private void init(){
        
        this.posX = 0;
        this.posY = 0;
        this.dx = -1;
        this.dy = 0;
        
        tileMap = new Tile[40][40];
        intMap = new int[40][40];
        
        loadTileMap(intMap);
        convertIntToTileMap(intMap, tileMap, tileset);
    }
    
    @Override
    public void update(){
        
        this.posX += dx*speed;
        this.posY += dy*speed;
    }
    
    public void draw(Graphics g){
        
        for(int i=0; i<40; i++){
            
            for(int j=0; j<40; j++){
                
                if(j*TILESIZE+(int)posX+TILESIZE>=0 && j*TILESIZE+(int)posX < Panel.getP_WIDTH()+TILESIZE)
                    g.drawImage(tileset[intMap[i][j]], j*TILESIZE+(int)posX, i*TILESIZE+(int)posY, null);
            }
        }
    }
    
    public void getCollision(Rectangle bounds){
        for(int row=0; row<40; row++){
            
            for(int col=0; col<40; col++){
                
                
            }
        }
    }
    
    private void loadTileMap(int[][] intMap){
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(new File("resources/TileMap/level1.txt")));
            
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
    }
    
    private void convertIntToTileMap(int[][] intMap, Tile[][] tileMap, BufferedImage[] tileset){
        
        for(int row=0; row<40; row++){
            
            for(int col=0; col<40; col++){
                
                Tile t = new Tile(tileset[intMap[row][col]], BLOCKED);
                tileMap[row][col] = t;
            }
        }
    }
}
