import java.util.Scanner;

public class dungeonEscape {
	public static void main(String[] args ){
		boolean won = false, lost = false;
		System.out.println("Welcome to Escape The Dungeon!\n To make your hero move, here's what you need to type.\nU - up;\nR - right;\nL - left;\nD - down;\n\nHave fun!\n");
		Scanner scan = new Scanner(System.in);
		char[][] a = {{'X','X','X','X','X','X','X','X','X','X'},{'X','H',' ',' ','I',' ','X',' ','G','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'X','X','X',' ','X','X','X','X',' ','X'},{'X',' ','I',' ','I',' ','X','K',' ','X'},{'X','X','X','X','X','X','X','X','X','X'}};
		boolean finish = game(a);
		if (finish == true) {
			System.out.println("Ganhei o jogo, e acabei de perder o jogo");
		}
	}

	public static boolean game(char[][] a) {
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);
		
		showMap(a);
		do {
			a = move(a, scan.next().charAt(0));
			
			if (a[5][0] == 'H' || a[6][0] == 'H')
				won = true;
			
		} while (!won && !lost);
		return won;
	}
	

	
	public static char[][] move(char[][] a, char c) {
		boolean won = false;
		int row = 0, col = 0;
		for (int i = 0 ; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'H') {
					row = i;
					col = j;
					j = a[i].length-1;
					i = a.length-1;
				}
			}
		}
		char result;
		int x;
		if (c == 'u') 
			result = next(a[row-1][col]);


		else if (c == 'l')
			result = next(a[row][col-1]);

		else if (c == 'd') 
			result = next(a[row+1][col]);
		else if (c == 'r')
			result = next(a[row][col+1]);
		else 
			result = 'N';

		if (result == 'H') {
			if (c == 'u') {
				a[row][col] = ' ';
				a[row-1][col] = 'H';
			}

			else if (c == 'l') {
				a[row][col] = ' ';
				a[row][col-1] = 'H';
			}
			
			
			else if (c == 'd') {
				a[row][col] = ' ';
				a[row+1][col] = 'H';
			}
			else if (c == 'r') {
				a[row][col] = ' ';
				a[row][col+1] = 'H';
			}

		}
		
		if (result == 'S') {
			

			if (c == 'l' && col > 1) {
				a[row][col] = ' ';
				a[row][col-2] = 'H';
			}
			else if (c == 'l' && col == 1) {
				a[row][col] = ' ';
				a[row][col-1] = 'H';
			}
			else if (c == 'r') {
				a[row][col] = ' ';
				a[row][col+2] = 'H';
			}
			
		}

		if (result == 'E') {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					if (a[i][j] == 'I')
						a[i][j] = 'S'; 
				}
			}
		}
		showMap(a);
		return a;

	}


	public static char next(char c) {
		if (c == 'X')
			return 'X';
		else if (c == 'K')
			return 'E';
		else if (c == ' ') 
			return 'H';
		else if (c == 'I')
			return 'X';
		else if (c == 'S') {
			return 'S';
		}
		return c;
	}

	public static char[][] changePosition(char[][] c) {
		return c;
	}

	public static void showMap(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		System.out.println("\n");
	}
}

