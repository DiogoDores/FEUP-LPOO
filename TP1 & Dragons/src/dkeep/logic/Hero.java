package dkeep.logic;

import dkeep.cli.Maps;

public class Hero {
	
	private int x, y;

	public static char[][] move(char[][] a, char c) {
		char s = 'H'; // SIMBOLO DO HEROI, PODE SER H OU K
		
		boolean won = false;
		int row = 0, col = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'H' || a[i][j] == 'K') {
					row = i;
					col = j;
					s = a[i][j];
					j = a[i].length - 1;
					i = a.length - 1;
				}
			}
		}
		char result;
		if (c == 'w') {
			result = GameLogic.next(a[row - 1][col]);
			}
		else if (c == 'a'){
			result = GameLogic.next(a[row][col - 1]);
			}
		else if (c == 's') {
			result = GameLogic.next(a[row + 1][col]);
			}
		else if (c == 'd') {
			result = GameLogic.next(a[row][col + 1]);
		}
		else
			result = 'N';
		if (result == 'H') {
			if (c == 'w') {
				a[row][col] = ' ';
				a[row - 1][col] = s;
			}

			else if (c == 'a') {
				a[row][col] = ' ';
				a[row][col - 1] = s;
			}

			else if (c == 's') {
				a[row][col] = ' ';
				a[row + 1][col] = s;
			} else if (c == 'd') {
				a[row][col] = ' ';
				a[row][col + 1] = s;
			}

		}
 
		else if (result == 'S') {
			if (c == 'a' && col > 1) {
				a[row][col] = ' ';
				a[row][col - 2] = s;
			} else if (c == 'a' && col == 1) {
				a[row][col] = ' ';
				a[row][col - 1] = s;
			} else if (c == 'd') {
				a[row][col] = ' ';
				a[row][col + 2] = s;
			}

		}

		else if (result == 'E') {
			System.out.println("You got the key, make your escape!");
			System.out.println(GameLogic.isLevelTwo);
			if (GameLogic.isLevelTwo) {
				if (c == 'w') {
					a[row][col] = ' ';
					a[row - 1][col] = 'K';
					
					
				} else if (c == 'd') {
					a[row][col] = ' ';
					a[row][col + 1] = 'K';
				}
				
			}
			else {
				a = Maps.activateLever(a);
			}

			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					if (a[i][j] == 'I')
						a[i][j] = 'S';
				}
			}
		}

		

		return a;

	}


	
	public Hero(int x, int y){
		this.x = x;
		this.y = y;	
	}
	
	public int getX(){
		return x;	
	}
	
	public int getY(){
		return y;
	}
	
}
