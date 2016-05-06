package Model;

// Item 
public class Object {
	private int posX;
	private int posY;
	
	public Object(int newPosX, int newPosY){

		this.posX = newPosX;
		this.posY = newPosY;
	}
	
	
	public int getPosX(){
		return this.posX;
	}
	public int getPosY(){
		return this.posY;
	}

	public void setPosX(int newPosX){
		this.posX = newPosX;
	}
	public void setPosY(int newPosY){
		this.posY = newPosY;
	}
}
