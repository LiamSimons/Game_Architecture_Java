package com.company;

import java.awt.*;
import java.util.Random;

public class FastEnemy extends GameObject{

    private Handler handler;
    private Random r;
    private int random = 1;

    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 4;
        velY = 7;
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52){
            /*r = new Random();
            random = r.nextInt(2);
            velY = -1*(velY/Math.abs(velY))*(5*random + 6);*/
            velY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH - 32) {
            /*r = new Random();
            random = r.nextInt(2)+1;
            velX = -1*(velX/Math.abs(velX))*(2*random + 6);*/
            velX *= -1;
        }

        handler.addObject(new Trail(x, y, Color.CYAN, 16, 16,  ID.Trail,0.04f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 16, 16);
    }
}
