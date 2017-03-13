package dkeep.logic;

import java.util.Random;

public class Ogre {

	protected int x, y, clubX, clubY;
	protected char symbol, clubSymbol;
	private boolean restoreSymbol = false;
	private boolean restoreClubSymbol = false;

	public Ogre(){}
	
	public Ogre(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'O';
		this.clubSymbol = '*';
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
	
	public int getClubX(){
		return clubX;
	}

	public int getClubY(){
		return clubY;
	}
	
	public char getClubSymbol(){
		return clubSymbol;
	}

	public void moveOgre(GameMap map) {

		char c;
		char result;

		do{
			c = createRandomMove(); 
			result = checkPossible(c, map);
		} while(result == 'X' || result == 'I' || result == 'S' || result == 'N');

		if (result == 'H') {
			
			if(restoreSymbol){
				this.symbol = 'O';
				restoreSymbol = false;
			}
			
			if (c == 'w') {
				x--;
			} else if (c == 'a') {
				y--;
			} else if (c == 's') {
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

	public void moveClub(GameMap map, int x, int y) {
		
		char club, clubResult;
		 
		do{
			club = createRandomMove();
			clubResult = checkPossible(club, map);
		} while(clubResult == 'X' || clubResult == 'I' || clubResult == 'S' || clubResult == 'N');

		if(clubResult == 'H'){
			
			if(restoreClubSymbol){
				this.symbol = '*';
				restoreClubSymbol = false;
			}
			
			if (club == 'w') {
				clubX = x - 1;
				clubY = y;
			} else if (club == 'a'){
				clubY = y - 1;
				clubX = x;
			} else if (club == 's') {
				clubX = x + 1;
				clubY = y;
			} else if (club == 'd') {
				clubY = y + 1;
				clubX = x;
			}

		} else if(clubResult == 'E'){
			if(club == 'w'){
				clubX = x - 1;
				clubY = y;
			} else if (club == 'd'){
				clubY = y + 1;
				clubX = x; 
			}
			clubSymbol = '$';
			restoreClubSymbol = true;
		}
	}
	
	public char createRandomMove(){
		String move = "wasd";
		Random random = new Random();
		int r = random.nextInt(move.length());
		char c = move.charAt(r);

		return c;
	}
	
	public char checkPossible(char key, GameMap map) {

		char result;

		if (key == 'w') {
			result = map.possibleMove(x - 1, y);
		} else if (key == 'a'){
			result = map.possibleMove(x, y - 1);
		} else if (key == 's') {
			result = map.possibleMove(x + 1, y);
		} else if (key == 'd') {
			result = map.possibleMove(x, y + 1);
		} else {
			result = 'N';
		}

		return result;

	}

}
