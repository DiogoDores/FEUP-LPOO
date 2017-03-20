package dkeep.logic;

import java.util.Random;

public class Ogre {

	private int isStunned;
	protected int x, y, clubX, clubY;
	protected char symbol, clubSymbol;
	private boolean restoreSymbol = false;
	private boolean restoreClubSymbol = false;

	public Ogre(){}

	public void createClub() {
		do {
			char key = createRandomMove();


			if(key == 'w'){
				this.clubX = this.x - 1; 
				this.clubY = this.y;
			} else if(key == 'a') {
				this.clubY = this.y - 1;
				this.clubX = this.x;
			}
			else if(key == 's') {
				this.clubX = this.x + 1;
				this.clubY = this.y;
			}
			else if(key == 'd') {
				this.clubY = this.y + 1;
				this.clubX = this.x;
			}
		} while (this.clubX == 0 || this.clubX == 8 || this.clubY == 0 || this.clubY == 8);
	}

	public void stunOgre() {
		isStunned = 2;
		this.symbol = '8';
	}

	public Ogre(int x, int y){
		isStunned = 0;
		this.x = x;
		this.y = y;
		this.symbol = 'O';
		createClub();
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

	public void moveOgre(GameLogic game) {
		if (isStunned == 0) {
			this.symbol = 'O';
			char c;
			char result;

			do{
				c = createRandomMove(); 
				result = checkPossible(c, game);
			} while(result == 'X' || result == 'I' || result == 'S' || result == 'N' || result == '*');

			if (game.currentMap.getMap()[x][y] == 'k')
				restoreSymbol = true;
			else
				restoreSymbol = false;
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
		else {
			isStunned--;
		}

	}

	public void moveClub(GameLogic game, int x, int y) {
		clubX = this.x; clubY = this.y;
		char club, clubResult;
		do{
			club = createRandomMove();
			clubResult = checkPossibleClub(club, game);
		} while(clubResult == 'X' || clubResult == 'I' || clubResult == 'S' || clubResult == 'N' || clubResult == 'O' || clubResult == '*');

		if (game.currentMap.getMap()[x][y] == 'k'){
			restoreClubSymbol = true;
			this.clubSymbol = '$';
		}
		else {
			restoreClubSymbol = false;
			this.clubSymbol = '*';
		}

		if(clubResult == 'H'){



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

	public char checkPossible(char key, GameLogic game) {

		char result;

		if (key == 'w' && key != 'a' && key != 's' && key != 'd') {
			result = game.currentMap.possibleMove(x - 1, y);
		} else if (key == 'a' && key != 's' && key != 'd' && key != 'w'){
			result = game.currentMap.possibleMove(x, y - 1);
		} else if (key == 's' && key != 'a' && key != 'd' && key != 'w') {
			result = game.currentMap.possibleMove(x + 1, y);
		} else if (key == 'd' && key != 'a' && key != 'w' && key != 's') {
			result = game.currentMap.possibleMove(x, y + 1);
		} else {
			result = 'N';
		}

		return result;

	}

	public char checkPossibleClub(char key, GameLogic game) {

		char result;

		if (key == 'w' && key != 'a' && key != 's' && key != 'd') {
			result = game.currentMap.possibleMove(clubX - 1, clubY, game );
		} else if (key == 'a' && key != 's' && key != 'd' && key != 'w'){
			result = game.currentMap.possibleMove(clubX, clubY - 1, game);
		} else if (key == 's' && key != 'a' && key != 'd' && key != 'w') {
			result = game.currentMap.possibleMove(clubX + 1, clubY, game);
		} else if (key == 'd' && key != 'a' && key != 'w' && key != 's') {
			result = game.currentMap.possibleMove(clubX, clubY + 1, game);
		} else {
			result = 'N';
		}

		return result;

	}


}
