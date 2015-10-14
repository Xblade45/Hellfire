/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.main;

import com.hellfire.gamestate.GameEngine;
import com.hellfire.gamestate.GameStateManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 *
 * @author Xblade45
 */
public class Panel extends JPanel implements GameEngine, MouseListener, KeyListener{

    GameStateManager gsm;
    
    
    private boolean isRunning;
    
    public static final int WIDTH = 512;
    public static int HEIGHT = WIDTH / 16 * 9;
    public static int SCALE = 2;
    
    private long start;
    private long wait;
    private long elapsed;
    
    public static final int FPS = 30;
    
    private Thread t;
    
    public Panel(){
    
        run();
    }

    @Override
    public final void run() {
        
        init();
        
        t = new Thread(){
            
            @Override
            public void run(){
                int counter = 0;
                while(isRunning){
                    
                    start = System.currentTimeMillis();
                    
                    update();
                    repaint();
                    
                    elapsed = System.currentTimeMillis() - start;
                    
                    if(elapsed < 0)
                        elapsed = 0;
                    
                    wait = 1000 / FPS + elapsed;
                    counter++;
                    
                    if(counter == FPS){
                        System.out.println("FPS:" + 1000/wait);
                        counter = 0;
                    }

                    try{
                        Thread.sleep(wait);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
    
    @Override
    public void init() {
        
        gsm = new GameStateManager();
        isRunning = true;
        
        this.addMouseListener(this);
        this.addKeyListener(this);
        
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setFocusable(true);
        setVisible(true);
        
    }

    @Override
    public void update() {
        
        gsm.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        draw(g);
    }
    
    @Override
    public void draw(Graphics g) {
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        gsm.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}
    
    @Override
    public void mousePressed(MouseEvent mouseEvent) {//envoie le mouseEvent a GSM
        
        gsm.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    
        gsm.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
        gsm.keyReleased(e);
    }
}
