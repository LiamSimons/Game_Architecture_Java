package com.company;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        //velX = r.nextInt(5)+1;
        //velY = r.nextInt(5);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH-48);
        y = Game.clamp(y, 0, Game.HEIGHT-70);

        handler.addObject(new Trail(x, y, Color.white, 32, 32,  ID.Trail,0.1f, handler));

        collision();
    }

    private void collision(){
        for(int i = 0; i< handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy){

                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                }

            }

        }


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }


}
