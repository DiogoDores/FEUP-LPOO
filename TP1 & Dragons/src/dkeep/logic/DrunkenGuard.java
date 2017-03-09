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

	public void sleep(){
		
		this.symbol = 'g';
		this.sleeping = true;
		
	}
}
