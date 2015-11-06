/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.ImageLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Xblade45
 */
public class Laser extends Sprite{
    
    private int rotation;
    private int currentDirection;
    

    //constructor
    public Laser(int x, int y, int direction, String sprite){
        
        super(x, y, ImageLoader.load(LASER, sprite));
        
        this.currentDirection = direction;
        
        init();
    }

    //methods
    public final void init() {

        this.speed = 15;
        
        setVectorAndRotation();
    }
    
    private void setVectorAndRotation(){
        
        switch(currentDirection){
        
            case NORTH:
                dx = 0;
                dy = -1;
                rotation = 90;
            break;
            case SOUTH:
                dx = 0;
                dy = 1;
                rotation = 90;
            break;
            case EAST:
                dx = 1;
                dy = 0;
                rotation = 0;
            break;
            case WEST:
                dx = -1;
                dy = 0;
                rotation = 0;
            break;
            case NW:
                dx = -1;
                dy = -1;
                rotation = 45;
            break;
            case NE:
                dx = 1;
                dy = -1;
                rotation = 135;
            break;
            case SW:
                dx = -1;
                dy = 1;
                rotation = 135;
            break;
            case SE:
                dx = 1;
                dy = 1;
                rotation = 45;
            break;
        }
    }
    
    @Override
    public void update() {
       
        super.update();
    }
    
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        AffineTransform trans = new AffineTransform();
        trans.rotate(Math.toRadians(rotation), posX, posY);
        g2d.transform(trans);
        g2d.drawImage(image, posX, posY, null);
        g2d.setTransform(backup);
    }
}
