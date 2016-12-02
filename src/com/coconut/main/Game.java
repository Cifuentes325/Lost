package com.coconut.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	

	private static final long serialVersionUID = 1963568187483608292L;

	
	private Random r;
	
	public static final int WIDTH = 600, HEIGHT = WIDTH/12 *9;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	private Handler handler;
	private HUD hud;
    public enum STATE{
        StartMenu,
        Pause,
        Shop,
        Help,
        Game,
        End
    }
    public static STATE gameState = STATE.StartMenu;
    
	public Game(){
		
		handler = new Handler();
		r = new Random();
		
		
		new Window(WIDTH, HEIGHT, "LOST" ,this);
		this.addKeyListener(new KeyInput(handler,this));
		//starts games right away for testing
		gameState = STATE.Game;
        handler.addObject(new Player(WIDTH/2 -32,HEIGHT/2 - 32, mobId.Player, 1, 100 ,handler));
        
        handler.addObject(new Slime(r.nextInt(WIDTH-50), r.nextInt(HEIGHT - 50), mobId.BasicEnemy, r.nextInt(4), handler));
        handler.addObject(new Slime(r.nextInt(WIDTH-50), r.nextInt(HEIGHT - 50), mobId.BasicEnemy, r.nextInt(4), handler));
        handler.addObject(new Slime(r.nextInt(WIDTH-50), r.nextInt(HEIGHT - 50), mobId.BasicEnemy, r.nextInt(4), handler));
        handler.addObject(new Slime(r.nextInt(WIDTH-50), r.nextInt(HEIGHT - 50), mobId.BasicEnemy, r.nextInt(4), handler));
        hud = new HUD(handler,this);
        //handler.clearEnemies();
		
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
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	//Game loop
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
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
    	hud.render(g);
    	handler.render(g);
    	
    	g.dispose();
    	bs.show();
    }
    
    
    //prevets player from going out fo bounds. needs to be adjusted for
    //future rooms of various shaps
    public static float clamp(float var, float min, float max)
    {
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }
    
    public static boolean outBounds(float var, float min, float max)
    {
        if(var >= max)
            return true;
        else if(var <= min)
            return true;
        else
            return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}

}
