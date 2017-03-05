package dkeep.logic;

import java.util.Random;

public class DrunkenGuard extends Guard {
	
	private boolean sleeping = false;

	public DrunkenGuard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}

	public void move(){

		Random random = new Random();
		int action = random.nextInt(3);
		
		if(sleeping){
			
			int wakeUp = random.nextInt(3);
			
			if(wakeUp == 0){
				sleep();
			} else {
				this.symbol = 'G';
				sleeping = false;
			}
		}

		if(action == 0 || action == 1){
			followPath();
		} else if(action == 2){
			sleep();
		}
	}

	public void sleep(){
		
		this.symbol = 'g';
		sleeping = true;
	}
}
