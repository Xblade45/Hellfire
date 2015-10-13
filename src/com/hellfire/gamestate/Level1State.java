/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Xblade45
 */
public class Level1State extends GameState{
    
    
    
    public Level1State(GameStateManager gsm){
        
        this.gsm = gsm;
        
        init();
    }
    
    @Override
    public final void init() {
        
        
    }
    
    @Override
    public void draw(Graphics g) {
        
        
    }

    @Override
    public void update() {
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    
    }
    
    @Override
    public void mousePressed(MouseEvent me) {}
}
