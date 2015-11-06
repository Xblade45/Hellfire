/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author Xblade45
 */
public class Animation {
    
    private BufferedImage[] frames;
    private int currentFrame;
    
    private long startTime;
    private long delay;
    
    private boolean playedOnce;
    
    //constructor
    public Animation(BufferedImage[] frames){
        setFrames(frames);
        playedOnce = false;
    }
    
    //methods
    private void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        playedOnce = false;
    }
    
    public void start(long d){
        startTime = System.currentTimeMillis();
        setDelay(d);
    }
    
    public void stop(){
        setDelay(-1);
    }
    
    private void setDelay(long d) {
        this.delay = d;
    }
    
    public void update(){
        
        if(delay == -1)
            return;
            
        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if(elapsed > delay) {
            
            currentFrame++;
            startTime = System.nanoTime();
        }

        if(currentFrame == frames.length){
            
            currentFrame = 0;
            playedOnce = true;
        }
    }
    
    public BufferedImage getImage(){
        return frames[currentFrame];
    }
    
    public BufferedImage getFrames(int index){
        return frames[index];
    }
    
    public boolean hasPlayedOnce(){
        return playedOnce;
    }
}
