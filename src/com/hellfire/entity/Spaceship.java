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

/**
 *
 * @author Xblade45
 */
public class Spaceship implements GameEngine {
    
    Animation shipAnimation;
    
    int posX;
    int posY;
    
    public Spaceship(String folder, String file, int frames){
        
        init(folder, file, frames);
    }

    public final void init(String folder, String file, int frames) {

        shipAnimation = new Animation();
        loadAnimation(shipAnimation, folder, file, frames);
        
        this.posX = Panel.WIDTH*Panel.SCALE /2 - shipAnimation.getImage().getWidth()/2;
        this.posY = Panel.HEIGHT*Panel.SCALE /2 - shipAnimation.getImage().getHeight()/2;
    }
    @Override
    public void run() {}
    @Override
    public void update() {
        
        shipAnimation.update();
    }
    @Override
    public void draw(Graphics g) {
        
        g.drawImage(shipAnimation.getImage(), posX, posY, null);
    }
    
    public void loadAnimation(Animation a, String folder, String file, int frames){
        
        a.setFrames(SpriteSheetLoader.getAnimationTab(SpriteSheetLoader.loadSpriteSheet(folder, file), frames));
        a.start(100);
    }

    @Override
    public void init() {}
    
}
