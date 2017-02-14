import java.util.Scanner;

public class dungeonEscape {
	public static void main(String[] args ){
		boolean won = false, lost = false;
		System.out.println("Bem-vindo ao Dungeon Escape!\n Para fazer o teu herói mexer, isto é o que precisas de saber.\nU - cima;\nR - direita;\nL - esquerda;\nD - baixo;\n\nBoa sorte!\n");
		Scanner scan = new Scanner(System.in);
		char[][] a = {{'X','X','X','X','X','X','X','X','X','X'},{'X','H',' ',' ','I',' ','X',' ','G','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'X',' ','I',' ','I',' ','X',' ',' ','X'},{'X','X','X',' ','X','X','X',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},{'X','X','X',' ','X','X','X','X',' ','X'},{'X',' ','I',' ','I',' ','X','K',' ','X'},{'X','X','X','X','X','X','X','X','X','X'}};
		boolean finish = game(a);
		if (finish == true) {
			System.out.println("Ganhei o jogo, e acabei de perder o jogo.");
		}
		if (!finish) 
			System.out.println("Acabei efetivamente de perder o jogo.");
	}

	public static boolean game(char[][] a) {
		boolean won = false, lost = false;;
		Scanner scan = new Scanner(System.in);
		
		showMap(a);
		do {
			a = move(a, scan.next().charAt(0));
			
			if (a[5][0] == 'H' || a[6][0] == 'H')
				won = true;
			if (checkPresence(a)) {
				lost = true;
				won = false;
			}
			
		} while (!won && !lost);
		return won;
	}
	
	public static boolean checkPresence(char[][] a) {
		int row = 0, col = 0, rowg = 0,colg = 0;
		for (int i = 0 ; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'H') {
					row = i; col = j;
				}
				if (a[i][j] == 'G') {
					rowg = i;
					colg = j;
				}
			}
		}
		
		if (colg == col && (rowg == row-1 || rowg == rowg+1 ))
			return true;
		if (rowg == row && (colg == col-1 || colg == col+1 ))
			return true;
		return false;
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
		a = moveGuard(a);
		showMap(a);
		return a;

	}
	
	public static char [][] moveGuard(char[][] a) {
		int rowg = 0, colg = 0;
		for (int i = 0 ; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'G') {
					rowg = i;
					colg = j;
				}
			}
		}
		
		if (rowg == 1 && colg == 8) {
			a[rowg][colg] = ' ';
			a[rowg][colg-1] = 'G';
		}
		else if (rowg >= 1 && colg == 7 && rowg < 5) {
			a[rowg][colg] = ' ';
			a[rowg+1][colg] = 'G';
		}
		else if (rowg == 5 && colg <= 7 && colg >1) {
			a[rowg][colg] = ' ';
			a[rowg][colg-1] = 'G';
		}
		else if (rowg == 5 && colg == 1) {
			a[rowg][colg] = ' ';
			a[rowg+1][colg] = 'G';
		}
		else if (rowg == 6 && colg >= 1 && colg < 8) {
			a[rowg][colg] = ' ';
			a[rowg][colg+1] = 'G';
		}
		else if (rowg > 1 && rowg <= 6 && colg == 8) {
			a[rowg][colg] = ' ';
			a[rowg-1][colg] = 'G';
		}
			
		
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

