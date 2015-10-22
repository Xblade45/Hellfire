/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

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
    
    protected BufferedImage image;
    
    //constructor
    public Sprite(int x, int y){
        
        this.posX = x;
        this.posY = y;
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
    public BufferedImage getImage() {
        return image;
    }
}
