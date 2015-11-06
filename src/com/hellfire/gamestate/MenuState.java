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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Xblade45
 */
public class MenuState extends GameState{
    
    
    Font menuFont;
    Background background;
    JLabel startMenu;
    JLabel quitMenu;
    
    public MenuState(GameStateManager gsm){
        
        this.gsm = gsm;
        
        init();
    }
    
    @Override
    public final void init() {
        
        menuFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
    }
    
    @Override
    public void draw(Graphics g) {
        
        // Black Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Panel.getP_WIDTH(), Panel.getP_HEIGHT());
        
        // String color
        g.setColor(Color.WHITE);
        
        // Center Tests
        //g.drawLine(Panel.getP_WIDTH()/2, 0, Panel.getP_WIDTH()/2, Panel.getP_HEIGHT());
        //g.drawLine(0, Panel.getP_HEIGHT()/2, Panel.getP_WIDTH(), Panel.getP_HEIGHT()/2);
        
        //String font
        g.setFont(menuFont);
        
        // Determine where to write to be center
        FontMetrics fm = g.getFontMetrics();      
        int centerX = Panel.getP_WIDTH()/2 - fm.stringWidth("start")/2;
        
        // Draw String/Menu
        g.drawString("Start", centerX, Panel.getP_HEIGHT()*12/25);
        g.drawString("Quit", centerX, Panel.getP_HEIGHT()*13/25);
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
    public void mousePressed(MouseEvent me) {
        
    }
}
