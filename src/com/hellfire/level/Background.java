/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.main.Panel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public class Background implements GameEngine {
    
    private BufferedImage image;
    
    public Background(){
        
        init();
    }

    @Override
    public final void init() {
    
        //image = SpriteSheetLoader.loadSpriteSheet("Backgrounds", "bg1");
    }

    @Override
    public void run() {}

    @Override
    public void update() {}

    @Override
    public void draw(Graphics g) {
    
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Panel.WIDTH*Panel.SCALE, Panel.HEIGHT*Panel.SCALE);
        //g.drawImage(image, Panel.WIDTH, Panel.HEIGHT, null);
    }
    
}
