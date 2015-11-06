/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import com.hellfire.level.Background;
import com.hellfire.main.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Xblade45
 */
public class MenuState extends GameState {
    
    
    private Font menuFont;
    private Background background;
    
    private int currentMenuState;
    
    private final int STARTMENUSTATE = 0;
    private final int QUITMENUSTATE = 1;
    
    
    public MenuState(GameStateManager gsm){
        
        this.gsm = gsm;
        
        init();
    }
    
    @Override
    public final void init() {
        
        this.currentMenuState = STARTMENUSTATE;
        
        menuFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        background = new Background("background1");
    }
    
    @Override
    public void draw(Graphics g) {
        
        // Black Background
        background.draw(g);
        
        //String font
        g.setFont(menuFont);
        
        // Determine where to write to be center
        FontMetrics fm = g.getFontMetrics();      
        int centerX = Panel.getP_WIDTH()/2 - fm.stringWidth("start")/2;
        
        // Draw String/Menu
        g.setColor(currentMenuState == STARTMENUSTATE? Color.red:Color.white);
        g.drawString("Start", centerX, Panel.getP_HEIGHT()*12/25);
        g.setColor(currentMenuState == QUITMENUSTATE? Color.red:Color.white);
        g.drawString("Quit", centerX, Panel.getP_HEIGHT()*13/25);
    }

    @Override
    public void update() {
        
        navigateMenu();
        checkSelection();
    }
    
    private void navigateMenu(){
        if(InputListener.isDownPressed && currentMenuState == STARTMENUSTATE)
            this.currentMenuState = QUITMENUSTATE;
        else if(InputListener.isUpPressed && currentMenuState == QUITMENUSTATE)
            this.currentMenuState = STARTMENUSTATE;
    }
    
    private void checkSelection(){
        if(currentMenuState == STARTMENUSTATE && InputListener.isEnterPressed)
            gsm.start();
        else if(InputListener.isEnterPressed)
            gsm.quit();
    }
}
