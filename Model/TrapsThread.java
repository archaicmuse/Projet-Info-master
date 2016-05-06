package Model;

public class TrapsThread implements Runnable{
	private int posX;
	private int posY;
	
	private int waitTime = 500;
	
	private Game game;
	
	public TrapsThread(Game game){
		this.game = game;
	}
	
	// Condition permettant de vérifier si un joueur est sur un piège ou non
	public boolean playerOnTrap(Player player, Trap trap){
		boolean res = false;
		
		if(player.getX() == trap.getX() && player.getY() == trap.getY()){
			res = true;
		}
		System.out.println(res);
		return res;
	}
	
	
	
	//Thread qui vérifie à chaque instant si le joueur est sur un piège ou non et lui fait perdre de la vie en conséquence
	@Override
	public void run() {
		try {
			while(true){
				for(int i=0;i<game.nbrTraps;i++){
					if(playerOnTrap(game.player,game.traps.get(i))){
						if(game.player.getInvincibilityStatus()==0){
							game.player.setHP(game.player.getHP()-1);
						}else{
							game.player.setInvincibilityStatus(game.player.getInvincibilityStatus()-1);
						}
						game.window.draw(game.getMap(),game.player.getDiscoveryRange(),game.player.getHP());
						if (game.player.getHP()<1){
							System.exit(0);
						}
						Thread.sleep(waitTime);
					}
				}
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}

}
