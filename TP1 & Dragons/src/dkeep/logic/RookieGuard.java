package dkeep.logic;

public class RookieGuard extends Guard{
	
	public RookieGuard(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}
	
	public void moveRookie(){
		followPath();
	}

}
