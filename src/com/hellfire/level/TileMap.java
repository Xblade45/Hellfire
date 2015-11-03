/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import com.hellfire.gamestate.ImageLoader;
import com.hellfire.main.Panel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Xblade45
 */
public class TileMap {
    
    private double posX;
    private double posY;
    private double dx;
    private double dy;
    private double scrollSpeed;
    
    private BufferedImage[] tileset;
    
    private Tile[][] tileMap;
    
    private static final int TILESIZE = 32;
    
    private final int BLOCKED = 1;
    private final int NORMAL = 0;
    
    public TileMap(String tilesetImage, double scrollSpeed){
        
        this.scrollSpeed = scrollSpeed;
        
        tileset = ImageLoader.getTileTab(ImageLoader.load("TileMap", tilesetImage), TILESIZE);
        
        init();
    }
    
    private void init(){
        
        this.posX = 0;
        this.posY = 0;
        this.dx = -1;
        this.dy = 0;
        
        tileMap = new Tile[Panel.getP_WIDTH()/TILESIZE][Panel.getP_HEIGHT()/TILESIZE];
        
        loadTileMap();
    }
    
    public void update(){
        
        this.posX = dx*scrollSpeed;
        this.posY = dy*scrollSpeed;
    }
    
    public void draw(Graphics g){
        
        for(int i=0; i<40; i++){
            
            for(int j=0; j<40; j++){
                
                g.drawImage(tileMap[i][j].getImage(), i*TILESIZE, j*TILESIZE, null);
            }
        }
    }
    
    private void loadTileMap(){
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(new File("TileMap/level1.txt")));
            
            String delimiter = " ";
            int counter = 0;
            
            while(br.readLine() != null){
                String line = br.readLine();
                String[] tokens = line.split(delimiter);
                
                for(int i=0; i<tokens.length; i++){
                    
                    int token = Integer.parseInt(tokens[i]);
                    
                    tileMap[counter][i] = new Tile((token==0? tileset[0]:tileset[1]), (token==0? NORMAL:BLOCKED));
                }
                counter++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
