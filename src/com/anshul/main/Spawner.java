package com.anshul.main;

import java.util.Random;

public class Spawner {
    private Handler handler;
    private HUD hud;
    private float spawnRate = 3;
    private float scoreCounter = 0;
    private Random r;

    public Spawner(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        r = new Random();
    }

    public void tick() {
        scoreCounter += spawnRate;
        if(scoreCounter >= 1000){
            scoreCounter = 0;
            hud.levelUp();
            spawnNewEnemy();
            if(hud.getLevel()%3 == 0 && hud.getLevel() > 7) spawnRate *= 0.6;
        }
    }

    private void spawnNewEnemy(){
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BASIC_ENEMY, handler));
        if(r.nextInt(3) == 0){
            handler.addObject(new Bounty(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BOUNTY, handler));
        }
        if(r.nextInt(3) == 0){
            handler.addObject(new HPRestore(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HP_RESTORE, handler));
        }
        if(r.nextInt(3) == 0 && hud.getLevel() >= 5){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SMART_ENEMY, handler));
        }
    }
}
