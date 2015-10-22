/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Xblade45
 */
public class ImageLoader {
    
    
    public static BufferedImage load(String folder, String file){
        
        BufferedImage img = null;
        
        try{
            img = ImageIO.read(ImageLoader.class.getClassLoader().getResource(folder + "/" + file + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
    
    public static BufferedImage[] getAnimationTab(BufferedImage img, int frames){

        int tileWidth = img.getWidth() / frames;
        
        int tileHeight = img.getHeight();
        
        BufferedImage[] animation = new BufferedImage[frames];
        
        for(int i=0; i<frames; i++)
            animation[i] = img.getSubimage(i*tileWidth, 0, tileWidth, tileHeight);
        
        return animation;
    }
    
    public static BufferedImage[][] getTileTab(BufferedImage img, int tileSize){

        int numCol = img.getWidth() / tileSize;
        int numRow = img.getHeight() / tileSize;
        
        BufferedImage[][] tileTab = new BufferedImage[numCol][numRow];
        
        for(int j=0; j<numRow; j++){// Y axis
            
            for(int i=0; i<numCol; i++){// X axis
                
                tileTab[i][j] = img.getSubimage(i, j, tileSize, tileSize);
            }
        }
        return tileTab;
    }
}

