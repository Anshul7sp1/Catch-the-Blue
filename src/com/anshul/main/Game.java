package com.anshul.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1550691097823471818L;
    public static final int WIDTH = 720, HEIGHT = WIDTH*3/4;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    private Random r;

    public Game(){
        handler = new Handler();
        hud = new HUD();
        spawner = new Spawner(handler, hud);
        r = new Random();

        this.addKeyListener(new KeyInput(handler));
        setFocusable(true);
        new Window(WIDTH, HEIGHT, "Game 1", this);

        handler.addObject(new Player(WIDTH/2, 0, ID.PLAYER, handler, hud));
        handler.addObject(new Bounty(r.nextInt(WIDTH), r.nextInt(HEIGHT), handler));
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), handler));
        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), handler));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running) render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int val, int min, int max){
        if(val <= min) val = min;
        else if(val >= max) val = max;
        return val;
    }
    public static int warp(int val, int min, int max){
        if(val < min) val = max;
        else if (val > max) val = min;
        return val;
    }

    public static void main(String[] args){
        new Game();
    }

}