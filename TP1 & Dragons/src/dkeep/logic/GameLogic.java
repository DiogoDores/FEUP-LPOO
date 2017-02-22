package dkeep.logic;
import dkeep.cli.*;

import java.util.Random;
import java.util.Scanner;
import dkeep.logic.*;


public class GameLogic {


	static char[][] a = Maps.getLevelOneMap();
	static char[][] ogreMap = Maps.getLevelTwoMap();
	static boolean isLevelTwo = UserInterface.isLevelTwo;

	public static boolean levelOne(char[][] a) {

		Hero hero = new Hero(1,1);     
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);

		String s = "DG";
		Random rand = new Random();
		int index = rand.nextInt(s.length());
		char ch = s.charAt(index);
		
		Enemy.placeEnemy(a, ch);
	
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
		if (c == 'w') {
			result = next(a[row - 1][col]);
			System.out.println(a[row-1][col]);
		}
		else if (c == 'a'){
			result = next(a[row][col - 1]);
			System.out.println(a[row][col - 1]);
		}
		else if (c == 's') {
			result = next(a[row + 1][col]);
			System.out.println(a[row+1][col]);
		}
		else if (c == 'd') {
			result = next(a[row][col + 1]);
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

			if (isLevelTwo) {
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

		if (isLevelTwo) {
			ogreMap = Enemy.moveOgre(ogreMap);
			UserInterface.showMap(ogreMap);
		}
		else {
			a = Enemy.moveGuard(a);
			UserInterface.showMap(a);
		}
		

		return a;

	}


	public static char next(char c) {
		if (c == 'X')
			return 'X';
		else if (c == 'k') {
			return 'E';
		}
		else if (c == ' ')
			return 'H';
		else if (c == 'I')
			return 'X';
		else if (c == 'S') {
			return 'S';
		}
		else if (c == '0' || c == 'D' || c == 'G') // ESPECIFICO PARA INIMIGOS
			return 'M';
		return c;
	}

}
