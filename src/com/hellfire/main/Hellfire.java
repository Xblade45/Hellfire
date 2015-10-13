/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.main;

import javax.swing.JFrame;

/**
 *
 * @author Xblade45
 */
public class Hellfire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Panel panel = new Panel();
        
        JFrame frame = new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
}
