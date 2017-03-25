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
		levelWidth = width; levelHeight = height;
		visited = new boolean[width][height];
		for (int i = 0 ; i < height ; i++) {
			for (int j = 0; j < width ; j++) {
				if ( i == 0 || i == height -1 ||j == 0 || j == width - 1) {
					level[i][j] = 'X';
				}	
				else 
					level[i][j] = ' ';
				visited[i][j] = false;
			}
			foundSolution = false;
		}
	}

	public void place(int x , int y, char symbol) {
		if (x >= 0 && x <= levelWidth -1 && y >= 0 && y <= levelHeight-1)
			level[x][y] = symbol;
	}



	/**
	 * Checks all possible moves.
	 */
	 
	@Override
	public char possibleMove(int x, int y, GameLogic game) {
		return 'H';
	/*	for (int i = 0; i < game.ogres.size(); i++) {
			if (game.ogres.get(i).getX() == x && game.ogres.get(i).getY() == y )
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
		return 'H';
	*/
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
			for (int j = 0; j < game.currentMap.getMap().length; j++) {
				if (game.currentMap.getMap()[i][j] == 'I')
					game.currentMap.getMap()[i][j] = 'S';
			}


		for (int i = 0 ; i < levelHeight ; i++) 
			for (int j = 0; j < levelWidth ; j++) 
				visited[i][j] = false;

	}

	/**
	 * User clicked the button to save the level, checks whether is is possible. 
	 */
	
	public boolean saveLevel(GameLogic game) {
		return checkMap(game);
	}

	
	/**
	 * With the assistance of FindWay, checks whether map
	 * is valid or not (can be finished by the player).
	 */
	
	public boolean checkMap(GameLogic game) {
		boolean check = false;

		boolean hasDoor = false;

		for (int i = 0 ; i < game.currentMap.getMap().length; i++)
			for (int j = 0; j < game.currentMap.getMap().length; j++) {
				if (game.currentMap.getMap()[i][j] == 'I' || game.currentMap.getMap()[i][j] == 'S')
					if (i == 0 || j == 0)
						hasDoor = true;
			}
		if (!hasDoor)
			return false;


		int x = game.hero.getX(), y = game.hero.getY();

		return check;
	}

	/*public boolean findWay(GameLogic game, int x , int y) {

		boolean doorIsOpen = false;

		for (int i = 0 ; i < game.currentMap.getMap().length; i++)
			for (int j = 0; j < game.currentMap.getMap().length; j++) {
				if (game.currentMap.getMap()[i][j] == 'S')
					doorIsOpen = true;
			}
		// PROCURA ALAVANCA
		if (!doorIsOpen) {

			//ENCONTRA ALAVANCA
			//VAI PARA A ESQUERDA
			if (!visited[x-1][y] && possibleMove(x-1, y, game) == 'S') {
				return true;
			}

			//VAI PARA A DIREITA
			else if (!visited[x+1][y] && possibleMove(x-1, y, game) == 'S') {
				return true;
			}

			//VAI PARA CIMA
			else if (!visited[x][y-1] && possibleMove(x, y-1, game) == 'S') {
				return true;
			}

			//VAI PARA BAIXO
			else if (!visited[x][y+1] && possibleMove(x, y+1, game) == 'S') {
				return true;
			}
		}
		// PROCURA SAIDA
		if (doorIsOpen) {
			//ENCONTRA SAIDA
			//VAI PARA A ESQUERDA
			if (!visited[x-1][y] && possibleMove(x-1, y, game) == 'E') {
				visited[x-1][y] = true;
				findWay(game, x-1, y);
			}

			//VAI PARA A DIREITA
			else if (!visited[x+1][y] && possibleMove(x-1, y, game) == 'E') {
				visited[x+1][y] = true;
				findWay(game, x+1, y);
			}

			//VAI PARA CIMA
			else if (!visited[x][y-1] && possibleMove(x, y-1, game) == 'E') {
				visited[x][y-1] = true;
				findWay(game, x, y-1);
			}

			//VAI PARA BAIXO
			else if (!visited[x][y+1] && possibleMove(x, y+1, game) == 'E') {
				visited[x][y+1] = true;
				findWay(game, x, y+1);
			}

		}

		// NÃO ENCONTRA ALAVANCA NEM SAIDA
		
		//VAI PARA A ESQUERDA
		if (!visited[x-1][y] && possibleMove(x-1, y, game) == 'H') {
			visited[x-1][y] = true;
			findWay(game, x-1, y);
		}

		//VAI PARA A DIREITA
		else if (!visited[x+1][y] && possibleMove(x-1, y, game) == 'H') {
			visited[x+1][y] = true;
			findWay(game, x+1, y);
		}

		//VAI PARA CIMA
		else if (!visited[x][y-1] && possibleMove(x, y-1, game) == 'H') {
			visited[x][y-1] = true;
			findWay(game, x, y-1);
		}

		//VAI PARA BAIXO
		else if (!visited[x][y+1] && possibleMove(x, y+1, game) == 'H') {
			visited[x][y+1] = true;
			findWay(game, x, y+1);
		}


		return false;

	}*/

	@Override
	public boolean checkWin(GameLogic game) {
		return false;
	}

	@Override
	public void resetPositions(GameLogic game) {
		
		
	}

}
