package Model;

import java.util.ArrayList;

public class MobAI implements Runnable{
	private int posX;
	private int posY;
	private static int stepX;
	private static int stepY;
	private int WaitTime=1000;
	private Game game;
	
	
	
	public int getX(){
		return this.posX;
	}
	
	public int getY(){
		return this.posY;
	}
	
	public void Direction(int n){
		if (n == 0){
			stepX = 0;
			stepY = 0;
		}
		if (n == 1){
			stepX = -1;
			stepY = 0;
		}	
		if (n == 2){
			stepX = 1;
			stepY = 0;
		}	
		if (n == 3){
			stepX = 0;
			stepY = 1;
		}	
		if (n == 4){
			stepX = 0;
			stepY = -1;
		}	
	}
	
	public boolean inRange(int range, int posX, int posY, int posXenemy, int posYenemy){
		boolean res = false;
		int distX = posX-posXenemy;
		int distY = posY-posYenemy;
		if(Math.abs(distX)<range||Math.abs(distY)<range){
			res = true;
		}
		return res;
	}
	

		
		
	@Override
	public void run(){
		try{
			while(true){
				for(int i=1;i<Game.nbrEnnemy+1;i++){
					int n = RandomGenerator.randInt(0,4);
					Direction(n);
					game.moveCharacter(stepX, stepY, i);
				}
				Thread.sleep(1000);
			}
		}catch(Exception e){};
		
	}
	public static void main(String[] args) {
	Thread t = new Thread(new MobAI());
	t.start();
	}
}
	
	
