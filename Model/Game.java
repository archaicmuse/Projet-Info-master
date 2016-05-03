package Model;
import View.Window;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Game {
	private Window window;
	public ArrayList<Character> entities;
	private RandomGenerator random;
	public static int size = 51;
	public static int nbrEnnemy = 82;
	Random ran = new Random();
	
	public Game(Window window){
		this.window = window;
		this.entities = new ArrayList<Character>();
		this.entities.add(new Player(5,5,1));
		for(int i = 0;i<nbrEnnemy;i++){
			this.entities.add(new Ennemy(ran.nextInt(50),ran.nextInt(50),1));
		}
		window.draw(this.getMap());
	}

	public void moveCharacter(int x, int y, int entityIndex){
		if (window.askCollision(x,y, this.entities.get(entityIndex).getX(),this.entities.get(entityIndex).getY())){
			this.entities.get(entityIndex).move(x, y);
			window.draw(this.getMap());
		}
		
		
	}
	
	public void playerUse(){
		if(this.entities.get(0).getStatus()==0){
			this.entities.get(0).setAttackStatus(1);
			System.out.println("Joueur attaque");
			window.draw(this.getMap());
		}
		else if(this.entities.get(0).getStatus()==1){
			this.entities.get(0).setAttackStatus(0);
			System.out.println("Joueur repos");
			window.draw(this.getMap());
		}
		
		
	}
	
	public void playerAttack(String direction){

	}
	
	
	public int[][] getMap(){
		int[][] map = new int[this.size][this.size];
		for(int i = 0; i<this.size; i++)
			for(int j = 0; j<this.size; j++)
				map[i][j] = 0;
		
		map[4][5] = -2;
		
		for(int i=1;i<nbrEnnemy+1;i++){
			map[this.entities.get(i).getX()][this.entities.get(i).getY()] = -3;
		}
		
		int x = this.entities.get(0).getX();
		int y = this.entities.get(0).getY();
		int attackStatus = this.entities.get(0).getStatus();
		if(attackStatus == 0){
			map[x][y] = 2;
		}else if (attackStatus == 1){
			map[x][y] = 3;
		}
		return map;
	}
	
}
