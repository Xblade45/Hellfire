/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import com.hellfire.entity.Spaceship;
import com.hellfire.level.Background;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Xblade45
 */
public class Level1State extends GameState{
    
    Background background;
    
    Spaceship player;
    
    
    public Level1State(GameStateManager gsm){
        
        this.gsm = gsm;
        
        init();
    }
    
    @Override
    public final void init() {
        
        //Background
        background = new Background();
        
        //player
        player = new Spaceship("Spaceships", "Spaceship1", 5);
    }
    
    @Override
    public void draw(Graphics g) {
        
        //draw Background
        background.draw(g);
        
        //draw Player
        player.draw(g);
    }

    @Override
    public void update() {
    
        player.update();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
    
    @Override
    public void mousePressed(MouseEvent me) {}
}
