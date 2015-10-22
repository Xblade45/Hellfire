/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.ImageLoader;
import com.hellfire.gamestate.InputListener;
import com.hellfire.main.Panel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Xblade45
 */
public class Spaceship extends Sprite implements GameEngine {

    Animation shipAnimation;
    Animation fireAnimation;

    private double dx;
    private double dy;
    private double speed;

    private int currentLaserType;
    private ArrayList lasers;

    private final int BOUNDS_PADDING = 10;
    private final int LASER_PADDING = 25;

    public static final int FRONT = 0;
    public static final int REAR = 1;
    public static final int UPDOWN = 2;
    public static final int DIAG = 3;

    private int fireCounter;
    private final int FIRE_DELAY = 5;

    private int laserSelectionCounter;
    private final int LASER_SELECTION_DELAY = 10;

    //constructor
    public Spaceship(int x, int y) {
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
        
        this.fireCounter = 0;
        this.laserSelectionCounter = 0;

        lasers = new ArrayList();
        this.currentLaserType = FRONT;

        this.speed = 15;
        this.dx = 0;
        this.dy = 0;
        this.posX -= width / 2;
        this.posY -= height / 2;
    }

    @Override
    public void run() {
    }

    @Override
    public void update() {
        
        //update counters
        fireCounter++;
        laserSelectionCounter++;

        //Input actions
        checkInput();

        //restart counters
        if (fireCounter > 60) {
            fireCounter = 0;
        }
        if (laserSelectionCounter > 60) {
            laserSelectionCounter = 0;
        }

        //update position of Spaceship
        shipAnimation.update();
        posX += dx * speed;
        posY += dy * speed;

        //Border Detection
        checkBounds();

        //reset Vector
        this.dx = 0;
        this.dy = 0;

        //Update each laser condition
        for (Object l1 : lasers) {
            Laser l = (Laser) l1;
            l.update();
        }
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(shipAnimation.getImage(), posX, posY, null);

        for (int i = 0; i < lasers.size(); i++) {

            Laser l = (Laser) lasers.get(i);

            if (l.isVisible()) {
                l.draw(g);
            } else {
                lasers.remove(i);
            }
        }
    }

    private void fire() {

        if (currentLaserType == FRONT) {
            lasers.add(new Laser(posX + width, posY + height / 2, Laser.EAST));
        }
        if (currentLaserType == REAR) {
            lasers.add(new Laser(posX - LASER_PADDING, posY + height / 2, Laser.WEST));
        }
        if (currentLaserType == UPDOWN) {
            lasers.add(new Laser(posX + width / 2, posY - 20, Laser.NORTH));
            lasers.add(new Laser(posX + width / 2, posY + height - 10, Laser.SOUTH));
        }
        if (currentLaserType == DIAG) {
            lasers.add(new Laser(posX, posY - 10, Laser.NW));
            lasers.add(new Laser(posX + width, posY - 10, Laser.NE));
            lasers.add(new Laser(posX, posY + height - 20, Laser.SW));
            lasers.add(new Laser(posX + width - 10, posY + height - 20, Laser.SE));
        }
    }

    private void changeLaser() {

        currentLaserType++;
        if (currentLaserType == DIAG + 1) {
            currentLaserType = FRONT;
        }
        System.out.println(currentLaserType);
    }

    private void checkBounds() {

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
            this.dy = -1;
        }
        if (InputListener.isDownPressed) {
            this.dy = 1;
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
