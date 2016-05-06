package Model;
import View.Window;

import java.util.ArrayList;
import java.util.Random;

// Boucle de jeu principale

public class Game {
	//Création des listes principales 
	Window window;
	Random ran = new Random();
	public ArrayList<Enemy> enemies;
	private ArrayList<Block> blocks;
	public ArrayList<Trap> traps;
	public ArrayList<Object> objects;
	public static Player player;
	private ArrayList<Teleporter> teleporter;
	//Création des paramètres principaux du jeu
	private int size = 51;
	private int nbrEnemyDesired = 20;
	private int nbrTrapsDesired = 80;
	private int nbrBlocks = 0;
	public static int nbrEnemy = 0;
	public int nbrTraps = 0;
	private int nbrObjects = 0;
	
	private int[][] matrixFilled = new int[this.size][this.size];
	
	public Game(Window window){
		this.window = window;
		this.enemies = new ArrayList<Enemy>();
		this.blocks = new ArrayList<Block>();
		this.traps = new ArrayList<Trap>();
		this.objects = new ArrayList<Object>();
		this.teleporter = new ArrayList<Teleporter>(1);
		
		//Player creation
		int playerXStart = ran.nextInt(this.size);
		int playerYStart = ran.nextInt(this.size);
		player = new Player(playerXStart,playerYStart,10);
		matrixFilled[playerXStart][playerYStart] = 1;
		
		
		//Teleporter generation
		int teleporterX = ran.nextInt(this.size);
		int teleporterY = ran.nextInt(this.size);
		matrixFilled[teleporterX][teleporterY] = 1;
		this.teleporter.add(new Teleporter(teleporterX,teleporterY, ran.nextInt(this.size),ran.nextInt(this.size)));
		
		
		
		//Blocks entities generation
		for(int i = 0;i<12;i++){
			int xC = ran.nextInt(this.size);
			int yC = ran.nextInt(this.size);
			for(int j = 0;j<30;j++){
				int xB = xC+ran.nextInt(9)-3;
				int yB = yC+ran.nextInt(10)-5;
				if(xB>-1&&yB>-1&&xB<this.size&&yB<this.size&&matrixFilled[xB][yB]==0){
					this.blocks.add(new Block(xB,yB));
					this.nbrBlocks++;
					matrixFilled[xB][yB] = 1;
				}
			}
		}
		
		//Traps generation
		for(int i = 0;i<nbrTrapsDesired;i++){
			int xTrap = ran.nextInt(this.size);
			int yTrap = ran.nextInt(this.size);
			if(matrixFilled[xTrap][yTrap] == 0){
				this.traps.add(new Trap(xTrap,yTrap));
				this.nbrTraps++;
				matrixFilled[xTrap][yTrap] = 1;
			}
		}
		
		//Enemies generation
		for(int i = 0;i<nbrEnemyDesired;i++){
			int ennemyXStart = ran.nextInt(this.size);
			int ennemyYStart = ran.nextInt(this.size);
			if(matrixFilled[ennemyXStart][ennemyYStart] == 0){
				this.enemies.add(new Enemy(ennemyXStart,ennemyYStart,3));
				nbrEnemy++;
				matrixFilled[ennemyXStart][ennemyYStart] = 1;
			}
		}
		//Initiation des threads de l'intelligence artificielle et de l'activation des pièges
		Thread t = new Thread(new MobAI(this));
		t.start();
		Thread traps = new Thread(new TrapsThread(this));
		traps.start();
		window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
		
	}
	
	// Boucle gérant le déplacement d'un personnage
	public void moveCharacter(int x, int y, int entityIndex){
		if(entityIndex<0){
			if (window.askCollision(x,y, this.player.getX(),this.player.getY())){ // Gestion des collisions
				this.player.move(x, y);
				window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
				if(this.player.getX() == this.teleporter.get(0).getX() && this.player.getY() == this.teleporter.get(0).getY()){ // Teleportation
					this.player.setPosition(this.teleporter.get(0).destinationX, this.teleporter.get(0).destinationY);
					int teleporterX = ran.nextInt(this.size);
					int teleporterY = ran.nextInt(this.size);
					while(this.matrixFilled[teleporterX][teleporterY]==1){
						teleporterX = ran.nextInt(this.size);
						teleporterY = ran.nextInt(this.size);
					}
					this.teleporter.set(0, new Teleporter(teleporterX,teleporterY, ran.nextInt(this.size),ran.nextInt(this.size)));
				}
				for(int i=0;i<this.nbrObjects;i++){ // Ramassage d'objets
					if(this.player.getX()==this.objects.get(i).getPosX() && this.player.getY()==this.objects.get(i).getPosY()){
						
						if(ran.nextInt(30)<24){
							if(this.player.getNbrHpPotions()<5){
								this.player.addToInventoryHPpotion();
								this.objects.remove(i);
								this.nbrObjects--;
							}
						}else if(this.player.getNbrTalisman()==0){
							this.player.addToInventoryTalisman();
							this.objects.remove(i);
							this.nbrObjects--;
						}
						
					}
				}
			}
		}else{ // Déplacement classique et disparition partielle du brouillard de guerre
			if (window.askCollision(x,y, this.enemies.get(entityIndex).getX(),this.enemies.get(entityIndex).getY())){
				this.enemies.get(entityIndex).move(x, y);
				window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
			}
		}
	}
	
	public void playerUse(){
		if(this.player.getStatus()==0){
			this.player.setAttackStatus(1);
			System.out.println("Joueur attaque");
			window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
		}
		else if(this.player.getStatus()==1){
			this.player.setAttackStatus(0);
			System.out.println("Joueur repos");
			window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
		}
	}
	
	public void playerAttack(int dirX, int dirY){ // Attaque du personnage
		for(int i=0;i<nbrEnemy;i++){
			if(this.enemies.get(i).getX()==this.player.getX()+dirX && this.enemies.get(i).getY()==this.player.getY()+dirY){
				this.enemies.get(i).setHP(this.enemies.get(i).getHP()-this.player.getAttackPower());
				if(this.enemies.get(i).getHP() <1){
					if(ran.nextInt(99)<99){
						this.objects.add(new Object(this.enemies.get(i).getX(),this.enemies.get(i).getY()));
						this.nbrObjects++;
					}
					this.enemies.remove(i);
					nbrEnemy--;
					window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
				}
			}
		}
	}
	
	public void playerAOE(){ //Attaque de zone
		for(int i=0;i<nbrEnemy;i++){
			if( Math.abs(this.enemies.get(i).getX()-this.player.getX())<2 && Math.abs(this.enemies.get(i).getY()-this.player.getY())<2 ){
				this.enemies.get(i).setHP(this.enemies.get(i).getHP()-this.player.getAttackPower()-2);
				if(this.enemies.get(i).getHP() <1){
					this.enemies.remove(i);
					nbrEnemy--;
					window.draw(this.getMap(),this.player.getDiscoveryRange(),this.player.getHP());
				}
			}
		}
	}
	
	public int[][] getMap(){ //Détermination de la value de chaque case qui sera ensuite représentée graphiquement en fonction de sa value
		int[][] map = new int[this.size][this.size];
		for(int i = 0; i<this.size; i++)
			for(int j = 0; j<this.size; j++){
				map[i][j] = 0;
			}
		for(int i=0;i<this.nbrBlocks;i++){
			map[this.blocks.get(i).getX()][this.blocks.get(i).getY()] = -2;
		}
		for(int i=0;i<nbrTraps;i++){
			map[this.traps.get(i).getX()][this.traps.get(i).getY()]=10;
		}
		for(int i=0;i<this.nbrObjects;i++){
			if(map[this.objects.get(i).getPosX()][this.objects.get(i).getPosY()]==10){
				map[this.objects.get(i).getPosX()][this.objects.get(i).getPosY()] = 21;
			}else{
				map[this.objects.get(i).getPosX()][this.objects.get(i).getPosY()] = 20;
			}
		}
			
		for(int i=0;i<nbrEnemy;i++){
			map[this.enemies.get(i).getX()][this.enemies.get(i).getY()] = -3;
		}
		
		map[this.teleporter.get(0).getX()][this.teleporter.get(0).getY()]=11;
		
		int attackStatus = this.player.getStatus();
		if(attackStatus == 0){
			map[this.player.getX()][this.player.getY()] = 2;
		}else if (attackStatus == 1){
			map[this.player.getX()][this.player.getY()] = 3;
		}
		return map;
	}
	
	
	
	
	public void playerUseHpPotion(){
		if(this.player.getNbrHpPotions()>0){
			this.player.setHP(this.player.getHP()+5);
			this.player.removeFromInventoryHPpotion();
		}
	}
	
	public void playerUseTalisman(){
		if(this.player.getNbrTalisman()>0){
			this.player.useTalisman();
			this.player.removeFromInventoryTalisman();
		}
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
}

