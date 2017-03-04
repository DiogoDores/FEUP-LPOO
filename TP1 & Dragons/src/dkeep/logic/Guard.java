package dkeep.logic;

public class Guard {
	
	protected char symbol;
	protected char[] path = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected char[] inversePath = {'s', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'w', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'd'};
	protected int x, y, position = 0, inversePosition = 0;
	
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
		inversePosition++;
		
		if(position == path.length){
			position = 0;
		}
	}
	
	public void followInversePath(){
		if(inversePath[inversePosition] == 'w'){
			x--;
		} else if(inversePath[inversePosition] == 'a'){
			y--;
		} else if(inversePath[inversePosition] == 's'){
			x++;
		} else if(inversePath[inversePosition] == 'd'){
			y++;
		}
		position++;
		inversePosition++;
		
		if(inversePosition == inversePath.length){
			inversePosition = 0;
		}
	}
}
