package com.anshul.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bounty extends GameObject {

    private Handler handler;

    private static final int SIZE = 16;
    public Bounty(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = r.nextInt(5) + 3;
        velY = r.nextInt(5) + 3;
        if (r.nextInt() > 0)
            velX *= -1;
        if (r.nextInt() > 0)
            velY *= -1;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT - 48)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32)
            velX *= -1;

        handler.addObject(new Trail(x, y, ID.TRAIL, Color.CYAN, SIZE, SIZE, 0.03f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, SIZE, SIZE);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    
}