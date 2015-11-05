/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import com.hellfire.entity.Sprite;
import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public class Tile extends Sprite{
    
    private int type;
    
    // Constructor
    public Tile(int x, int y, BufferedImage image, int type){
        
        super(x, y, image);
        
        this.type = type;
    }
    
    public int getType(){
        return type;
    }
}
