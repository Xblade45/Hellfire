/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.ImageLoader;
import com.hellfire.main.Panel;
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
    
    // Direction
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    public static final int NW = 4;
    public static final int NE = 5;
    public static final int SW = 6;
    public static final int SE = 7;
    
    // constructor
    public Sprite(int x, int y, String sprite, String directory, int nbFrames){
        // With Animation
        
        this(x, y);
        this.dx = 0;
        this.dy = 0;
        
        initAnimation(sprite, directory, nbFrames);
        
        this.image = animation.getFrames(0);
        this.width = animation.getImage().getWidth();
        this.height = animation.getImage().getHeight();
    }
    
    public Sprite(int x, int y, BufferedImage image){
        // With 1 Image
        
        this(x, y);
        this.image = image;

        this.width = image.getWidth();
        this.height = image.getHeight();
    }
    
    public Sprite(int x, int y){
        // default constructor#2
                
        this.posX = x;
        this.posY = y;
    }
    
    public Sprite(){
        // default constructor#1
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
    
    public boolean isVisible(){
        
        return posX < Panel.getP_WIDTH()
            && posX > 0
            && posY > 0 
            && posY < Panel.getP_HEIGHT();
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