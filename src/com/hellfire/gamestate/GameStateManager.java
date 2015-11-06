/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import com.hellfire.main.Panel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author Xblade45
 */
public class GameStateManager implements GameEngine, MouseListener, KeyListener{
    
    
    protected GameStateManager gsm;
    
    private final ArrayList<GameState> gameStates;
    private int currentState;
    
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    
    
    public GameStateManager(){
        
        gameStates = new ArrayList<>();
        currentState = LEVEL1STATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));
    }
    
    private void setState(int currentState){
        
        this.currentState = currentState;
        gameStates.get(currentState).init();
    }
    
    public void gameOver(){
        setState(MENUSTATE);
    }
    
    public void start(){
        setState(LEVEL1STATE);
    }
    
    public void quit(){
        Panel.setRunning(false);
    }

    @Override
    public void init() {
        
        gameStates.get(currentState).init();
    }
    @Override
    public void run() {
        
        gameStates.get(currentState).run();
    }
    @Override
    public void update() {
        
        gameStates.get(currentState).update();
    }
    @Override
    public void draw(Graphics g) {
        
        gameStates.get(currentState).draw(g);
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {}
    @Override
    public void mousePressed(MouseEvent me) {
        
        gameStates.get(currentState).mousePressed(me);
    }
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}

    
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
    
        gameStates.get(currentState).keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    
        gameStates.get(currentState).keyReleased(e);
    }

}
