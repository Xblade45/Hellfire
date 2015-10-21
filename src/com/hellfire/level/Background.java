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
    
    private int width;
    private int height;
    
    //Background position
    private int posX;
    private int posY;
    
    //Vector
    private int dx;
    private int dy;
    
    //Speed
    private int scrollSpeed;
    
    public Background(int w, int h){
        
        this.width = w;
        this.height = h;
        
        init();
    }

    @Override
    public final void init() {
    
        image = ImageLoader.load("Backgrounds", "black");
    }

    @Override
    public void run() {}

    @Override
    public void update() {
    
        
    }

    @Override
    public void draw(Graphics g) {

    }
    
}
