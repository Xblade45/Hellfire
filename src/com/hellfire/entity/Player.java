/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.InputListener;
import com.hellfire.main.Panel;
import java.awt.Graphics;

/**
 *
 * @author Xblade45
 */
public class Player extends Spaceship {
        
    private int currentLaserType;
    
    private final int BOUNDS_PADDING = 10;
    
    private int laserSelectionCounter;
    private final int LASER_SELECTION_DELAY = 10;
    
    protected static final int FRONT = 0;
    protected static final int REAR = 1;
    protected static final int UPDOWN = 2;
    protected static final int DIAG = 3;
    
    
    public Player(int x, int y, String sprite){
        
        super(x, y, sprite);
        
        init();
    }
    
    private void init() {
        
        this.laserSelectionCounter = 0;

        this.currentLaserType = FRONT;

        this.speed = 15;
        this.dx = 0;
        this.dy = 0;
        this.posX -= width / 2;
        this.posY -= height / 2;
    }
    
    @Override
    public void update() {
        
        //update counters
        laserSelectionCounter++;

        //Input actions
        checkInput();
        
        //update position
        super.update();
        
        //reset Vector
        this.dx = 0;
        this.dy = 0;
        
        //restart counters
        if (laserSelectionCounter > 60)
            laserSelectionCounter = 0;

        //Border Detection
        checkPanelBounds();
    } 
    
    @Override
    public void draw(Graphics g){
        
        super.draw(g);
    }
    
    private void fire() {

        if (currentLaserType == FRONT) {
            lasers.add(new Laser(posX + width, posY + height / 2, Laser.EAST, "laser1", "Lasers"));
        }
        if (currentLaserType == REAR) {
            lasers.add(new Laser(posX - LASER_PADDING, posY + height / 2, Laser.WEST, "laser1", "Lasers"));
        }
        if (currentLaserType == UPDOWN) {
            lasers.add(new Laser(posX + width / 2, posY - 20, Laser.NORTH, "laser1", "Lasers"));
            lasers.add(new Laser(posX + width / 2, posY + height - 10, Laser.SOUTH, "laser1", "Lasers"));
        }
        if (currentLaserType == DIAG) {
            lasers.add(new Laser(posX, posY - 10, Laser.NW, "laser1", "Lasers"));
            lasers.add(new Laser(posX + width, posY - 10, Laser.NE, "laser1", "Lasers"));
            lasers.add(new Laser(posX, posY + height - 20, Laser.SW, "laser1", "Lasers"));
            lasers.add(new Laser(posX + width - 10, posY + height - 20, Laser.SE, "laser1", "Lasers"));
        }
    }

    private void changeLaser() {

        currentLaserType++;
        if (currentLaserType == DIAG + 1) {
            currentLaserType = FRONT;
        }
        System.out.println(currentLaserType);
    }

    private void checkPanelBounds() {

        if (posX < 0 + BOUNDS_PADDING) {
            posX = 0 + BOUNDS_PADDING;
        }
        if (posY < 0 + BOUNDS_PADDING) {
            posY = 0 + BOUNDS_PADDING;
        }
        if (posY > Panel.getP_HEIGHT() - getHeight() - BOUNDS_PADDING) {
            posY = Panel.getP_HEIGHT() - getHeight() - BOUNDS_PADDING;
        }
        if (posX > Panel.getP_WIDTH() - getWidth() - BOUNDS_PADDING) {
            posX = Panel.getP_WIDTH() - getWidth() - BOUNDS_PADDING;
        }
    }

    private void checkInput() {

        if (InputListener.isUpPressed) {
            this.dy = -0.85;
        }
        if (InputListener.isDownPressed) {
            this.dy = 0.85;
        }
        if (InputListener.isLeftPressed) {
            this.dx = -0.85;
        }
        if (InputListener.isRightPressed) {
            this.dx = 0.85;
        }
        if (InputListener.isFirePressed && fireCounter > FIRE_DELAY) {
            fire();
            fireCounter = 0;
        }
        if (InputListener.isChangePressed && laserSelectionCounter > LASER_SELECTION_DELAY) {
            changeLaser();
            laserSelectionCounter = 0;
        }
    }
}
