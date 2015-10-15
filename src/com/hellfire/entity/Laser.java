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
import java.awt.Graphics2D;

/**
 *
 * @author Xblade45
 */
public class Laser extends Sprite implements GameEngine {
    
    private double dx;
    private double dy;
    
    private double speed;
    private int rotation;
    
    private int currentDirection;
    
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    public static final int NW = 4;
    public static final int NE = 5;
    public static final int SW = 6;
    public static final int SE = 7;
    
    //constructor
    public Laser(int x, int y, int direction){
        super(x, y);
        
        this.currentDirection = direction;
        
        init();
    }

    //methods
    @Override
    public final void init() {

        this.speed = 20;
        this.rotation = 0;
        
        setVector();
        image = ImageLoader.load("Lasers", "laser1");
    }
    
    public boolean isVisible(){
        
        return posX < Panel.getP_WIDTH()
            && posX > 0
            && posY > 0 
            && posY < Panel.getP_HEIGHT();
    }
    
    private void setVector(){
        
        switch(currentDirection){
        
            case NORTH:
                dx = 0;
                dy = -1;
            break;
            case SOUTH:
                dx = 0;
                dy = 1;
            break;
            case EAST:
                dx = 1;
                dy = 0;
            break;
            case WEST:
                dx = -1;
                dy = 0;
            break;
            case NW:
                dx = -1;
                dy = -1;
            break;
            case NE:
                dx = 1;
                dy = -1;
            break;
            case SW:
                dx = -1;
                dy = 1;
            break;
            case SE:
                dx = 1;
                dy = 1;
            break;
        }
    }
    
    @Override
    public void run() {}
    @Override
    public void update() {
        
        posX += dx*speed;
        posY += dy*speed;
    }
    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(rotation));
        g2d.drawImage(image, posX, posX, null);
    }
    
}
