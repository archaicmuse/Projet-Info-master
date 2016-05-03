package Model;
import java.util.Random;

public class RandomGenerator {
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int  n = rand.nextInt(max-min+1) + min;
		System.out.println(n);
		return  n;
	}
	public void main (String args[]){
	int min = 0;
	int max = 50;
	randInt(min, max);
	}
}