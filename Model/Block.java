package Model;

// Classe bloc poss�dant une coordonn� X et Y sur la carte

public class Block {
	private int posX;
	private int posY;
	
	public Block(int X, int Y){
		this.posX = X;
		this.posY = Y;
	}
	
	public int getX(){
		return this.posX;
	}
	
	public int getY(){
		return this.posY;
	}
}
