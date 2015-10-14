/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.main;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Xblade45
 */
public class Hellfire extends JFrame{

    
    
    public Hellfire(){
        
        initUI();
    }
    
    private void initUI(){
        add(new Panel());
        
        setResizable(false);
        pack();
        
        setTitle("Hellfire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable(){
        
            @Override
            public void run(){
                Hellfire game = new Hellfire();
                game.setVisible(true);
            }
        });
    }
}
