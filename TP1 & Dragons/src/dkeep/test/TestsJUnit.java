package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;
import dkeep.logic.*;

public class TestsJUnit {
	
	public TestsJUnit(){};
	
	@Test
	public void moveIntoFreeCell() {
	
		GameLogic game = new GameLogic();
		char[][] map = game.currentMap.getMap();
		for (int i = 0 ; i < map.length ; i++) {
			for (int j = 0 ; j < map[i].length; j++) {
				System.out.println(map[i][j]);
			}
		}
		//System.out.println(map[1][0]);

		//assertEquals(map[1][1], map[game.hero.getX()][game.hero.getY()]);
		//System.out.println(map[1][2]);
		//assertEquals('H', map[1][2]);
		//game.createGuard(3,1);		
	
		
	}
	
}
