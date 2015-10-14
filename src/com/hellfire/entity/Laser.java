/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.ImageLoader;
import com.hellfire.main.Panel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public class Laser implements GameEngine {
    
    private int posX;
    private int posY;
    
    private double dx;
    private double dy;
    
    private double speed;
    
    private int laserType;
    
    private BufferedImage laserImg;
    
    //constructor
    public Laser(String folder, int laserType, Spaceship s){
        
        init(folder, laserType, s);
    }

    //getter
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public BufferedImage getLaserImg() {
        return laserImg;
    }

    //methods
    public final void init(String folder, int laserType, Spaceship s) {
        
        this.laserType = laserType;
        this.dx = 0;
        this.dy = 0;
        this.speed = 15;
        this.posX = s.getPosX() + s.getWidth();
        this.posY = s.getPosY() + s.getHeight()/2;
        
        laserImg = ImageLoader.load(folder, getLaserFile(laserType));
    }
    
    public boolean isVisible(){
        
        return posX < Panel.WIDTH*Panel.SCALE 
            && posX > 0
            && posY > 0 
            && posY < Panel.HEIGHT*Panel.SCALE;
    }
    
    private String getLaserFile(int type){
        
        String result = "";
        
        switch(type){
        
            case Spaceship.FRONT:
                result = "laserFront";
            break;
            case Spaceship.REAR:
                result = "laserRear";
            break;
            case Spaceship.UPDOWN:
                result = "laserUpDown";
            break;
            case Spaceship.DIAG:
                result = "laserDiag";
            break;
        }
        return result;
    }
    
    @Override
    public void run() {}
    @Override
    public void update() {
    
        if(laserType == Spaceship.FRONT){
            dx = 1;
        }
        if(laserType == Spaceship.REAR){
        }
        if(laserType == Spaceship.UPDOWN){
        }
        if(laserType == Spaceship.DIAG){
        }
        
        posX += dx*speed;
        posY += dy*speed;
    }
    @Override
    public void draw(Graphics g) {
    
        g.drawImage(laserImg, posX, posX, null);
    }
    @Override
    public void init() {}
    
}
