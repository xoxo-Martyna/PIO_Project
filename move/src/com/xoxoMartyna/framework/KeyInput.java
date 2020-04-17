package com.xoxoMartyna.framework;

import com.xoxoMartyna.objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Player player;

    public KeyInput(Player player) {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // nie jest plynnie, dla player.setVelX(5); jest lepiej
        //(tak jak dla LEFT, UP, DOWN)
        if(key == KeyEvent.VK_RIGHT) {
            player.setX(player.getX()+5);
        }
        if(key == KeyEvent.VK_LEFT) {
            player.setVelX(-5);
        }
        if(key == KeyEvent.VK_UP) {
            player.setVelY(-5);
        }
        if(key == KeyEvent.VK_DOWN) {
            player.setVelY(5);
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT) player.setVelX(0);
        if(key==KeyEvent.VK_LEFT) player.setVelX(0);
        if(key==KeyEvent.VK_UP) player.setVelY(0);
        if(key==KeyEvent.VK_DOWN) player.setVelY(0);
    }
}
