package Model;

public class Object {
	private int ID;
	private int inInventory;
	private int equippedStatus;
	private int posX;
	private int posY;
	
	
	public void Object(int newID, int isInInventory, int isEquippedStatus, int newPosX, int newPosY){
		this.ID = newID;
		this.inInventory = isInInventory;
		this.equippedStatus = isEquippedStatus;
		this.posX = newPosX;
		this.posY = newPosY;
	}
	
	public int getObjectID(){
		return this.ID;
	}
	
	public int getInInventory(){
		return this.inInventory;
	}
	
	public int getEquippedStatus(){
		return this.equippedStatus;
	}
	
	public int getPosX(){
		return this.posX;
	}
	public int getPosY(){
		return this.posY;
	}
	
	public void setInInventory(int newStatus){
		this.inInventory = newStatus;
	}
	public void setEquippedStatus(int newStatus){
		this.equippedStatus = newStatus;
	}
	public void setPosX(int newPosX){
		this.posX = newPosX;
	}
	public void setPosY(int newPosY){
		this.posY = newPosY;
	}
}
