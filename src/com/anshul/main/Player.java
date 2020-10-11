package com.anshul.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

    private static final int SIZE = 32;
    private Handler handler;
    private HUD hud;

    public Player(int x, int y, ID id, Handler handler, HUD hud){
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        y = Game.warp(y, 0, Game.HEIGHT - 72);
        x = Game.warp(x, 0, Game.WIDTH - 48);
        checkCollision();
        handler.addObject(new Trail(x, y, ID.TRAIL, Color.white, SIZE, SIZE, 0.1f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    private void checkCollision(){
        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.BASIC_ENEMY){
                if(this.getBounds().intersects(tempObject.getBounds())){
                    HUD.lowerHealth(1);
                }
            }
            else if(tempObject.getID() == ID.BOUNTY){
                if(this.getBounds().intersects(tempObject.getBounds())){
                    hud.setScore(hud.getScore()+5000);
                    handler.removeObject(tempObject);
                }    
            }
            else if(tempObject.getID() == ID.HP_RESTORE){
                if(this.getBounds().intersects(tempObject.getBounds())){
                    HUD.heal(50);
                    handler.removeObject(tempObject);
                } 
            }
            else if(tempObject.getID() == ID.SMART_ENEMY){
                if(this.getBounds().intersects(tempObject.getBounds())){
                    HUD.lowerHealth(20);
                    handler.removeObject(tempObject);
                } 
            }
        }
    }
    
}