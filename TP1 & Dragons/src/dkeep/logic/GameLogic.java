package dkeep.logic;
import dkeep.cli.*;

import java.util.Random;
import java.util.Scanner;

public class GameLogic {
	
	Maps map = new Maps();
	
	char[][] a = map.getLevelOneMap();
	char[][] ogreMap = map.getLevelTwoMap();
	static boolean isLevelTwo = UserInterface.isLevelTwo;

	public static boolean levelOne(char[][] a) {
		
		Hero hero = new Hero(1,1);     
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);

		UserInterface.showMap(a);
		do {
			
			a = move(a, scan.next().charAt(0));

			if (a[5][0] == 'H' || a[6][0] == 'H')
				won = true;
			if (checkPresence(a)) {
				lost = true;
				won = false;
			}

		} while (!won && !lost);
		if (won) {
			UserInterface.isLevelTwo = true;
		}
		return won;
	}

	public static boolean levelTwo(char[][] ogreMap) {
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);

		UserInterface.showMap(ogreMap);
		do {
			ogreMap = move(ogreMap, scan.next().charAt(0));

			if (ogreMap[1][0] == 'H') // ESTAVA AQUI O PROBLEMA. Nunca havia
										// nenhuma circunstância em que o K
										// estava naquela posição.
				won = true;
			if (checkPresence(ogreMap)) {
				lost = true;
				won = false;
			}

		} while (!won && !lost);
		return won;
	}

	public static boolean checkPresence(char[][] a) {
		int row = -1, col = -1, rowg = 0, colg = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'H' || a[i][j] == 'K') {
					row = i;
					col = j;
				}
				if (a[i][j] == 'G' || a[i][j] == '0') {
					rowg = i;
					colg = j;
				}
			}
		}
		if (row == -1 && col == -1) { // Adicionei aqui esta verificação para
										// quando o H é comido pelo 0 e nem
										// sequer está no array.
			return true;

		}
		if (colg == col && (rowg == row - 1 || rowg == row + 1))
			return true;
		if (rowg == row && (colg == col - 1 || colg == col + 1))
			return true;
		return false;
	}

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
		if (c == 'w')
			result = next(a[row - 1][col]);
		else if (c == 'a')
			result = next(a[row][col - 1]);
		else if (c == 's')
			result = next(a[row + 1][col]);
		else if (c == 'd')
			result = next(a[row][col + 1]);
		else
			result = 'N';

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

		if (result == 'S') {
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

		if (result == 'E') {
			if (isLevelTwo) {
				if (c == 'w') {
					a[row][col] = ' ';
					a[row - 1][col] = 'H';
					System.out.println("\n\nYou got the Key! Make your escape!\n\n"); // Adicionei
																						// isto
																						// e
																						// troquei
																						// o
																						// K
																						// porque
																						// nao
																						// atualizava
																						// corretamente.
				} else if (c == 'd') {
					a[row][col] = ' ';
					a[row][col + 1] = 'K';
				}

			}

			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					if (a[i][j] == 'I')
						a[i][j] = 'S';
				}
			}
		}

		if (isLevelTwo) {
			a = moveOgre(a);
		} else {
			a = moveGuard(a);
		}
		UserInterface.showMap(a);

		return a;

	}

	public static char[][] moveGuard(char[][] a) {
		int rowg = 0, colg = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'G') {
					rowg = i;
					colg = j;
				}
			}
		}

		if (rowg == 1 && colg == 8) {
			a[rowg][colg] = ' ';
			a[rowg][colg - 1] = 'G';
		} else if (rowg >= 1 && colg == 7 && rowg < 5) {
			a[rowg][colg] = ' ';
			a[rowg + 1][colg] = 'G';
		} else if (rowg == 5 && colg <= 7 && colg > 1) {
			a[rowg][colg] = ' ';
			a[rowg][colg - 1] = 'G';
		} else if (rowg == 5 && colg == 1) {
			a[rowg][colg] = ' ';
			a[rowg + 1][colg] = 'G';
		} else if (rowg == 6 && colg >= 1 && colg < 8) {
			a[rowg][colg] = ' ';
			a[rowg][colg + 1] = 'G';
		} else if (rowg > 1 && rowg <= 6 && colg == 8) {
			a[rowg][colg] = ' ';
			a[rowg - 1][colg] = 'G';
		}

		return a;

	}

	public static char next(char c) {
		if (c == 'X')
			return 'X';
		else if (c == 'k')
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

	public static char[][] moveOgre(char[][] a) {

		String s = "wasd";
		boolean restoreKey = false;
		Random rand = new Random();
		int index = rand.nextInt(s.length());
		char c = s.charAt(index);
		char result;

		int row = 0, col = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == '0') {
					row = i;
					col = j;
				}
			}
		}

		System.out.println("Ogre: " + c);

		if (c == 'w') {
			result = next(a[row - 1][col]);
			System.out.println(result);
		} else if (c == 'a') {
			result = next(a[row][col - 1]);
			System.out.println(result);
		} else if (c == 's') {
			result = next(a[row + 1][col]);
			System.out.println(result);
		} else if (c == 'd') {
			result = next(a[row][col + 1]);
			System.out.println(result);
		} else {
			result = 'N';
		}

		if (result == 'H') {
			if (c == 'w' && a[row - 1][col] != 'X' && a[row - 1][col] != 'S' && a[row - 1][col] != 'I') {
				a[row][col] = ' ';
				a[row - 1][col] = '0';
			}

			else if (c == 'a' && a[row][col - 1] != 'X' && a[row][col - 1] != 'S' && a[row][col - 1] != 'I') {

				// ELIMINEI O RESTORE KEY, porque como isto acontece antes do
				// movimento do ogre, se o ogre se puser em cima, vai ser
				// colocado outra vez o 'k', mas o ogre sobrepõe-se logo a isso,
				// por isso, simplesmente impedi-o de se pôr na chave.

				a[row][col] = ' ';
				a[row][col - 1] = '0';
			}

			else if (c == 's' && a[row + 1][col] != 'X' && a[row + 1][col] != 'S' && a[row + 1][col] != 'I') {

				if (restoreKey) {
					a[1][7] = 'k';
					restoreKey = false;
				} else {
					a[row][col] = ' ';
				}
				a[row + 1][col] = '0';
			} else if (c == 'd' && a[row][col + 1] != 'X' && a[row][col + 1] != 'S' && a[row][col + 1] != 'I') {
				a[row][col] = ' ';
				a[row][col + 1] = '0';
			}

		}

		else if (result == 'E') {
			a[row][col] = ' ';
			a[row + 1][col] = '0';
		}

		return a;

	}
}
