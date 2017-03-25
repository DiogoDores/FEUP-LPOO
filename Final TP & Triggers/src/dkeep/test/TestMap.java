package dkeep.test;

import java.util.Arrays;

import dkeep.logic.GameLogic;
import dkeep.logic.GameMap;
import dkeep.logic.Hero;
 
public class TestMap extends GameMap{
	private static char [][] tmp = {{'X','X','X','X','X'},
			{'I',' ',' ','k','X'},
			{'X',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','X','X','X','X'}};

	private String mapName = "TestMap";
	private char [][] TestMap;
	
	public TestMap(){
		TestMap = new char[tmp.length][];

		for (int line = 0; line < tmp.length; line++) {
			TestMap[line] = Arrays.copyOf(tmp[line], tmp[line].length);
		}
		
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
	public boolean checkWin(GameLogic game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPositions(GameLogic game) {
		// TODO Auto-generated method stub
		
	}
	
	
}

