package dkeep.logic;
  
public class Guard {
	
	protected int x, y;
	protected char path[] = {'a', 's', 's', 's','s', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'w', 'w'};
	protected char symbol;
	
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
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
}
