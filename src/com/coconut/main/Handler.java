package com.coconut.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    @SuppressWarnings("Convert2Diamond")
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void tick(){
        for(int i = 0; i < object.size(); ++i)
        {
            GameObject tempObject = object.get(i);
            if(tempObject != null)
            	tempObject.tick();
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i < object.size(); ++i)
        {
            GameObject tempObject = object.get(i);
            if(tempObject != null)
            	tempObject.render(g);
        }
    }
    
    public void clearEnemies()
    {
        
        for(int i = 0; i < object.size(); ++i)
        {
            GameObject tempObject = object.get(i);
            
            
            if(tempObject.getId() == mobId.Player){
                object.clear();
                if(Game.gameState != Game.STATE.End){
                	addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), mobId.Player, 1,100 ,this));
                }
                
            }
               
        }
    }
    
    
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
}
