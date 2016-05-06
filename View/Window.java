package View;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Model.Game;

public class Window {
	private Map map = new Map();
	
	public Window(){	    
	    JFrame window = new JFrame("SquareLand");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    window.setResizable(false);
	    window.setBounds(800, 0, 1016, 1016);
	    window.getContentPane().setBackground(Color.gray);
	    window.getContentPane().add(this.map);
	    window.setVisible(true);
	}
	
	public void draw(int[][] mapMatrix, int range, int playerHP){
		map.setMapMatrix(mapMatrix, range, playerHP);
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.map.addKeyListener(keyboard);
	}
	
	public boolean askCollision(int stepX, int stepY, int currentX, int currentY){
		if (map.checkCollision(stepX, stepY, currentX, currentY)){
			return true;
		}else{
			return false;
		}
	}
}
