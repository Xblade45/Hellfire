/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.level;

import com.hellfire.entity.Sprite;
import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.ImageLoader;
import java.awt.Graphics;

/**
 *
 * @author Xblade45
 */
public class Background extends Sprite implements GameEngine {
    
    //Constructor
    public Background(String file){
        
        super();
        
        image = ImageLoader.load(BACKGROUND, file);
        
        init();
    }
    
    public Background(String file, double speed, int dx, int dy){
        
        this(file);
        
        this.speed = speed;
        this.dx = dx;
        this.dy = dy;
    }
    
    //Accessors
    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    
    //Methods
    @Override
    public final void init() {
        
        this.posX = 0;
        this.posY = 0;
        this.dx = 0;
        this.dy = 0;
        this.speed = 0;
    }

    @Override
    public void run() {}

    @Override
    public void update() {
        
        this.posX += dx*speed;
        this.posY += dy*speed;
    }

    @Override
    public void draw(Graphics g) {
        
        g.drawImage(image, posX, posY, null);
        
        if(posX < 0)
            g.drawImage(image, posX+image.getWidth(), posY, null);
        
        if(posX < -image.getWidth())
            posX = 0;
    }
    
}
