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
	public void drawMap(GameLogic game) {
		char[][] mapToDraw = TestMap;
		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){
				if (i == 0 && j == 0)
					System.out.print('X');
				else if(game.guard.getX() == i && game.guard.getY() == j){
					System.out.print(game.guard.getSymbol());
					continue;
				}
				else if(game.ogre.getX() == i && game.ogre.getY() == j){
					System.out.print(game.ogre.getSymbol());
					continue;
				}
				else if(game.hero.getX() == i && game.hero.getY() == j){
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

	@Override
	public char possibleMove(int x, int y, GameLogic game) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
