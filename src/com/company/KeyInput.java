package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler){
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_UP) {tempObject.setVelY(tempObject.getVelY()-5); /*keyDown[0]=true;*/}                                   //tempObject.getVelY()
                if (key == KeyEvent.VK_DOWN) {tempObject.setVelY(tempObject.getVelY()+5);/*keyDown[1]=true;*/}                                   //tempObject.getVelY()+
                if (key == KeyEvent.VK_LEFT) {tempObject.setVelX(tempObject.getVelX()-5);/*keyDown[2]=true;*/}                                  //tempObject.getVelX()
                if (key == KeyEvent.VK_RIGHT) {tempObject.setVelX(tempObject.getVelX()+5);/*keyDown[3]=true;*/}                                  //tempObject.getVelX()+
                tempObject.setVelY(Game.clamp(tempObject.getVelY(),-5,5));
                tempObject.setVelX(Game.clamp(tempObject.getVelX(),-5,5));
            }
        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
        //System.out.println(key);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player){
                //key events for player 1

                if (key == KeyEvent.VK_UP) /*keyDown[0]=false;*/tempObject.setVelY(tempObject.getVelY()+5);
                if (key == KeyEvent.VK_DOWN) /*keyDown[1]=false;*/tempObject.setVelY(tempObject.getVelY()-5);
                if (key == KeyEvent.VK_LEFT) /*keyDown[2]=false;*/tempObject.setVelX(tempObject.getVelX()+5);
                if (key == KeyEvent.VK_RIGHT) /*keyDown[3]=false;*/tempObject.setVelX(tempObject.getVelX()-5);
                tempObject.setVelY(Game.clamp(tempObject.getVelY(),-5,5));
                tempObject.setVelX(Game.clamp(tempObject.getVelX(),-5,5));


                /*
                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);*/
            }

        }
    }

}
