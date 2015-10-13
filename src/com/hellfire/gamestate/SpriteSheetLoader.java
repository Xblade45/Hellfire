/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Xblade45
 */
public class SpriteSheetLoader {
    
    public static BufferedImage loadSpriteSheet(String folder, String inputFile){
        
        BufferedImage sprite = null;
        
        try{
            sprite = ImageIO.read(new File("resources/" + folder + "/" + inputFile + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return sprite;
    }
    
    public static BufferedImage[] getAnimationTab(BufferedImage img){
        
        int tileSize = img.getHeight();
        
        int frames = img.getWidth() / tileSize;
        
        BufferedImage[] animation = new BufferedImage[frames];
        
        for(int i=0; i<frames; i++)
            animation[i] = img.getSubimage(i*tileSize, 0, tileSize, tileSize);
        
        return animation;
    }
}

