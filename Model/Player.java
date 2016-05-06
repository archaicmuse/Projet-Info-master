package Model;

// Classe du personnage jouable reprenant ses attributs et des méthodes


public class Player extends Character{
	private int discoveryRange = 5;
	private int attackPower = 3;
	private int nbrHpPotions = 0;
	private int nbrTalisman = 0;
	private int invincibilityStatus = 0;
	public Player(int X, int Y, int firstHP) {	
		super(X, Y, firstHP);
	}
	
	
	public void addToInventoryHPpotion(){
		this.nbrHpPotions++;
	}
	public void removeFromInventoryHPpotion(){
		if(nbrHpPotions>0){
			this.nbrHpPotions--;
		}
	}
	public int getNbrHpPotions(){
		return this.nbrHpPotions;
	}
	
	public void usePotion(){
		this.setHP(this.getHP()+5);
	}
	
	
	public void addToInventoryTalisman(){
		this.nbrTalisman++;
	}
	public void removeFromInventoryTalisman(){
		if(nbrTalisman>0){
			this.nbrTalisman--;
		}
	}
	public int getNbrTalisman(){
		return this.nbrTalisman;
	}
	
	
	public void useTalisman(){
		this.invincibilityStatus = 10;
	}

	
	
	
	public int getDiscoveryRange(){
		return this.discoveryRange;
	}
	public void setDiscoveryRange(int newRange){
		this.discoveryRange = newRange;
	}
	
	public int getAttackPower(){
		return this.attackPower;
	}
	public int getInvincibilityStatus(){
		return this.invincibilityStatus;
	}
	public void setInvincibilityStatus(int newStatus){
		this.invincibilityStatus = newStatus;
	}

	

}
