package com.coconut.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected float x, y;
    protected mobId id;
    protected float velX, velY;
    protected int direction; //up = 1, down = 2, left = 3, right = 4
    protected boolean attack;
    
    public GameObject(float x, float y, mobId id, int direction)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.direction = direction;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setId(mobId id){
        this.id = id;
    }
    public mobId getId(){
        return id;
    }
    public void setVelX(float velX)
    {
        this.velX = velX;
    }
    public void setVelY(float velY)
    {
        this.velY = velY;
    }        
    public float getVelX()
    {
        return velX;
    }
    public float getVelY()
    {
        return velY;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    public int getDirection()
    {
        return direction;
    }
    public void setAttack(boolean attack)
    {
        this.attack = attack;
    }
    public boolean getattack()
    {
        return attack;
    }
    
}