package Model;

import java.util.Random;

public class MobAI implements Runnable{
	private int posX;
	private int posY;
	private static int stepX;
	private static int stepY;
	private int WaitTime=600;
	private Game game;
	private int visionRange = 4;
	private int attackRange = 1;
	Random ran = new Random();
	public MobAI(Game game){
		this.game = game;
	}
	
	
	
	public int getX(){
		return this.posX;
	}
	
	public int getY(){
		return this.posY;
	}
	
	// Méthode convertissant un réel compris entre 0 et 4 à une direction
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
	
	//Méthode déterminant si le joueur est dans le champs de vision d'un ennemi
	private boolean inRange(int range,int posXenemy, int posYenemy, int posX, int posY){
		boolean res = false;
		int distX = posX-posXenemy;
		int distY = posY-posYenemy;
		if(Math.abs(distX)<=range && Math.abs(distX)>=1 && Math.abs(distY)<=range &&Math.abs(distY)>=1) {
			res = true;
		}
		return res;
	}
	//Méthode déterminant si le joueur est à portée d'attaque ennemie
	private boolean inAttackRange(int range,int posXenemy, int posYenemy, int posX, int posY){
		boolean res = false;
		int distX = posX-posXenemy;
		int distY = posY-posYenemy;
		if((Math.abs(distX)==range && Math.abs(distY)==0)||(Math.abs(distY)==range && Math.abs(distX)==0)) {
			res = true;
		}
		return res;
	}
	
	//Méthode rapprochant l'ennemi du joueur une fois à portée
	private void getCloseToPlayer(int posXenemy, int posYenemy, int posX, int posY){
		int distX = posX-posXenemy;
		int distY = posY-posYenemy;
		if (Math.abs(distX)<Math.abs(distY)){
			stepX=0;
			stepY=(int)Math.signum(distY);
		}
		else{
			stepX = (int)Math.signum(distX);
			stepY = 0;
		}
	}
	
	@Override
	public void run(){
		try{
			while(true){
				System.out.println(1);
				for(int i=1;i<game.nbrEnemy;i++){
					if (inRange(visionRange, game.enemies.get(i).getX(),game.enemies.get(i).getY(),game.player.getX(),game.player.getY())){ // L'ennemi se rapproche du joueur si il est à portée
						getCloseToPlayer(game.enemies.get(i).getX(),game.enemies.get(i).getY(),game.player.getX(),game.player.getY());
						game.moveCharacter(stepX, stepY, i);
						if (game.player.getHP()<1){
							System.exit(0);
						}
					}
					else if (inAttackRange(attackRange, game.enemies.get(i).getX(),game.enemies.get(i).getY(),game.player.getX(),game.player.getY())){ // L'ennemi est à proximité du joueur
						if(game.player.getInvincibilityStatus()==0){
							int health = game.player.getHP(); 
							game.player.setHP(health-1);
						}else{
							game.player.setInvincibilityStatus(game.player.getInvincibilityStatus()-1);
						}
						
						
					}
					else { // Aucune condition est remplie, l'ennemi se déplace aléatoirement
					int n = RandomGenerator.randInt(0,4);
					Direction(n);
					game.moveCharacter(stepX, stepY, i);
					}
				}
				Thread.sleep(WaitTime);
			}
		}catch(Exception e){};
		
	}


}
	
	
