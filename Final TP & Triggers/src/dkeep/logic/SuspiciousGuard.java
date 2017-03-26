package dkeep.logic;

import java.util.Random;

/**
 * Handles the behavior of a Suspicious Guard
 * Extends the Guard class
 * 
 * @author Diogo Dores, Pedro Reis
 */

public class SuspiciousGuard extends Guard{
 
	/**
	 * Creates a new instance of a Suspicious Guard
	 * @param x This is the x coordinates
	 * @param y This is the y coordinates
	 */
	public SuspiciousGuard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}

	/**
	 * Handles the movement of a Suspicious Guard
	 */
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
