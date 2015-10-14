/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import java.awt.Graphics;


/**
 *
 * @author Xblade45
 */
public interface GameEngine{
    
    
    //Initialize everything
    public void init();
    
    //Where the game run
    public void run();
    
    //Where attributes are updated
    public void update();
    
    //Where everything is draw to screen
    public void draw(Graphics g);
    
    
}
