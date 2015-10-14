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
public class ImageLoader {
    
    
    public static BufferedImage load(String folder, String inputFile){
        
        BufferedImage img = null;
        
        try{
            img = ImageIO.read(new File("resources/" + folder + "/" + inputFile + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
    
    public static BufferedImage[] getAnimationTab(BufferedImage img, int frames){
        
        int tileHeight = img.getHeight();
        
        int tileWidth = img.getWidth() / frames;
        
        BufferedImage[] animation = new BufferedImage[frames];
        
        for(int i=0; i<frames; i++)
            animation[i] = img.getSubimage(i*tileWidth, 0, tileWidth, tileHeight);
        
        return animation;
    }
}

