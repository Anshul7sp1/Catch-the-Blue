package com.anshul.main;

import java.awt.Graphics;
import java.awt.Color;

public class HUD {

    public static int HEALTH = 100;
    private int greenValue = 255;
    private int score = 0;
    private int level = 1;

    public void tick(){
        score += 3;
        HEALTH = Game.clamp(HEALTH, 0, 100);

        greenValue = HEALTH*2;
        greenValue = Game.clamp(greenValue, 0, 255);
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(10, 10, 200, 15);
        g.setColor(new Color(80, greenValue, 0));
        g.fillRect(10, 10, HEALTH*2, 15);
        g.setColor(Color.white);
        g.drawRect(10, 10, 200, 15);
        
        g.drawString("Score : " + score/100, 10, 44);
        g.drawString("Level : " + level, 10, 60);
    }
    public static void lowerHealth(int val){
        HEALTH -= val;
    }
    public static void heal(int val){
        HEALTH += val;
    }

    public void setScore(int val){
        this.score = val;
    }
    public int getScore(){
        return this.score;
    }
    public void setLevel(int val){
        this.level = val;
    }
    public int getLevel(){
        return this.level;
    }
    public void levelUp(){
        this.setLevel(this.level+1);
    }
}