/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.main;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.GameStateManager;
import com.hellfire.gamestate.InputListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 *
 * @author Xblade45
 */
public class Panel extends JPanel implements GameEngine, Runnable{

    GameStateManager gsm;
    
    private boolean isRunning;
    
    private static final int P_WIDTH = 512;
    private static final int P_HEIGHT = P_WIDTH / 16 * 9;
    private static final int SCALE = 2;
    
    private final int DELAY = 15;
    
    private Thread game;
    
    public Panel(){
    
        init();
    }
    
    public static final int getP_WIDTH(){
        return P_WIDTH*SCALE;
    }
    
    public static final int getP_HEIGHT(){
        return P_HEIGHT*SCALE;
    }
    
    @Override
    public final void init() {
        
        gsm = new GameStateManager();
        isRunning = true;
        
        this.addKeyListener(new InputListener());
        
        setPreferredSize(new Dimension(getP_WIDTH(), getP_HEIGHT()));
        setDoubleBuffered(true);
        setFocusable(true);
        setVisible(true);
    }
    
    @Override
    public final void run() {

        long beforeTime, timeDiff, sleep;
        
        while(isRunning){
            
            beforeTime = System.currentTimeMillis();   
            
            update();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            
            if(sleep < 0)
                sleep = DELAY;
            
            try{
                Thread.sleep(sleep);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addNotify() {
        
        super.addNotify();

        game = new Thread(this);
        game.start();
    }

    @Override
    public void update() {
        
        gsm.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void draw(Graphics g) {
        
        // White rect between each frame
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getP_WIDTH(), getP_HEIGHT());
        
        gsm.draw(g);
    }
}
