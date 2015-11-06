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
    
    // Resources Directory
    public static final String BACKGROUND = "Backgrounds";
    public static final String ENNEMY = "Ennemies";
    public static final String LASER = "Lasers";
    public static final String SPACESHIP = "Spaceships";
    public static final String TILEMAP = "TileMap";
    
    // constructor
    public Sprite(int x, int y, String sprite, String directory, int nbFrames){
        
        this(x, y);
        this.dx = 0;
        this.dy = 0;
        
        initAnimation(sprite, directory, nbFrames);
        this.width = animation.getImage().getWidth();
        this.height = animation.getImage().getHeight();
    }
    
    public Sprite(int x, int y, BufferedImage image){
        
        this(x, y);
        this.image = image;

        this.width = image.getWidth();
        this.height = image.getHeight();
    }
    
    public Sprite(int x, int y){
                
        this.posX = x;
        this.posY = y;
    }
    
    public Sprite(){
        
    }
        
    // getter
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
    
    // setter
    public void setPosX(int x){
        this.posX = x;
    }
    public void setPosY(int y){
        this.posY = y;
    }
    
    // Methods
    protected void update(){
        
        //update position
        this.posX += dx*speed;
        this.posY += dy*speed;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(posX, posY, width, height);
    }
    
    private void initAnimation(String sprite, String directory, int nbFrames){
        
        animation = new Animation(
        ImageLoader.getAnimationTab(
                ImageLoader.load(directory, sprite), nbFrames));
        animation.start(100);
    }

}