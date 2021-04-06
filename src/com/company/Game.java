package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private int ticks = 0;

    private Thread thread;
    private boolean running = false;

    private Window window;
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;


    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        window = new Window(WIDTH, HEIGHT, "Run till you die!", this);
        hud = new HUD();
        spawner = new Spawn(handler, hud);

        r = new Random();
        handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH-32), r.nextInt(HEIGHT-52), ID.BasicEnemy, handler));

        /*for(int i = 0; i < 5; i++) {
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
        }*/
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void end(){
        handler.clear();
        window.clear();
        int score = hud.end();
    }

    public void run(){
        this.requestFocus();                                    // don't need to click on the screen
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
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                hud.setFrames(frames);
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
        spawner.tick();
        if(hud.HEALTH <= 0) {
            end();
            ticks++;
            if(ticks >= 2){
                stop();
            }
        }

        /*if(HUD.HEALTH <= 0){
            stop();
        }*/
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor((Color.BLACK));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static int clamp(int var, int min, int max){
        if(var >= max){
            return var = max;
        }
        else if (var <= min){
            return var = min;
        }
        else return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}
