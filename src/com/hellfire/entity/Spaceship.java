/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.SpriteSheetLoader;
import com.hellfire.main.Panel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Xblade45
 */
public class Spaceship implements GameEngine, KeyListener {
    
    Animation shipAnimation;
    
    private int posX;
    private int posY;
    
    private double dx;
    private double dy;
    
    private double speed;
    
    
    public Spaceship(String folder, String file, int frames){
        
        init(folder, file, frames);
    }

    public final void init(String folder, String file, int frames) {

        shipAnimation = new Animation();
        loadAnimation(shipAnimation, folder, file, frames);
        
        this.speed = 10;
        this.dx = 0;
        this.dy = 0;
        this.posX = Panel.WIDTH*Panel.SCALE /2 - shipAnimation.getImage().getWidth()/2;
        this.posY = Panel.HEIGHT*Panel.SCALE /2 - shipAnimation.getImage().getHeight()/2;
    }
    @Override
    public void run() {}
    @Override
    public void update() {
        
        if(shipAnimation != null){
            shipAnimation.update();
            posX += dx*speed;
            posY += dy*speed;
        }
    }
    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        if(shipAnimation != null)
            g2d.drawImage(shipAnimation.getImage(), posX, posY, null);
    }
    
    public void loadAnimation(Animation a, String folder, String file, int frames){
        
        a.setFrames(SpriteSheetLoader.getAnimationTab(SpriteSheetLoader.loadSpriteSheet(folder, file), frames));
        a.start(100);
    }

    @Override
    public void init() {}

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A){
            this.dx = -1;
        }
        if(key == KeyEvent.VK_D){
            this.dx = 1;
        }
        if(key == KeyEvent.VK_W){
            this.dy = -1;
        }
        if(key == KeyEvent.VK_S){
            this.dy = 1;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A){
            this.dx = 0;
        }
        if(key == KeyEvent.VK_D){
            this.dx = 0;
        }
        if(key == KeyEvent.VK_W){
            this.dy = 0;
        }
        if(key == KeyEvent.VK_S){
            this.dy = 0;
        }
    }
    
}