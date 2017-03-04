package dkeep.logic;

public class Guard {
	
	protected int x, y, position;
	protected char symbol;
	protected char[] path = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	
	public Guard(){}
	
	public Guard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}
	
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
}
