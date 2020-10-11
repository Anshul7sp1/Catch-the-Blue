package com.anshul.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject target;

    private static final int SIZE = 16;
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for(GameObject obj : handler.object) if(obj.getID() == ID.PLAYER) target = obj;

        velX = r.nextInt(4) + 2;
        velY = velX;
        if (r.nextInt() > 0)
            velX *= -1;
        if (r.nextInt() > 0)
            velY *= -1;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffX =  this.x - target.getX() - 8;
        float diffY =  this.y - target.getY() - 8;
        float distance = (float)Math.sqrt((diffX*diffX) + (diffY*diffY));
        velX = (int)((-4.0/distance) * diffX);
        velY = (int)((-4.0/distance) * diffY);

        // if (y <= 0 || y >= Game.HEIGHT - 48)
        //     velY *= -1;
        // if (x <= 0 || x >= Game.WIDTH - 32)
        //     velX *= -1;

        handler.addObject(new Trail(x, y, ID.TRAIL, Color.magenta, SIZE, SIZE, 0.03f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect(x, y, SIZE, SIZE);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    
}