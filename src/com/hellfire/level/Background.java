/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.ImageLoader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public class Background implements GameEngine {
    
    private BufferedImage image;
    
    //Background position
    private int posX;
    private int posY;
    
    //Vector
    private int dx;
    private int dy;
    
    //Speed
    private double scrollSpeed;
    
    //Constructor
    public Background(String file, double scrollSpeed){
        
        this.scrollSpeed = scrollSpeed;
        
        image = ImageLoader.load("Backgrounds", file);
        
        init();
    }
    
    //Accessors
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    
    //Methods
    @Override
    public final void init() {
        
        this.posX = 0;
        this.posY = 0;
        this.dx = -1;
        this.dy = 0;
    }

    @Override
    public void run() {}

    @Override
    public void update() {
        
        this.posX += dx*scrollSpeed;
        this.posY += dy*scrollSpeed;
    }

    @Override
    public void draw(Graphics g) {
        
        g.drawImage(image, posX, posY, null);
        
        if(posX < 0)
            g.drawImage(image, posX+image.getWidth(), posY, null);
        
        if(posX < -image.getWidth())
            posX = 0;
    }
    
}
