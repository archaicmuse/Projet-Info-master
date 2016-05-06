package Model;

// Super classe personnage

public class Character {
	private int posX;
	private int posY;
	private int HP;
	private int attackStatus;
	
	public Character(int X, int Y, int firstHP){
		this.posX = X;
		this.posY = Y;
		this.HP = firstHP;
		this.attackStatus = 0;
	}
	
	public int getX(){
		return this.posX;
	}
	
	public int getY(){
		return this.posY;
	}
	
	public int getHP(){
		return this.HP;
	}
	
	public int getStatus(){
		return this.attackStatus;
	}
	
	
	public void goTo(int newX, int newY){
		this.posX = newX;
		this.posY = newY;
	}
	
	public void move(int stepX, int stepY){
		this.posX += stepX;
		this.posY += stepY;
	}
	
	public void setHP(int newHP){
		if(newHP>-1&&newHP<11){
			this.HP = newHP;
		}else if(newHP>10){
			this.HP = 10;
		}
	}
	
	
	public void setAttackStatus(int newStatus){
		this.attackStatus = newStatus;
	}
	
	public void setPosition(int newX, int newY){
		this.posX = newX;
		this.posY = newY;
	}


	
}
