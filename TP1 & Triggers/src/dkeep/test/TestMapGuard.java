package dkeep.test;

import java.util.Arrays;

import dkeep.logic.GameLogic;
import dkeep.logic.GameMap;
import dkeep.logic.Hero;

public class TestMapGuard implements GameMap{
	private static char [][] tmp = {{'X','X','X','X','X'},
			{'I',' ',' ','k','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};

	private String mapName = "TestMap";
	private char [][] TestMap;
	
	public TestMapGuard(){
		TestMap = new char[tmp.length][];

		for (int line = 0; line < tmp.length; line++) {
			TestMap[line] = Arrays.copyOf(tmp[line], tmp[line].length);
		}
		
	}
	
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
	public String drawMap(GameLogic game) {
		char[][] mapToDraw = TestMap;
		String map = "";
		
		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){
				if (i == 0 && j == 0)
					map += "X";
				else if(game.guard.getX() == i && game.guard.getY() == j){
					map += game.guard.getSymbol();
					continue;
				}
				else if(game.ogre.getX() == i && game.ogre.getY() == j){
					map += game.ogre.getSymbol();
					continue;
				}
				else if(game.hero.getX() == i && game.hero.getY() == j){
					map += game.hero.getSymbol();
					continue;
				} 
				else 
					map += mapToDraw[i][j];

			}
			map += "\n";
		}
		return map;
	}		


	@Override
	public String getName() {
		return mapName;
	}

	@Override
	public char[][] getMap() {
		return TestMap;
	}

	@Override
	public char possibleMove(int x, int y, GameLogic game) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
