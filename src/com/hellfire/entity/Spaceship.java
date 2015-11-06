/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Xblade45
 */
public abstract class Spaceship extends Sprite {

    protected ArrayList lasers;

    protected final int LASER_PADDING = 25;

    protected int fireCounter;
    protected final int FIRE_DELAY = 5;

    //constructor
    public Spaceship(int x, int y, String sprite) {
        
        super(x, y, sprite, SPACESHIP, 5);
        
        init();
    }

    //Methods
    private void init() {
        
        this.fireCounter = 0;

        lasers = new ArrayList();
    }
    
    @Override
    public void update(){
        
        //update counters
        fireCounter++;
        
        //restart counters
        if (fireCounter > 60)
            fireCounter = 0;

        //update position of Spaceship
        animation.update();
        super.update();
        
        //Update each laser condition
        for (Object l1 : lasers) {
            Laser l = (Laser) l1;
            l.update();
        }
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(animation.getImage(), posX, posY, null);

        for (int i = 0; i < lasers.size(); i++) {

            Laser l = (Laser) lasers.get(i);

            if (l.isVisible())
                l.draw(g);
            else
                lasers.remove(i);
        }
    }
   
    public boolean getCollision(Sprite sprite){
        return sprite.getBounds().intersects(getBounds());
    }
}
