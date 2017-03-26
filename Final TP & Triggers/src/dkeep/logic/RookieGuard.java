package dkeep.logic;
/**
 * Handles the behavior of a Rookie Guard
 * Extends the Guard class
 * 
 * @author Diogo Dores, Pedro Reis
 */
public class RookieGuard extends Guard{
	 
	/**
	 * Creates a new instance of a Rookie Guard
	 * @param x This is the x coordinates
	 * @param y This is the y coordinates
	 */
	public RookieGuard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}
	
	/**
	 * Handles the movement of a Rookie Guard
	 */
	public void move(){
		followPath();
	}

}
