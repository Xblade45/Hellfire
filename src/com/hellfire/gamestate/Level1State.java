/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hellfire.gamestate;

import com.hellfire.entity.Player;
import com.hellfire.level.Background;
import com.hellfire.level.TileMap;
import com.hellfire.main.Panel;
import java.awt.Graphics;

/**
 *
 * @author Xblade45
 */
public class Level1State extends GameState {
    
    private Background backLayer;
    private Background middleLayer;
    private TileMap tileMap;
    private Player player;
    
    private final double SCROLLSPEED_DEFAULT = 1.5;
    
    public Level1State(GameStateManager gsm){
        
        this.gsm = gsm;
        
        init();
    }
    
    @Override
    public final void init() {
        
        backLayer = new Background("background1", SCROLLSPEED_DEFAULT, -1, 0);
        middleLayer = new Background("background2", SCROLLSPEED_DEFAULT +1, -1, 0);
        tileMap = new TileMap("tileset2", "level1", SCROLLSPEED_DEFAULT +1.5);
        player = new Player(Panel.getP_WIDTH()/2, Panel.getP_HEIGHT()/2, "spaceship1");
    }
    
    @Override
    public void draw(Graphics g) {
        
        backLayer.draw(g);
        middleLayer.draw(g);
        tileMap.draw(g);
        player.draw(g);
    }

    @Override
    public void update() {
        
        backLayer.update();
        middleLayer.update();
        tileMap.update();
        player.update();
        
        if(tileMap.getCollision(player))
            gsm.gameOver();
    }
}
