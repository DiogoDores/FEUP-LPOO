package dkeep.logic;

public class Hero {

	protected int x, y;
	protected char symbol;
 
	public void setSymbol(char c) {
		symbol = c;
	}
	
	public Hero(){}

	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'H';
	}

	public void setX(int xNew){
		x = xNew;
	}
	public void setY(int yNew){
		y = yNew;
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
	
	/**
	 * Moves hero based on map and key pressed by the player.
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
			if (c == 'a' && y > 1) {
				y -= 2;
			} else if (c == 'a' && y == 1) {
				y--;
			} else if (c == 'd') {
				y +=2;
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
				game.currentMap.activateLever(new Hero(x, y));
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
