package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;
import dkeep.logic.*;

public class TestsJUnit {
	
	public TestsJUnit(){};
	
	@Test
	public void moveIntoFreeCell() {
	
		GameLogic game = new GameLogic();
		game.currentMap.drawMap(game);
		char[][] map = game.currentMap.getMap();
		assertEquals(map[1][1], map[game.hero.getX()][game.hero.getY()]);
		//System.out.println(map[1][2]);
		//assertEquals('H', map[1][2]);
		game.createGuard(3,1);		
	
		
	}
	
}
