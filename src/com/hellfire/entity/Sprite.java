/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.ImageLoader;
import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public abstract class Sprite {
    
    protected int posX;
    protected int posY;
    
    protected int width;
    protected int height;
    
    protected double dx;
    protected double dy;
    protected double speed;
    
    protected Animation animation;
    protected BufferedImage image;
    
    //constructor
    public Sprite(int x, int y, String sprite, String directory, int nbFrames){
        
        this.posX = x;
        this.posY = y;
        this.dx = 0;
        this.dy = 0;
        
        initImages(sprite, directory, nbFrames);
    }
    
    protected void update(){
        
        //update position
        this.posX += dx*speed;
        this.posY += dy*speed;
    }
    
    private void initImages(String sprite, String directory, int nbFrames){
        
        animation = new Animation(
        ImageLoader.getAnimationTab(
                ImageLoader.load(directory, sprite), nbFrames));
        animation.start(100);

        this.width = animation.getImage().getWidth();
        this.height = animation.getImage().getHeight();
    }
    
    //getter
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
