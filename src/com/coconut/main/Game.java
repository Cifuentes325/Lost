package com.coconut.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	

	private static final long serialVersionUID = 1963568187483608292L;

	
	
	public static final int WIDTH = 500, HEIGHT = 900;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	public static boolean bossTrigger = false;
	private LevelSpawner levelSpawner;
	public static boolean bossOut = false;
	public static boolean gameOver = false;
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
		//r = new Random();
		
		
		new Window(WIDTH, HEIGHT, "LOST" ,this);
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		//starts games right away for testing
		levelSpawner = new LevelSpawner(handler, this);
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
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
            
        }
        stop();
    }
	
    private void tick(){
    	if(gameState == STATE.Game){
    		handler.tick();
    		hud.tick();
    		levelSpawner.tick();
    	}
    	menu.tick();
    	
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
    	if(gameState == STATE.Game){
    		    	hud.render(g);
    		    	handler.render(g);
    	}
    	else if(gameState == STATE.StartMenu || gameState == STATE.Pause || gameState == STATE.Help){
    		menu.render(g);
    	}
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
