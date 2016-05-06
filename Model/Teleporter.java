package Model;
import java.util.Random;

// Teleporteur plaçant le joueur sur une case aléatoire
public class Teleporter extends Block {
	
	Random ran = new Random();
	int destinationX;
	int destinationY;

	public Teleporter(int X, int Y, int xDestination, int yDestination) {
		super(X, Y);
		this.destinationX = xDestination;
		this.destinationY = yDestination;
	}
	
	
	
}
