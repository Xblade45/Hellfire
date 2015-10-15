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
public class Spaceship extends Sprite implements GameEngine{
    
    Animation shipAnimation;
    Animation fireAnimation;
    
    private double dx;
    private double dy;
    private double speed;
    
    private int currentLaserType;
    private ArrayList lasers;
    
    private final int BOUNDSPADDING = 10;
    private final int LASERPADDING = 25;
    
    public static final int FRONT = 0;
    public static final int REAR = 1;
    public static final int UPDOWN = 2;
    public static final int DIAG = 3;
    
    private int fireCounter;
    private final int FIREDELAY = 5;
    
    private int changeCounter;
    private final int CHANGEDELAY = 10;
    
    //constructor
    public Spaceship(int x, int y){
        super(x, y);
        init();
    }

    //Methods
    @Override
    public final void init() {

        shipAnimation = new Animation(
                ImageLoader.getAnimationTab(
                        ImageLoader.load("Spaceships", "Spaceship1"), 5));
        shipAnimation.start(100);
        
        this.width = shipAnimation.getImage().getWidth();
        this.height = shipAnimation.getImage().getHeight();
        fireCounter = 0;
        
        lasers = new ArrayList();
        currentLaserType = FRONT;

        this.speed = 15;
        this.dx = 0;
        this.dy = 0;
        this.posX -= width/2;
        this.posY -= height/2;
    }
    @Override
    public void run() {}
    @Override
    public void update() {
        
        fireCounter++;
        changeCounter++;

        //Input actions
        checkInput();
        
        //restart counters
        if(fireCounter > 60)
            fireCounter = 0;
        if(changeCounter > 60)
            changeCounter = 0;
        
        //update position of Spaceship
        shipAnimation.update();
        posX += dx*speed;
        posY += dy*speed;
        
        //Border Detection
        checkBounds();
        
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
        
        g2d.drawImage(shipAnimation.getImage(), posX, posY, null);
        
        for(int i=0; i<lasers.size(); i++){
            
            Laser l = (Laser) lasers.get(i);
            
            if(l.isVisible())
                g2d.drawImage(l.getImage(), l.getPosX(), l.getPosY(), null);
            else
                lasers.remove(i);
        }
    }
    
    private void fire(){
        
        if(currentLaserType == FRONT)
            lasers.add(new Laser(posX +width, posY +height/2, Laser.EAST));
        if(currentLaserType == REAR)
            lasers.add(new Laser(posX -LASERPADDING, posY +height/2, Laser.WEST));
        if(currentLaserType == UPDOWN){
            lasers.add(new Laser(posX +width/2 -15, posY, Laser.NORTH));
            lasers.add(new Laser(posX +width/2 -15, posY +height, Laser.SOUTH));
        }
        if(currentLaserType == DIAG){
            lasers.add(new Laser(posX -15, posY, Laser.NW));
            lasers.add(new Laser(posX -15 + width, posY, Laser.NE));
            lasers.add(new Laser(posX -15, posY +height, Laser.SW));
            lasers.add(new Laser(posX -15 +width, posY +height, Laser.SE));
        }
    }
    
    private void changeLaser(){
        currentLaserType++;
        if(currentLaserType == DIAG+1)
            currentLaserType = FRONT;
        System.out.println(currentLaserType);
    }
    
    private void checkBounds(){
        
        if(posX < 0 + BOUNDSPADDING)
            posX = 0 + BOUNDSPADDING;
        if(posY < 0 + BOUNDSPADDING)
            posY = 0 + BOUNDSPADDING;
        if(posY > Panel.getP_HEIGHT() - getHeight() - BOUNDSPADDING)
            posY = Panel.getP_HEIGHT() - getHeight() - BOUNDSPADDING;
        if(posX > Panel.getP_WIDTH() - getWidth() - BOUNDSPADDING)
            posX = Panel.getP_WIDTH() - getWidth() - BOUNDSPADDING;
    }
    
    private void checkInput(){
        
        if(Panel.isUpPressed)
            this.dy = -1;
        if(Panel.isDownPressed)
            this.dy = 1;
        if(Panel.isLeftPressed)
            this.dx = -0.85;
        if(Panel.isRightPressed)
            this.dx = 0.85;
        if(Panel.isFirePressed && fireCounter > FIREDELAY){
            fire();
            fireCounter = 0;
        }
        if(Panel.isChangePressed && changeCounter > CHANGEDELAY){
            changeLaser();
            changeCounter = 0;
        }
    }
}