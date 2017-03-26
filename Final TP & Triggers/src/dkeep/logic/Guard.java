package dkeep.logic;

public class Guard {
	 
	protected char symbol;
	protected char[] path = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected int x, y, position;
	
	public Guard(){}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public char getSymbol(){
		return symbol;
	}
	
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
	
	public char getGuardMove(){
		return path[position];
	}
}
