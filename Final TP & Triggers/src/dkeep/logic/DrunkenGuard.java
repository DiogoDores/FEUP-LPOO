package dkeep.logic;

import java.util.Random;

/**
 * Handles the behavior of a Drunken Guard
 * Extends the Guard class
 * 
 * @author Diogo Dores, Pedro Reis
 */

public class DrunkenGuard extends Guard {
	
	private boolean sleeping = false;
 
	/**
	 * Creates a new instance of a Drunken Guard
	 * @param x This is the x coordinates
	 * @param y This is the y coordinates
	 */
	public DrunkenGuard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}

	/**
	 * Handles the movement of a Drunken Guard
	 */
	public void move(){

		Random random = new Random();
		int action = random.nextInt(3);
		
		if(sleeping){
			
			int wakeUp = random.nextInt(5);
			
			if(wakeUp == 0){
				sleep(); 
			} else if(wakeUp == 1 || wakeUp == 2){
				this.symbol = 'G';
				this.sleeping = false;
				followPath();
			} else if(wakeUp == 3 || wakeUp == 4){
				this.symbol = 'G';
				this.sleeping = false;
				followInversePath();
			}
			
			return;
		}

		if(action == 0 || action == 1){
			followPath();
		} else if(action == 2){
			sleep();
		}
	
	}

	/**
	 * Used when the guard falls asleep
	 */
	public void sleep(){
		
		this.symbol = 'g';
		this.sleeping = true;
		
	}
}
