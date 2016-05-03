package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import Model.Game;

public class Keyboard implements KeyListener{
	private Game game;
	
	public Keyboard(Game game){
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		switch (key){
			case KeyEvent.VK_D: 
				System.out.println("Droite");
				game.moveCharacter(1,0,0);

				break;
			case KeyEvent.VK_Q:
				System.out.println("Gauche");
				game.moveCharacter(-1,0,0);
				break;
			case KeyEvent.VK_S:
				System.out.println("Bas");
				game.moveCharacter(0,1,0);
				break;
			case KeyEvent.VK_Z:
				System.out.println("Haut");
				game.moveCharacter(0,-1,0);
				break;	
			case KeyEvent.VK_SPACE:
				game.playerUse();
				break;
			case KeyEvent.VK_LEFT:
				game.playerAttack("gauche");
				break;
			case KeyEvent.VK_RIGHT:
				game.playerAttack("droite");
				break;
			case KeyEvent.VK_UP:
				game.playerAttack("haut");
				break;
			case KeyEvent.VK_DOWN:
				game.playerAttack("bas");
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
