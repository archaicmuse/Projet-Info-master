package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Map extends JPanel {
	private int[][] mapMatrix;
	
	public Map(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) { 
		if(mapMatrix == null){
		}
		else{
			int k = 0;
			int l = 0;
			for(int i = this.getCentreX()-10; i<this.getCentreX()+11; i++){
				l=0;
				for(int j = this.getCentreY()-10; j<this.getCentreY()+11; j++){
					
					int tileStatus = 0;
					if(i<0 || j<0 || i>50 ||j>50){
						tileStatus = -1;
					}
					else{
						tileStatus = mapMatrix[i][j];
					}
					
					if(tileStatus == 0){
						g.setColor(Color.GREEN);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 1){
						g.setColor(Color.BLUE);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 2){
						g.setColor(Color.WHITE);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == 3){
						g.setColor(Color.MAGENTA);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == -1){
						g.setColor(Color.BLACK);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == -2){
						g.setColor(Color.yellow);
						g.fillRect(k*50, l*50,50,50);
					}else if(tileStatus == -3){
						g.setColor(Color.RED);
						g.fillRect(k*50, l*50,50,50);
					}

					 
					l++;
				}
				k++;
			}
		}
	}
	
	
	
	public void setMapMatrix(int[][] mapMatrix){
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

}
