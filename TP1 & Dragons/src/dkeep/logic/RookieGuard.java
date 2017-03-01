package dkeep.logic;

public class RookieGuard extends Guard{
	
	public RookieGuard(int x, int y){
		this.x = x;
		this.y = y;
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
 