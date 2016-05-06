package Model;

// CLASSE A SUPPRIMER CAR REMPLACEE PAR MOBAI

public class EnemyMove {
	private int posX;
	private int posY;
	private int stepX;
	private int stepY;
	private int tileEnemy;
	private int tileStatus;
	
	
	public int getX(){
		return this.posX;
	}
	
	public int getY(){
		return this.posY;
	}
	
	public static String Direction(int n){
		String move = null;
		if (n == 0){
			move = "STILL";
		}
		if (n == 1){
			move = "LEFT";
		}	
		if (n == 2){
			move = "RIGHT";
		}	
		if (n == 3){
			move = "UP";
		}	
		if (n == 4){
			move = "DOWN";
		}	
		return move;
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
	
	public void moveFrom(String move){
		if (move == "STILL"){
			stepX = 0;
			stepY = 0;
		}
		if (move == "LEFT"){
			stepX = -1;
			stepY = 0;
		}	
		if (move == "RIGHT"){
			stepX = 1;
			stepY = 0;
		}	
		if (move == "UP"){
			stepX = 0;
			stepY = 1;
		}	
		if (move == "DOWN"){
			stepX = 0;
			stepY = -1;
		}	
		int i = this.getX()+stepX;
		int j = this.getY()+stepY;
		tileStatus = 0;
		if( (!(this.getX()+stepX <0 || this.getX()+stepX > 50 || this.getY()+stepY<0 || this.getY()+stepY>50)) & tileStatus==0){
			this.posX += stepX;
			this.posY += stepY;
			tileStatus = tileEnemy ; 
		}else{
			System.out.println("Collision bord");
		}
	}	
}