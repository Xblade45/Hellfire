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
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 *
 * @author Xblade45
 */
public class Panel extends JPanel implements GameEngine, MouseListener, KeyListener, Runnable{

    GameStateManager gsm;
    
    private boolean isRunning;
    
    public static final int WIDTH = 512;
    public static int HEIGHT = WIDTH / 16 * 9;
    public static int SCALE = 2;
    
    private final int DELAY = 16;
    
    private Thread game;
    
    public Panel(){
    
        init();
    }
    
    @Override
    public final void init() {
        
        gsm = new GameStateManager();
        isRunning = true;
        
        this.addMouseListener(this);
        this.addKeyListener(this);
        
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
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
                sleep = 16;
            
            System.out.println(1000/sleep);//FPS
            
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
    }
    
    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh
        = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        gsm.draw(g2d);
        
        Toolkit.getDefaultToolkit().sync();
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
    public void keyPressed(KeyEvent e) {
    
        gsm.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    
        gsm.keyReleased(e);
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
