package com.coconut.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.coconut.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
    private Handler handler;
    private int speed = 2;
    private boolean[] keyDown = new boolean[9];
    
    Game game;
    
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;//movement
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
        
        keyDown[4] = false;
        
        keyDown[5] = false;//direction
        keyDown[6] = false;
        keyDown[7] = false;
        keyDown[8] = false;

        
    }  
    public void keyPressed(KeyEvent e){
        int key = e. getKeyCode();
        for(int i = 0;i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == mobId.Player)
            {//key events for player
                if(key == KeyEvent.VK_W)
                {   
                    tempObject.setVelY(-speed);
                    keyDown[0] = true;

                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(speed);
                    keyDown[1] = true;
                    
                }
                if(key == KeyEvent.VK_A)
                { 
                    tempObject.setVelX(-speed);
                    keyDown[2] = true;
                    
                }
                if(key == KeyEvent.VK_D)
                { 
                    tempObject.setVelX(speed);
                    keyDown[3] = true;
                    
                }
                //SPACE currently unused
                if(key == KeyEvent.VK_SPACE)
                { 
                    keyDown[4] = true;
                    
                }
                
                if(key == KeyEvent.VK_UP)
                {
                	keyDown[5] = true;
                    tempObject.setDirection(1);
                    tempObject.setAttack(true);
                }
                if(key == KeyEvent.VK_DOWN)
                {
                	keyDown[6] = true;
                    tempObject.setDirection(2);
                    tempObject.setAttack(true);
                }
                if(key == KeyEvent.VK_LEFT)
                {
                	keyDown[7] = true;
                    tempObject.setDirection(3);
                    tempObject.setAttack(true);
                }
                if(key == KeyEvent.VK_RIGHT)
                {
                	keyDown[8] = true;
                    tempObject.setDirection(4);
                    tempObject.setAttack(true);
                }
                
            }
        }
        if(key == KeyEvent.VK_P)
        {
        	if(Game.gameState == STATE.Game)
        		if(Game.paused)
        			Game.paused = false;
        		else
        			Game.paused = true;
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(0);
        
        
    }
	
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0;i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == mobId.Player)
            {//key events for player
                if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) keyDown[2] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_SPACE)
                { 
                    keyDown[4] = false;
                    //tempObject.setAttack(false);
                }
                if(key == KeyEvent.VK_UP) keyDown[5] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) keyDown[6] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_LEFT) keyDown[7] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_RIGHT) keyDown[8] = false;//tempObject.setVelX(0);
                
                // movement based on released keys
                if(!keyDown[0] && !keyDown[1])
                    tempObject.setVelY(0);
                if(!keyDown[2] && !keyDown[3])
                    tempObject.setVelX(0);
                
                if(!keyDown[0] && keyDown[1])
                    tempObject.setVelY(speed);
                if(keyDown[0] && !keyDown[1])
                    tempObject.setVelY(-speed);
                
                if(!keyDown[2] && keyDown[3])
                    tempObject.setVelX(speed);
                if(keyDown[2] && !keyDown[3])
                    tempObject.setVelX(-speed);
                
                if(!keyDown[5] && keyDown[6])
                {
                	//tempObject.setAttack(true);
                    tempObject.setDirection(2);
                }
                if(keyDown[5] && !keyDown[6])
                {
                	//tempObject.setAttack(true);
                    tempObject.setDirection(1);
                }
                if(!keyDown[7] && keyDown[8])
                {
                	//tempObject.setAttack(true);
                	tempObject.setDirection(4);
                }
                if(keyDown[7] && !keyDown[8])
                {
                	//tempObject.setAttack(true);
                    tempObject.setDirection(3);
                }
                if(!keyDown[5] && !keyDown[6] && !keyDown[7] && !keyDown[8])
                	tempObject.setAttack(false);

                
            }
            
        }
        
    }
    
}
