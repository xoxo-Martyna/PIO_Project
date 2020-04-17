package com.xoxoMartyna.window;

import com.xoxoMartyna.framework.KeyInput;
import com.xoxoMartyna.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private boolean running = false;

    public static int WIDTH, HEIGHT;

    Player player;

    private void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        player = new Player(100, 200);
        this.addKeyListener(new KeyInput(player));
    }

    public synchronized void start() {
        if(running) {
            return;
        }
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime=now;
            while(delta>=1) {
                tick();
                delta--;
            }
            render();

            if(System.currentTimeMillis() - timer > 1000) {
                timer+=1000;
            }
        }

    }

    private void tick() {
        player.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        player.render(g);
        ///////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Window(800,600, "xoxoMartyna", new Game());
    }
}

