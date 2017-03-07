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

		char c, club, clubResult;

		char result;

		deleteLastClub(map);

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

		} while(result == 'X' || result == 'I' || result == 'S' || result == 'N');

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

		/*
			
		do{
			club = createRandomMove();
			clubResult = generateClubPos(club, map);
		} while(clubResult == 'X' || clubResult == 'I' || clubResult == 'S' || clubResult == 'N');

		if(clubResult == 'H'){
			if (club == 'w') {
				map.getMap()[x-1][y] = '*';
			}
			else if (club == 'a'){
				map.getMap()[x][y-1] = '*';
			}
			else if (club == 's') {
				map.getMap()[x+1][y] = '*';
			}
			else if (club == 'd') {
				map.getMap()[x][y+1] = '*';
			}

		} else if(clubResult == 'E'){
			if(club == 'w'){
				map.getMap()[x-1][y] = '$';
			} else if (club == 'd'){
				map.getMap()[x][y+1] = '$';
			}
		}*/

	}

	private void deleteLastClub(GameMap map) {
		for(int i = 0; i < map.getMap().length; i++){
			for(int j = 0; j < map.getMap()[i].length; j++){
				if(map.getMap()[i][j] == '*'){
					map.getMap()[i][j] = ' ';
				} else if(map.getMap()[i][j] == '$' && restoreSymbol == false){
					map.getMap()[i][j] = 'k';
				}
			}
		}

	}

	private char generateClubPos(char club, GameMap map) {

		char result;

		if (club == 'w') {
			result = map.possibleMove(x - 1, y);
		}
		else if (club == 'a'){
			result = map.possibleMove(x, y - 1);
		}
		else if (club == 's') {
			result = map.possibleMove(x + 1, y);
		}
		else if (club == 'd') {
			result = map.possibleMove(x, y + 1);
		} else {
			result = 'N';
		}

		return result;

	}

	public char createRandomMove(){
		String move = "wasd";
		Random random = new Random();
		int r = random.nextInt(move.length());
		char c = move.charAt(r);

		return c;
	}

	public void moveClub(GameMap map) {
		
		char club, clubResult;
		 
		do{
			club = createRandomMove();
			clubResult = generateClubPos(club, map);
		} while(clubResult == 'X' || clubResult == 'I' || clubResult == 'S' || clubResult == 'N');

		if(clubResult == 'H'){
			if (club == 'w') {
				map.getMap()[x-1][y] = '*';
			}
			else if (club == 'a'){
				map.getMap()[x][y-1] = '*';
			}
			else if (club == 's') {
				map.getMap()[x+1][y] = '*';
			}
			else if (club == 'd') {
				map.getMap()[x][y+1] = '*';
			}

		} else if(clubResult == 'E'){
			if(club == 'w'){
				map.getMap()[x-1][y] = '$';
			} else if (club == 'd'){
				map.getMap()[x][y+1] = '$';
			}
		}
	}

}
