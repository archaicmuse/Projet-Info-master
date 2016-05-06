package Model;

// Classe des ennemis qui hérite de la classe Character

public class Enemy extends Character {
	public static int damage = 1;
	public Enemy(int X, int Y, int firstHP) {
		super(X, Y, firstHP);
	}

}
