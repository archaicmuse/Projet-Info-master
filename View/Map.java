package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.JPanel;

import Model.Game;

public class Map extends JPanel {
	public int[][] mapMatrix;
	private int DiscoveryRange;
	private int currentPlayerHP = 0;
	private int[][] mapDiscovery = new int[51][51];
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) { 
		if(this.mapMatrix == null){
		}
		else{
			int k = 0;
			int l = 0;
			for(int a = 0;a<51;a++){
				for(int b = 0;b<51;b++){
					if(checkDiscoveryRange(this.getCentreX(), this.getCentreY(), a,b)){
						this.mapDiscovery[a][b]=1;
					}
				}
			}
			
			if(this.currentPlayerHP >0){
				
			}
			for(int i = this.getCentreX()-10; i<this.getCentreX()+11; i++){
				l=0;
				for(int j = this.getCentreY()-10; j<this.getCentreY()+11; j++){
					
					int tileStatus = 0;
					int discoveryStatus = 0;
					if(i<0 || j<0 || i>50 ||j>50){
						tileStatus = -1;
						discoveryStatus = -1;
					}
					else{
						tileStatus = this.mapMatrix[i][j];
						discoveryStatus = this.mapDiscovery[i][j];
					}
					
					if(tileStatus == 0&&discoveryStatus == 1){
						g.setColor(Color.GREEN);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 1&&discoveryStatus == 1){
						g.setColor(Color.BLUE);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 2&&discoveryStatus == 1){
						g.setColor(Color.WHITE);
						g.fillRect(k*50, l*50,50,50);
						g.setColor(Color.gray);
						g.fillRect(k*50+5, l*50+5, 40, 40);
						g.setColor(Color.DARK_GRAY);
						g.fillRect(k*50+5, l*50+5, 40, (10-this.currentPlayerHP)*4);
					}else if(tileStatus == 3&&discoveryStatus == 1){
						g.setColor(Color.MAGENTA);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == -1){
						g.setColor(Color.BLACK);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == -2&&discoveryStatus == 1){
						g.setColor(new Color(139,76,57));
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 20&&discoveryStatus == 1){
						g.setColor(Color.GREEN);
						g.fillRect(k*50, l*50,50,50);
						g.setColor(Color.YELLOW);
						g.fillRect(k*50+20, l*50+20,10,10);
					}else if(tileStatus == 21&&discoveryStatus == 1){
						g.setColor(new Color(102,204,0));
						g.fillRect(k*50, l*50,50,50);
						g.setColor(Color.YELLOW);
						g.fillRect(k*50+20, l*50+20,10,10);
					}else if(tileStatus == -3&&discoveryStatus == 1){
						g.setColor(Color.RED);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 10&&discoveryStatus == 1){
						g.setColor(new Color(102,204,0));
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 11&&discoveryStatus == 1){
						g.setColor(Color.BLUE);
						g.fillRect(k*50, l*50,50,50);
						g.setColor(Color.MAGENTA);
						g.fillRect(k*50+8, l*50+8, 34, 34);
						g.setColor(Color.BLUE);
						g.fillRect(k*50+12, l*50+12,26,26);
					}else{
						g.setColor(Color.BLACK);
						g.fillRect(k*50, l*50,50,50);
					}
					g.setFont(g.getFont().deriveFont(24.0f));
					g.setColor(Color.WHITE);
					g.fillRect(670, 0, 416, 160);
					g.setColor(Color.BLUE);
					g.fillRect(678, 0, 408, 152);
					g.setColor(Color.WHITE);
					g.drawString("HP : "+ Integer.toString(Game.player.getHP()), 700, 30);
					g.drawString("Potions in bag : " + Integer.toString(Game.player.getNbrHpPotions()), 800, 30);
					g.drawString("Talisman : " + Integer.toString(Game.player.getNbrTalisman()), 700, 65);
					g.drawString("Immunity : "+ Integer.toString(Game.player.getInvincibilityStatus()), 700, 100);
					g.drawString("Enemies left : "+ Integer.toString(Game.nbrEnemy), 700,135);
					 
					l++;
				}
				k++;
			}
		}
	}
	
	
	
	public void setMapMatrix(int[][] mapMatrix, int range, int playerHP){
		this.currentPlayerHP = playerHP;
		this.DiscoveryRange = range;
		this.mapMatrix = mapMatrix;
		this.repaint();
	}
	
	public int getCentreX(){
		int xValue = 10;
		for(int i = 0;i<51;i++){
			for(int j=0; j<51; j++){
				if(this.mapMatrix[i][j] == 2 || this.mapMatrix[i][j] == 3){
					xValue = i;
				}
			}
		}
		return xValue;
	}
	public int getCentreY(){
		int yValue = 10;
		for(int i = 0;i<51;i++){
			for(int j=0; j<51; j++){
				if(this.mapMatrix[i][j] == 2 || this.mapMatrix[i][j] == 3){
					yValue = j;
				}
			}
		}
		return yValue;
	}
	
	public boolean checkCollision(int depX, int depY, int currentX, int currentY){
		if(currentX+depX<0 || currentY+depY<0 || currentX+depX>50||currentY+depY>50){
			return false;
		}else if (this.mapMatrix[currentX+depX][currentY+depY]>=0){
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean checkDiscoveryRange(int centreX, int centreY, int testX, int testY){
		if(Math.sqrt( Math.pow(centreX-testX, 2)+Math.pow(centreY-testY, 2))<this.DiscoveryRange){
			return true;
		}else{
			return false;
		}
	}
	
}
