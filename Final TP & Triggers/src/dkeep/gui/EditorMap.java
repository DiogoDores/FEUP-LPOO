package dkeep.gui;

import dkeep.logic.*;

public class EditorMap extends GameMap {

	int levelWidth, levelHeight;
	static boolean visited[][];
	static char level[][];
	boolean foundSolution;
	/**
	 * Creates an Editor Map, with self-explanatory parameters. These can be
	 * altered by the player. 
	 */

	public EditorMap(int width, int height) {

		this.levelWidth = width;
		this.levelHeight = height;

		level = new char[width][height];
		visited = new boolean[width][height];

		for (int i = 0 ; i < levelHeight ; i++) {
			for (int j = 0; j < levelWidth ; j++) {
				if ( i == 0 || i == levelHeight - 1 ||j == 0 || j == levelWidth - 1) {
					level[i][j] = 'X';
				}	
				else 
					level[i][j] = ' ';
			}
		}
	}

	public void place(int x , int y, char symbol) {
		if (x >= 0 && x <= levelWidth -1 && y >= 0 && y <= levelHeight-1)
			level[y][x] = symbol;
	}

	public void checkOgre(GameLogic game, int x, int y) {
		for (int i = 0 ; i < game.ogres.size(); i++) {
			if (game.ogres.get(i).getX() == y && game.ogres.get(i).getY() == x) {
				game.ogres.remove(i);
				
			}
		}
	}
	
	public boolean checkKey(int x, int y, GameLogic game) {
		return (game.currentMap.getMap()[x][y] == 'k');
	}

	


	/**
	 * Checks all possible moves.
	 */

	@Override
	public char possibleMove(int x, int y, GameLogic game) {
		for (int i = 0; i < game.ogres.size(); i++) {
			if (game.ogres.get(i).getX() == x && game.ogres.get(i).getY() == y)
				return 'O';
		}
		if (game.currentMap.getMap()[x][y]  == 'X'){
			return 'X';
		} else if (game.currentMap.getMap()[x][y] == 'k') {
			return 'E';
		} else if (game.currentMap.getMap()[x][y] == ' '){
			return 'H';
		} else if (game.currentMap.getMap()[x][y] == 'I'){
			return 'I';
		} else if (game.currentMap.getMap()[x][y] == 'S') {
			return 'S';
		} else if (game.currentMap.getMap()[x][y] == 'H') // AC
			return 'D';
		return 'N';
	}

	@Override
	public void activateLever(Hero hero) {

	}

	/**
	 * Draws Map only on console.
	 */


	@Override
	public String getName() {
		return "Editor";
	}

	@Override
	public char[][] getMap() {
		return level;
	}

	/**
	 * Opens all doors on the map.
	 */

	public void openDoor(GameLogic game) {
		for (int i = 0 ; i < game.currentMap.getMap().length; i++)
			for (int j = 0; j < game.currentMap.getMap()[i].length; j++) {
				if (game.currentMap.getMap()[i][j] == 'I')
					game.currentMap.getMap()[i][j] = 'S';
			}
	}

	/**
	 * With the assistance of FindWay, checks whether map
	 * is valid or not (can be finished by the player).
	 */

	public boolean checkMap(GameLogic game, int width, int height) {
		boolean hasKey = false;
		boolean hasDoor = false;
		boolean hasOgre = false;

		for (int i = 0 ; i < game.currentMap.getMap().length; i++)
			for (int j = 0; j < game.currentMap.getMap()[i].length; j++) {
				if (game.currentMap.getMap()[i][j] == 'I'){
					if (i == 0 || j == 0 || i == width -1 || j == height -1){
						hasDoor = true;
					}
				}
				if (game.currentMap.getMap()[i][j] == 'k'){
					hasKey = true;
				}
			}

		if (game.ogres.size() >= 1)
			hasOgre = true;

		return hasOgre && hasDoor && hasKey;	
	}

	@Override
	public void resetPositions(GameLogic game) {


	}

	public void resetMap(GameLogic gameLogic) {
		gameLogic.ogres.clear();

		for (int i = 0 ; i < levelWidth ; i++) {
			for (int j = 0; j < levelHeight ; j++) {
				if ( i == 0 || i == levelWidth -1 ||j == 0 || j == levelHeight - 1) {
					level[i][j] = 'X';
				}	
				else 
					level[i][j] = ' ';
			}
		}
	}
	
	@Override
	public boolean checkWin(GameLogic game) {
		int x = 0, y = 0;
		for (int i = 0; i < game.currentMap.getMap().length;i++) {
			for (int j = 0 ; j< game.currentMap.getMap()[i].length; j++) {
				if (game.currentMap.getMap()[i][j] == 'S') {
					x = i; 
					y = j;
					if (game.hero.getX() == x && game.hero.getY() == y)
						return true;
				}
					
			}
		}
		return false; 
	}
}
