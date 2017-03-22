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
			if (map.getName() == "OgreMap") {
				if (c == 'w') {
					x--;
				} else if (c == 'd') {
					y++;
				}
				this.symbol = 'K';
				map.getMap()[1][7] = ' '; 

			}
			else if(map.getName() == "TestMap"){
				if (c == 'w') {
					x--;
				} else if (c == 'd') {
					y++;
				}
				this.symbol = 'K';
				map.getMap()[1][3] = ' '; 
			} else {
				map.activateLever(new Hero(x, y));
			}

		} else if(result == 'I'){
			if(map.getName() == "OgreMap" || map.getName() == "TestMap" ){
				if(this.symbol == 'K'){
					map.getMap()[1][0] = 'S';
				}
			}
		}

	}

}
