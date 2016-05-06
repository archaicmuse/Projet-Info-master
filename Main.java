import Controller.Keyboard;
import Model.Game;
import View.Window;

// Fichier main qui crée une fenêtre, charge le jeu, l'affiche et permet des interactions avec le clavier
public class Main {
	public static void main(String[] args) {
		
		Window window = new Window();
		Game game = new Game(window);
		Keyboard keyboard = new Keyboard(game);
		window.setKeyListener(keyboard);
		
		}
}
