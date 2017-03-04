package dkeep.logic;

import java.util.Random;

public class Ogre {

	protected int x, y;
	protected char symbol, club;
	private boolean restoreSymbol = false;

	public Ogre(){}

	public Ogre(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'O';
		this.club = '*';
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

	public void moveOgre(GameMap map) {
		
		char c;
		
		char result;

		do{
			c = createRandomMove();
			
			if (c == 'w') {
				result = map.possibleMove(x - 1, y);
			}
			else if (c == 'a'){
				result = map.possibleMove(x, y - 1);
			}
			else if (c == 's') {
				result = map.possibleMove(x + 1, y);
			}
			else if (c == 'd') {
				result = map.possibleMove(x, y + 1);
			}
			else
				result = 'N';
			
		} while(result == 'X' || result == 'I' || result == 'S');


		if (result == 'H') {
			if (c == 'w') {
				x--;
			} else if (c == 'a') {

				if(restoreSymbol){
					this.symbol = 'O';
					restoreSymbol = false;
				}

				y--;
			} else if (c == 's') {

				if(restoreSymbol){
					this.symbol = 'O';
					restoreSymbol = false;
				}

				x++;
			} else if (c == 'd') {
				y++;
			}

		} else if(result == 'E'){
			if(c == 'w'){
				x--;
			} else if (c == 'd'){
				y++;
			}
			this.symbol = '$';
			this.restoreSymbol = true;
		}

	}
	
	public char createRandomMove(){
		String move = "wasd";
		Random random = new Random();
		int r = random.nextInt(move.length());
		char c = move.charAt(r);
		
		return c;
	}

}
