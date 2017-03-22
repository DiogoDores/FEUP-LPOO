package dkeep.logic;

import java.util.Random;

public class SuspiciousGuard extends Guard{
 
	public SuspiciousGuard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}

	public void move(){

		Random random = new Random();
		int action = random.nextInt(3);

		if(action == 0 || action == 1){
			followPath();
		} else if(action == 2){
			followInversePath();
		}
	}
}
