package dkeep.logic;
import dkeep.cli.*;

import java.util.Random;

public class Enemy {
	private static final char OGRE = '0';
	private static final char ROOKIE_GUARD = 'G';
	private static final char DRUNKEN_GUARD = 'D';

	/*public static char[][] moveEnemy(char[][] map) { // Retorna o mapa modificado após carater.
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == OGRE) {	
					map = moveOgre(map);
				}
				else if (map[i][j] == ROOKIE_GUARD || map[i][j] == DRUNKEN_GUARD) {
					map = moveGuard(map);
				}
			}
		}

		return map;
	}*/

	public static char[][] armOgre(char[][] map) {
		boolean isValid = false;

		String s = "wasd";
		while (!isValid) {
			Random rand = new Random();
			int index = rand.nextInt(s.length());
			char c = s.charAt(index);
			char result;

			int row = 0, col = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == '0') {
						row = i;
						col = j;
					}
				}
			}

			if (c == 'w') {
				result = GameLogic.next(map[row - 1][col]);
				//	System.out.println(result);
			} else if (c == 'a') {
				result = GameLogic.next(map[row][col - 1]);
				//	System.out.println(result);
			} else if (c == 's') {
				result = GameLogic.next(map[row + 1][col]);
				//	System.out.println(result);
			} else if (c == 'd') {
				result = GameLogic.next(map[row][col + 1]);
				//	System.out.println(result);
			}
			else 
				result = 'N';




			if (result == 'H') {
				
				if (c == 'w' && map[row - 1][col] != 'X' && map[row - 1][col] != 'S' && map[row - 1][col] != 'I') {
					map[row - 1][col] = 'C';
				}

				else if (c == 'a' && map[row][col - 1] != 'X' && map[row][col - 1] != 'S' && map[row][col - 1] != 'I') {
					map[row][col - 1] = 'C';
				}

				else if (c == 's' && map[row + 1][col] != 'X' && map[row + 1][col] != 'S' && map[row + 1][col] != 'I') {
					map[row+1][col] = 'C';

				} else if (c == 'd' && map[row][col + 1] != 'X' && map[row][col + 1] != 'S' && map[row][col + 1] != 'I') {

					map[row][col + 1] = 'C';
				}
				isValid = true;
			}
		}
		return map;
	}

	public static char[][] changeClub(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'C') {
					map[i][j] = ' ';
				}
			}
		}

		return map;
	}

	public static char[][] moveGuard(char[][] map, char typeOfGuard) { // Verifica se é rookie ou drunken

		if(typeOfGuard == 'G'){
			//Guard.followPath(map, 'G');
		}
		
		drawEnemy(map, typeOfGuard);
		/*if (typeOfGuard == 'D') {
			boolean isValid = false;
			String s = "wasd";
			while (!isValid) {
				Random rand = new Random();
				int index = rand.nextInt(s.length());
				char c = s.charAt(index);
				char result;

				if (c == 'w') {
					result = GameLogic.next(map[row - 1][col]);
					System.out.println(result);
				} else if (c == 'a') {
					result = GameLogic.next(map[row][col - 1]);
					System.out.println(result);
				} else if (c == 's') {
					result = GameLogic.next(map[row + 1][col]);
					System.out.println(result);
				} else if (c == 'd') {
					result = GameLogic.next(map[row][col + 1]);
					System.out.println(result);
				} else {
					result = 'N';
				}
				if (result == 'X') {
					continue;
				}
				else if (result == 'H') {
					if (c == 'w' && map[row - 1][col] != 'X' && map[row - 1][col] != 'S' && map[row - 1][col] != 'I' && map[row - 1][col] != 'K') {
						map[row][col] = ' ';
						map[row - 1][col] = 'D';
						isValid = true;
					}

					else if (c == 'a' && map[row][col - 1] != 'X' && map[row][col - 1] != 'S' && map[row][col - 1] != 'I'&& map[row][col -1] != 'K') {
						map[row][col] = ' ';
						map[row][col - 1] = 'D';
						isValid = true;
					}

					else if (c == 's' && map[row + 1][col] != 'X' && map[row + 1][col] != 'S' && map[row + 1][col] != 'I' && map[row + 1][col] != 'K') {
						map[row][col] = ' ';
						map[row + 1][col] = 'D';	
						isValid = true;

					} else if (c == 'd' && map[row][col + 1] != 'X' && map[row][col + 1] != 'S' && map[row][col + 1] != 'I' && map[row][col+1] != 'K') {
						map[row][col] = ' ';
						map[row][col + 1] = 'D';
						isValid = true;
					}

				}


			}

		}*/

		return map;

	}

	public static char[][] moveOgre(char[][] map) {
		map = changeClub(map);
		String s = "wasd";
		boolean restoreKey = false;
		Random rand = new Random();
		int index = rand.nextInt(s.length());
		char c = s.charAt(index);
		char result;

		int row = 0, col = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == '0') {
					row = i;
					col = j;
				}
			}
		}


		if (c == 'w') {
			result = GameLogic.next(map[row - 1][col]);
			//	System.out.println(result);
		} else if (c == 'a') {
			result = GameLogic.next(map[row][col - 1]);
			//	System.out.println(result);
		} else if (c == 's') {
			result = GameLogic.next(map[row + 1][col]);
			//	System.out.println(result);
		} else if (c == 'd') {
			result = GameLogic.next(map[row][col + 1]);
			//	System.out.println(result);
		} else {
			result = 'N';
		}

		if (result == 'H' || result == 'D') {
			if (c == 'w' && map[row - 1][col] != 'X' && map[row - 1][col] != 'S' && map[row - 1][col] != 'I') {
				map[row][col] = ' ';
				map[row - 1][col] = '0';


			}

			else if (c == 'a' && map[row][col - 1] != 'X' && map[row][col - 1] != 'S' && map[row][col - 1] != 'I') {

				// ELIMINEI O RESTORE KEY, porque como isto acontece antes do
				// movimento do ogre, se o ogre se puser em cima, vai ser
				// colocado outra vez o 'k', mas o ogre sobrepõe-se logo a isso,
				// por isso, simplesmente impedi-o de se pôr na chave.

				map[row][col] = ' ';
				map[row][col - 1] = '0';
			}

			else if (c == 's' && map[row + 1][col] != 'X' && map[row + 1][col] != 'S' && map[row + 1][col] != 'I') {

				if (restoreKey) {
					map[1][7] = 'k';
					restoreKey = false;
				} else {
					map[row][col] = ' ';
				}
				map[row + 1][col] = '0';
			} else if (c == 'd' && map[row][col + 1] != 'X' && map[row][col + 1] != 'S' && map[row][col + 1] != 'I') {
				map[row][col] = ' ';
				map[row][col + 1] = '0';
			}

		}

		else if (result == 'D') {

		}


		map = armOgre(map);
		return map;
 
	}

	public static char[][] drawEnemy(char[][] map, char c) {
		if (!UserInterface.isLevelTwo)
			//map[Guard.getX()][Guard.getY()] = 'G';
			System.out.println("Olá Pedro :)");
		else 
			map[1][4] = c;
		return map;
	}
	
	/*public static char[][] followPath(char[][] map, char z){
		
		/*Guard[] coordinates = new Guard[24];
		
		coordinates[0] = new Guard(1, 8);
		coordinates[1] = new Guard(1, 7);
		coordinates[2] = new Guard(2, 7);
		coordinates[3] = new Guard(3, 7);
		coordinates[4] = new Guard(4, 7);
		coordinates[5] = new Guard(5, 7);
		coordinates[6] = new Guard(5, 6);
		coordinates[7] = new Guard(5, 5);
		coordinates[8] = new Guard(5, 4);
		coordinates[9] = new Guard(5, 3);
		coordinates[10] = new Guard(5, 2);
		coordinates[11] = new Guard(5, 1);
		coordinates[12] = new Guard(6, 1);
		coordinates[13] = new Guard(6, 2);
		coordinates[14] = new Guard(6, 3);
		coordinates[15] = new Guard(6, 4);
		coordinates[16] = new Guard(6, 5);
		coordinates[17] = new Guard(6, 6);
		coordinates[18] = new Guard(6, 7);
		coordinates[19] = new Guard(6, 8);
		coordinates[20] = new Guard(5, 8);
		coordinates[21] = new Guard(4, 8);
		coordinates[22] = new Guard(3, 8);
		coordinates[23] = new Guard(2, 8);
		
		
		for(int i = 0; i < coordinates.length; i++){
			if(Guard.getX() == coordinates[i].getX() && Guard.getY() == coordinates[i].getY()){
				
			}
		}
		
		
		
		
		int row = 0, col = 0;
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == z) {
					row = i;
					col = j;
				}
			}
		}
		System.out.println(z + " ROW " + row + " COL " + col);
		if (z == 'G') {
			int rowg = row, colg = col;


			if (rowg == 1 && colg == 8) {
				map[rowg][colg] = ' ';
				map[rowg][colg - 1] = 'G';
			} else if (rowg >= 1 && colg == 7 && rowg < 5) {
				map[rowg][colg] = ' ';
				map[rowg + 1][colg] = 'G';
			} else if (rowg == 5 && colg <= 7 && colg > 1) {
				map[rowg][colg] = ' ';
				map[rowg][colg - 1] = 'G';
			} else if (rowg == 5 && colg == 1) {
				map[rowg][colg] = ' ';
				map[rowg + 1][colg] = 'G';
			} else if (rowg == 6 && colg >= 1 && colg < 8) {
				map[rowg][colg] = ' ';
				map[rowg][colg + 1] = 'G';
			} else if (rowg > 1 && rowg <= 6 && colg == 8) {
				map[rowg][colg] = ' ';
				map[rowg - 1][colg] = 'G';
			}
		}
		
		return map;
	}*/
	
}
