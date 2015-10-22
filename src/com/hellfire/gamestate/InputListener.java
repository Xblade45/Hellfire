/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Xblade45
 */
public class InputListener implements KeyListener {

    public static boolean isUpPressed = false;
    public static boolean isDownPressed = false;
    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;
    public static boolean isFirePressed = false;
    public static boolean isChangePressed = false;
    
    public InputListener(){}
    
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
