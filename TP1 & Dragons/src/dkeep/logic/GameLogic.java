package dkeep.logic;
import dkeep.cli.*;

import java.util.Random;
import java.util.Scanner;
import dkeep.logic.*;


public class GameLogic {


	static char[][] a = Maps.getLevelOneMap();
	static char[][] ogreMap = Maps.getLevelTwoMap();
	static boolean isLevelTwo = UserInterface.isLevelTwo;

	public static boolean levelOne() {

		Hero hero = new Hero(1,1);     
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);

		String s = "DG";
		Random rand = new Random();
		int index = rand.nextInt(s.length());
		char ch = s.charAt(index);

		a = Enemy.placeEnemy(a, ch);

		UserInterface.showMap(a);
		do {

			a = Hero.move(a, scan.next().charAt(0));
			if (isLevelTwo) {
				a = Enemy.moveOgre(a);
				UserInterface.showMap(a);
			}
			else {
				a = Enemy.moveGuard(a,ch);
				UserInterface.showMap(a);
			}

			if (a[5][0] == 'H' || a[6][0] == 'H')
				won = true;
			if (checkPresence(a)) {
				lost = true;
				won = false;
			}

		} while (!won && !lost);
		
		if (won)
			return true;	
		else 
			return false;
	}

	public static boolean levelTwo() {
		boolean won = false, lost = false;
		Scanner scan = new Scanner(System.in);
		ogreMap = Enemy.placeEnemy(ogreMap, '0');
		
		do {
			UserInterface.showMap(ogreMap);
			ogreMap = Hero.move(ogreMap, scan.next().charAt(0));
			ogreMap = Enemy.moveOgre(ogreMap);
			if (ogreMap[1][0] == 'H') // ESTAVA AQUI O PROBLEMA. Nunca havia
				// nenhuma circunstância em que o K
				// estava naquela posição.
				won = true;
			if (checkPresence(ogreMap)) {
				lost = true;
				won = false;
			}

		} while (!won && !lost);
		if (won)
			return true;
		else return false;
	}

	public static boolean checkPresence(char map[][]) {
		if (isLevelTwo)
			map = ogreMap;
		else 
			map = a;
		int row = -1, col = -1, rowg = 0, colg = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'H' || map[i][j] == 'K') {
					row = i;
					col = j;
				}
				if (map[i][j] == 'G' || map[i][j] == '0' || map[i][j] == 'D') {
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
		if (colg == col && (rowg == row - 1 || rowg == row + 1)) {
			return true;

		}
		else if (rowg == row && (colg == col - 1 || colg == col + 1)){
			return true;

		}
		return false;
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
		return c;
	}

}
