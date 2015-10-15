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
    
    private static final int P_WIDTH = 512;
    private static final int P_HEIGHT = P_WIDTH / 16 * 9;
    private static final int SCALE = 2;
    
    public static boolean isUpPressed = false;
    public static boolean isDownPressed = false;
    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;
    public static boolean isFirePressed = false;
    public static boolean isChangePressed = false;
    
    private final int DELAY = 16;
    
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
        
        this.addMouseListener(this);
        this.addKeyListener(this);
        
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
        
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                               RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
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
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
        
            case KeyEvent.VK_W:
                isUpPressed = true;
            break;
            case KeyEvent.VK_S:
                isDownPressed = true;
            break;
            case KeyEvent.VK_A:
                isLeftPressed = true;
            break;
            case KeyEvent.VK_D:
                isRightPressed = true;
            break;
            case KeyEvent.VK_SPACE:
                isFirePressed = true;
            break;
            case KeyEvent.VK_E:
                isChangePressed = true;
            break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
        
            case KeyEvent.VK_W:
                isUpPressed = false;
            break;
            case KeyEvent.VK_S:
                isDownPressed = false;
            break;
            case KeyEvent.VK_A:
                isLeftPressed = false;
            break;
            case KeyEvent.VK_D:
                isRightPressed = false;
            break;
            case KeyEvent.VK_SPACE:
                isFirePressed = false;
            break;
            case KeyEvent.VK_E:
                isChangePressed = false;
            break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
