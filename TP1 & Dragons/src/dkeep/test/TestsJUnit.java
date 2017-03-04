package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;
import dkeep.logic.*;
import dkeep.cli.*;



public class TestsJUnit {
	
	
	
	@Test
	public static void moveIntoFreeCell() {
		
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		testMap.drawMap(game);
		char[][] map = testMap.getMap();
		//System.out.println(map[1][2]);
		//assertEquals('H', map[1][2]);
		game.createGuard(3,1);		
	
		
	}
	
}
