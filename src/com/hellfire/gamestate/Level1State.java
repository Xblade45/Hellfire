/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import com.hellfire.entity.Spaceship;
import com.hellfire.level.Background;
import com.hellfire.main.Panel;
import java.awt.Graphics;

/**
 *
 * @author Xblade45
 */
public class Level1State extends GameState{
    
    Background background;
    Spaceship player;
    
    private final double SCROLL = 5;
    
    public Level1State(GameStateManager gsm){
        
        this.gsm = gsm;
        
        init();
    }
    
    @Override
    public final void init() {
        
        background = new Background("background1", SCROLL);
        player = new Spaceship(Panel.getP_WIDTH()/2, Panel.getP_HEIGHT()/2);//Center of panel
    }
    
    @Override
    public void draw(Graphics g) {
        
        background.draw(g);
        player.draw(g);
    }

    @Override
    public void update() {
        
        background.update();
        player.update();
    }
}
