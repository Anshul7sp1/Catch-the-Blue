package com.anshul.main;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Random;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    public static Random r = new Random();

    public GameObject(int x, int y, ID id){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY) {
        this.velY = velY;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public ID getID(){
        return id;
    }
    public int getVelX() {
        return velX;
    }
    public int getVelY(){
        return velY;
    }
}