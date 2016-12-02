package com.coconut.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	Handler handler;
	private boolean attack = false;
	
	public Player(float x, float y, mobId id, int direction, Handler handler) {
		super(x, y, id, direction);
		this.handler = handler;
	}

	
	public void tick() {
		x += velX;
        y += velY;
        x = Game.clamp((int)x, 0, Game.WIDTH-36);
        y = Game.clamp((int)y, 0, Game.HEIGHT-36); 
		
	}

	
	public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
		
	}

    public void setAttack(boolean attack)
    {
        this.attack = attack;
    }
    public boolean getattack()
    {
        return attack;
    }
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32,32);
	}

	
}
