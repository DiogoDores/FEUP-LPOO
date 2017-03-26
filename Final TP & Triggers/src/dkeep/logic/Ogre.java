package dkeep.logic;
/**
 * Handles the ogre's movement
 * @author Diogo Dores, Pedro Reis
 */
import java.util.Random;

public class Ogre {

	public int isStunned;
	protected int x, y, clubX, clubY;
	protected char symbol, clubSymbol;
	private boolean restoreSymbol = false;
	public Ogre(){}
	
	/**
	 * Creates a club on a position that is not on the walls. 
	 * Can't be on player's position, but this is checked in another function.
	 * @param width This is the map's width
	 * @param height This is the map's height
	 */

	public void createClub(int width, int height) {
		do {
			char key = createRandomMove();


			if(key == 'w'){
				this.clubX = this.x - 1; 
				this.clubY = this.y;
			}
			if(key == 'a') {
				this.clubY = this.y - 1;
				this.clubX = this.x;
			}
			if(key == 's') {
				this.clubX = this.x + 1;
				this.clubY = this.y;
			}
			if(key == 'd') { 
				this.clubY = this.y + 1;
				this.clubX = this.x;
			}
		} while (this.clubX == 0 || this.clubX == width -1  || this.clubY == 0 || this.clubY == height -1 );
	}

	/**
	 * Stuns ogre if it gets close to the hero, but its club doesn't kill him. 
	 */

	public void stunOgre() {
		isStunned = 2;
		this.symbol = '8';
	}

	/**
	 * 
	 * @param x This is the x coordinate
	 * @param y This is the y coordinate
	 * @param width This is the map's width
	 * @param height This is the map's height
	 */
	public Ogre(int x, int y, int width, int height){
		isStunned = 0;
		this.x = x;
		this.y = y;
		this.symbol = 'O';
		createClub(width, height);
		this.clubSymbol = '*';
	}

	/**
	 * 
	 * @return int This returns the ogre's x coordinate
	 */
	public int getX(){
		return x;
	}

	/**
	 * 
	 * @return int This returns the ogre's y coordinate
	 */
	public int getY(){
		return y;
	}

	/**
	 * 
	 * @return char This returns the ogre's symbol
	 */
	public char getSymbol(){
		return symbol;
	}

	/**
	 * 
	 * @return int This returns the club's x coordinate
	 */
	public int getClubX(){
		return clubX;
	}

	/**
	 * 
	 * @return int This returns the club's y coordinate
	 */
	public int getClubY(){
		return clubY;
	}

	/**
	 * 
	 * @return char This returns the club's symbol 
	 */
	public char getClubSymbol(){
		return clubSymbol;
	}

	/**
	 * Moves an ogre
	 * @param game This is an object of the GameLogic class
	 * @param c This is the key correspondent to the ogre's next move
	 */
	public void moveOgre(GameLogic game, char c) {

		if (isStunned == 0) {
			this.symbol = 'O';
			char result;

			if(c == 'n'){
				do{
					c = createRandomMove(); 
					result = checkPossible(c, game);
				} while(result == 'X' || result == 'I' || result == 'S' || result == 'N' || result == '*');
			} else {
				result = checkPossible(c, game);
			}

			if (game.currentMap.getMap()[x][y] == 'k'){
				restoreSymbol = true;
			} else {
				restoreSymbol = false;
			}

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

	/**
	 * Moves an ogre's club
	 * @param game This is an object of the GameLogic class
	 * @param x This is the club's x coordinate
	 * @param y This is the club's y coordinate
	 */
	public void moveClub(GameLogic game, int x, int y) {
		clubX = this.x; clubY = this.y;
		char club, clubResult;
		do{
			club = createRandomMove();
			clubResult = checkPossibleClub(club, game);
		} while(clubResult == 'X' || clubResult == 'I' || clubResult == 'S' || clubResult == 'N' || clubResult == 'O' || clubResult == '*');

		if (game.currentMap.getMap()[x][y] == 'k'){
			this.clubSymbol = '$';
		}
		else {
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
		}


	}

	/**
	 * 
	 * @return char This returns a random char ('w', 'a', 's' or 'd')
	 */
	public char createRandomMove(){
		String move = "wasd";
		Random random = new Random();
		int r = random.nextInt(move.length());
		char c = move.charAt(r);

		return c;
	}

	/**
	 * Checks whether the possible move is valid or not
	 * @param key This is the ogre's next move
	 * @param game This is an object of the GameLogic class
	 * @return char This returns a char that checks whether the possible move is valid or not
	 */
	public char checkPossible(char key, GameLogic game) {

		char result;

		if (key == 'w') {
			result = game.currentMap.possibleMove(x - 1, y, game);
		} else if (key == 'a'){
			result = game.currentMap.possibleMove(x, y - 1, game);
		} else if (key == 's') {
			result = game.currentMap.possibleMove(x + 1, y, game);
		} else if (key == 'd') {
			result = game.currentMap.possibleMove(x, y + 1, game);
		} else {
			result = 'N';
		}

		return result;

	}

	/**
	 * Checks whether the club's possible move is valid or not
	 * @param key This is the ogre's club next move
	 * @param game This is an object of the GameLogic class
	 * @return char This returns a char that checks whether the club's possible move is valid or not
	 */
	public char checkPossibleClub(char key, GameLogic game) {

		char result;

		if (key == 'w') {
			result = game.currentMap.possibleMove(clubX - 1, clubY, game );
		} else if (key == 'a'){
			result = game.currentMap.possibleMove(clubX, clubY - 1, game);
		} else if (key == 's') {
			result = game.currentMap.possibleMove(clubX + 1, clubY, game);
		} else if (key == 'd') {
			result = game.currentMap.possibleMove(clubX, clubY + 1, game);
		} else {
			result = 'N';
		}

		return result;

	}


}
