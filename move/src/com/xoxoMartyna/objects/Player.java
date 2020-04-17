package com.xoxoMartyna.objects;

import com.xoxoMartyna.framework.KeyInput;

import java.awt.*;

public class Player {
    protected float x, y;
    protected float velX = 0, velY=0;
    private float width = 32, height = 32;

    public Player(float x, float y) {
        this.x =x;
        this.y = y;
    }

    public void tick() {
        x+=velX;
        y+=velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int)x, (int)y, (int) width, (int) height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

}
