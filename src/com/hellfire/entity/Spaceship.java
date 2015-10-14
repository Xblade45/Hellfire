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
import java.util.ArrayList;

/**
 *
 * @author Xblade45
 */
public class Spaceship implements GameEngine{
    
    Animation shipAnimation;
    
    private int posX;
    private int posY;
    private double dx;
    private double dy;

    private double speed;
    
    private int width;
    private int height;
    
    private int padding;

    private int currentLaserType;
    private ArrayList lasers;
    
    public static final int FRONT = 0;
    public static final int REAR = 1;
    public static final int UPDOWN = 2;
    public static final int DIAG = 3;
    
    private int fireCounter;
    private final int FIREDELAY = 5;
    
    //constructor
    public Spaceship(String folder, String file, int frames){
        
        init(folder, file, frames);
    }

    //Getters
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

    //Methods
    public final void init(String folder, String file, int frames) {

        shipAnimation = new Animation();
        loadAnimation(shipAnimation, folder, file, frames);
        
        this.width = shipAnimation.getImage().getWidth();
        this.height = shipAnimation.getImage().getHeight();
        
        padding = 10;
        fireCounter = 0;
        
        lasers = new ArrayList();
        currentLaserType = FRONT;

        this.speed = 15;
        this.dx = 0;
        this.dy = 0;
        this.posX = Panel.WIDTH*Panel.SCALE /2 - shipAnimation.getImage().getWidth()/2;
        this.posY = Panel.HEIGHT*Panel.SCALE /2 - shipAnimation.getImage().getHeight()/2;
    }
    @Override
    public void run() {}
    @Override
    public void update() {
        
        fireCounter++;
        
        if(Panel.isUpPressed)
            this.dy = -1;
        if(Panel.isDownPressed)
            this.dy = 1;
        if(Panel.isLeftPressed)
            this.dx = -1;
        if(Panel.isRightPressed)
            this.dx = 1;
        if(Panel.isFirePressed && fireCounter > FIREDELAY){
            fire();
            fireCounter = 0;
        }
        
        //restart firecounter
        if(fireCounter > 60)
            fireCounter = 0;
        
        //update position of Spaceship
        if(shipAnimation != null){
            shipAnimation.update();
            posX += dx*speed;
            posY += dy*speed;
        }
        
        //Border Detection
        if(posX < 0 + padding)
            posX = 0 + padding;
        if(posY < 0 + padding)
            posY = 0 + padding;
        if(posY > Panel.HEIGHT*Panel.SCALE - getHeight() - padding)
            posY = Panel.HEIGHT*Panel.SCALE - getHeight() - padding;
        if(posX > Panel.WIDTH*Panel.SCALE - getWidth() - padding)
            posX = Panel.WIDTH*Panel.SCALE - getWidth() - padding;
        
        //reset Vector
        this.dx = 0;
        this.dy = 0;
        
        //Update each laser condition
        for(Object l1 : lasers){

            Laser l = (Laser) l1;
                l.update();
        }
    }
    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        if(shipAnimation != null)
            g2d.drawImage(shipAnimation.getImage(), posX, posY, null);
        
        for(int i=0; i<lasers.size(); i++){
            
            Laser l = (Laser) lasers.get(i);
            
            if(l.isVisible())
                g2d.drawImage(l.getLaserImg(), l.getPosX(), l.getPosY(), null);
            else
                lasers.remove(i);
        }
    }
    
    private void fire(){
        
        lasers.add(new Laser("Lasers", currentLaserType, this));
    }
    
    public void loadAnimation(Animation a, String folder, String file, int frames){
        
        a.setFrames(ImageLoader.getAnimationTab(ImageLoader.load(folder, file), frames));
        a.start(100);
    }

    @Override
    public void init() {}
}