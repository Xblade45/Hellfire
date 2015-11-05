/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.ImageLoader;
import java.awt.Rectangle;
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
        
        this(x, y);
        this.dx = 0;
        this.dy = 0;
        
        initImages(sprite, directory, nbFrames);
    }
    
    public Sprite(int x, int y, BufferedImage image){
        
        this(x, y);
        this.image = image;
    }
    
    public Sprite(int x, int y){
                
        this.posX = x;
        this.posY = y;
    }
    
    protected void update(){
        
        //update position
        this.posX += dx*speed;
        this.posY += dy*speed;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(posX, posY, width, height);
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
    public BufferedImage getImage(){
        return image;
    }
}