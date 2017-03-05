package dkeep.test;

import dkeep.logic.*;
import dkeep.cli.*;


public class TestMap implements GameMap{

	private String mapName = "TestMap";

	static char [][] TestMap = {{'X','X','X','X','X'},
			{'I',' ',' ','k','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};
	@Override
	public char possibleMove(int x, int y) {
		if (TestMap[x][y]  == 'X'){
			return 'X';
		} else if (TestMap[x][y] == 'k') {
			return 'E';
		} else if (TestMap[x][y] == ' '){
			return 'H';
		} else if (TestMap[x][y] == 'I'){
			return 'I';
		} else if (TestMap[x][y] == 'S') {
			return 'S';
		} else if (TestMap[x][y] == 'H') // AC
			return 'D';
		return TestMap[x][y];

	}

	@Override
	public void activateLever(Hero hero) {
		hero.setSymbol('K');
	}

	@Override
	public void drawMap(GameLogic game) {
		char[][] mapToDraw = TestMap;
		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){
				if (i == 0 && j == 0)
					System.out.print('X');
				else if(game.guard.getX() == i && game.guard.getY() == j){
					System.out.print(game.guard.getSymbol());
					continue;
				}else if(game.hero.getX() == i && game.hero.getY() == j){
					System.out.print(game.hero.getSymbol());
					continue;
				} 
				else 
					System.out.print(mapToDraw[i][j]);
				
			}
			System.out.print("\n");
		}
	}		


	@Override
	public String getName() {
		return mapName;
	}

	@Override
	public char[][] getMap() {
		return TestMap;
	}




}

