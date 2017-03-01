package dkeep.logic;
  
public class Guard {
	
	protected int x, y, position;
	protected char path[] = {'a', 's', 's', 's','s', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected char symbol;
	
	public Guard(){}
	
	public Guard(int x, int y){
		this.x = x;
		this.y = y;	
		this.symbol = 'G';
		this.position = 0;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public char getSymbol(){
		return symbol;
	}
	
	public void followPath(){}
	
}
