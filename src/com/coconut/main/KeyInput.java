package com.coconut.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.coconut.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
    private Handler handler;
    private int speed = 4;
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
                if(key == KeyEvent.VK_UP)
                {   
                    tempObject.setVelY(-speed);
                    keyDown[0] = true;

                }
                if(key == KeyEvent.VK_DOWN){
                    tempObject.setVelY(speed);
                    keyDown[1] = true;
                    
                }
                if(key == KeyEvent.VK_LEFT)
                { 
                    tempObject.setVelX(-speed);
                    keyDown[2] = true;
                    
                }
                if(key == KeyEvent.VK_RIGHT)
                { 
                    tempObject.setVelX(speed);
                    keyDown[3] = true;
                    
                }
                //SPACE currently unused
                if(key == KeyEvent.VK_SPACE)
                { 
                    keyDown[4] = true;
                    
                }
                
                if(key == KeyEvent.VK_Z)
                {
                	keyDown[5] = true;
                    tempObject.setDirection(1);
                    tempObject.setAttack(true);
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
        	if(Game.gameState == STATE.Game){
        		if(Game.gameState == STATE.Pause)
        			Game.gameState = STATE.Game;
        		else
        			Game.gameState = STATE.Pause;
        	}
        }
        
        
    }
	
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0;i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == mobId.Player)
            {//key events for player
                if(key == KeyEvent.VK_UP) keyDown[0] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) keyDown[1] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_LEFT) keyDown[2] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_RIGHT) keyDown[3] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_SPACE)
                { 
                    keyDown[4] = false;
                    //tempObject.setAttack(false);
                }
                if(key == KeyEvent.VK_Z) keyDown[5] = false;//tempObject.setVelY(0);
                
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
                
                if(!keyDown[5])
                	tempObject.setAttack(false);

                
            }
            
        }
        
    }
    
}
