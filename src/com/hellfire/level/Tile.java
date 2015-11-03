/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public class Tile {
    
    private int type;
    
    private BufferedImage image;
    
    // Constructor
    public Tile(BufferedImage image, int type){
        
        this.image = image;
        this.type = type;
    }
    
    // Accessors
    public BufferedImage getImage(){
        return image;
    }
    
    public int getType(){
        return type;
    }
}
