package dkeep.logic;

/**
 * Handles all the guards behaviors
 * Mother class to DrunkenGuard, RookieGuard and SuspiciousGuard
 */

public class Guard {
	 
	protected char symbol;
	protected char[] path = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected int x, y, position;
	
	public Guard(){}
	
	/**
	 * Gets the guard's x coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gets the guard's y coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Gets the guard's symbol
	 */
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Abstract function for all the child classes
	 */
	public void move(){
		return;
	}
	
	/**
	 * Follows set Path.
	 */
	
	public void followPath(){
		if(path[position] == 'w'){
			x--;
		} else if(path[position] == 'a'){
			y--;
		} else if(path[position] == 's'){
			x++;
		} else if(path[position] == 'd'){
			y++;
		}
		position++;
		
		if(position == path.length){
			position = 0;
		}
	}
	
	/**
	 * Follows inverse set Path.
	 */
	public void followInversePath(){
		
		if(position == 0){
			position = path.length;
		}
		
		position--;
		
		if(path[position] == 'w'){
			x++;
		} else if(path[position] == 'a'){
			y++;
		} else if(path[position] == 's'){
			x--;
		} else if(path[position] == 'd'){
			y--;
		}
	}
	
	/**
	 * Gets the guards position
	 * @return char This returns the current position of the guard
	 */
	public char getGuardMove(){
		return path[position];
	}
}
