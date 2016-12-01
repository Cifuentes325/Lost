package com.coconut.main;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	
	private static final long serialVersionUID = 2171675895437643968L;
	
	public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        
    }
	
}
