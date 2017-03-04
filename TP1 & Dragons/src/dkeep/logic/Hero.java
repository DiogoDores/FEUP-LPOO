package dkeep.logic;

public class Hero {

	protected int x, y;
	protected char symbol;

	public Hero(){}

	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'H';
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

	public void move(GameMap map, char c) {

		boolean won = false;

		char result;
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
			
			//Ainda tenho que pensar na condição
			
			/*if (map == ) {
				if (c == 'w') {
					a[row][col] = ' ';
					a[row - 1][col] = 'K';


				} else if (c == 'd') {
					a[row][col] = ' ';
					a[row][col + 1] = 'K';
				}

			}
			else {*/
				map.activateLever(new Hero(x, y));
			//}

		}

	}

}
