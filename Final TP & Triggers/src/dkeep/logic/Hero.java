package dkeep.logic;

/**
 * Handles all of the hero's movement
 * @author Diogo Dores, Pedro Reis
 */

public class Hero {

	protected int x, y;
	protected char symbol;
 
	/**
	 * 
	 * @param c Sets the hero's symbol
	 */
	public void setSymbol(char c) {
		symbol = c;
	}
	
	/**
	 * Empty constructor
	 */
	public Hero(){}

	/**
	 * Constructor
	 * @param x This is the x coordinate
	 * @param y This is the y coordinate
	 */
	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'H';
	}

	/**
	 * 
	 * @param xNew Sets the hero's x coordinate 
	 */
	public void setX(int xNew){
		x = xNew;
	}
	/**
	 * 
	 * @param xNew Sets the hero's y coordinate 
	 */
	public void setY(int yNew){
		y = yNew;
	}
	
	/**
	 * 
	 * @return int Returns the hero's x coordinate
	 */
	public int getX(){
		return x;
	}

	/**
	 * 
	 * @return int Returns the hero's y coordinate
	 */
	public int getY(){
		return y;
	}

	/**
	 * 
	 * @return char Returns the hero's symbol
	 */
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Moves hero based on map and key pressed by the player.
	 * @param game This is an object from the GameLogic class
	 * @param c This is the key selected by the user
	 */

	public void move(GameLogic game, char c) {
		char result;
		if (c == 'w') {
			result = game.currentMap.possibleMove(x - 1, y, game);
		}
		else if (c == 'a'){
			result = game.currentMap.possibleMove(x, y - 1, game);
		}
		else if (c == 's') {
			result = game.currentMap.possibleMove(x + 1, y, game);
		}
		else if (c == 'd') {
			result = game.currentMap.possibleMove(x, y + 1, game);
		}
		else
			result = 'N';


		if (result == 'H') {
			if (c == 'w') {
				x--;
			}
			else if (c == 'a') {
				y--;
			}
			else if (c == 's') {
				x++;
			} else if (c == 'd') {
				y++;
			}

		}

		else if (result == 'S') {
			if (c == 'a') {
				y--;
			} else if (c == 's') {
				x++;
			} else if (c == 'd') {
				y++;
			} else if (c == 'w') {
				x--;
			}

		} 

		else if (result == 'E') { 
			if (game.currentMap.getName() == "OgreMap") {
				if (c == 'w') {
					x--;
				} else if (c == 'd') {
					y++;
				}
				this.symbol = 'K';
				game.currentMap.getMap()[1][7] = ' '; 

			}
			else if(game.currentMap.getName() == "TestMap"){
				if (c == 'w') {
					x--;
				} else if (c == 'd') {
					y++;
				}
				this.symbol = 'K';
				game.currentMap.getMap()[1][3] = ' '; 
			} else {
				if (c == 'w') {
					x--;
				} else if (c == 'd') {
					y++;
				} else if (c == 'a') {
					y--;
				}
				 else if (c == 's') {
					x++;
				}
				game.currentMap.getMap()[x][y] = ' ';
				game.currentMap.activateLever(game);
			}

		} else if(result == 'I'){
			if(game.currentMap.getName() == "OgreMap" || game.currentMap.getName() == "TestMap" ){
				if(this.symbol == 'K'){
					game.currentMap.getMap()[1][0] = 'S';
				}
			}
		}

	}

}
