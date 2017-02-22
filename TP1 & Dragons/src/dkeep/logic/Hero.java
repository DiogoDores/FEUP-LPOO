package dkeep.logic;

import dkeep.cli.Maps;

public class Hero {
	
	private int x, y;

	public static char[][] move(char[][] a, char c) {
		char s;
		boolean won = false;
		int row = 0, col = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'H' || a[i][j] == 'K') {
					row = i;
					col = j;
					j = a[i].length - 1;
					i = a.length - 1;
				}
			}
		}
		char result;
		if (c == 'w') {
			result = GameLogic.next(a[row - 1][col]);
			System.out.println(a[row-1][col]);
		}
		else if (c == 'a'){
			result = GameLogic.next(a[row][col - 1]);
			System.out.println(a[row][col - 1]);
		}
		else if (c == 's') {
			result = GameLogic.next(a[row + 1][col]);
			System.out.println(a[row+1][col]);
		}
		else if (c == 'd') {
			result = GameLogic.next(a[row][col + 1]);
			System.out.println(a[row][col + 1]);

		}
		else
			result = 'N';
		System.out.println(result);
		if (result == 'H') {
			if (c == 'w') {
				a[row][col] = ' ';
				a[row - 1][col] = 'H';
			}

			else if (c == 'a') {
				a[row][col] = ' ';
				a[row][col - 1] = 'H';
			}

			else if (c == 's') {
				a[row][col] = ' ';
				a[row + 1][col] = 'H';
			} else if (c == 'd') {
				a[row][col] = ' ';
				a[row][col + 1] = 'H';
			}

		}

		else if (result == 'S') {
			if (c == 'a' && col > 1) {
				a[row][col] = ' ';
				a[row][col - 2] = 'H';
			} else if (c == 'a' && col == 1) {
				a[row][col] = ' ';
				a[row][col - 1] = 'H';
			} else if (c == 'd') {
				a[row][col] = ' ';
				a[row][col + 2] = 'H';
			}

		}

		else if (result == 'E') {

			if (GameLogic.isLevelTwo) {
				if (c == 'w') {
					a[row][col] = ' ';
					a[row - 1][col] = 'H';
					
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
