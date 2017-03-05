package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.UserInterface;
import dkeep.logic.*;

public class TestsJUnit {
	
	public TestsJUnit(){};
	
	@Test
	public void moveIntoFreeCell() {
	
		GameLogic game = UserInterface.game;
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		
		char[][] map = game.currentMap.getMap();

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move(testMap, 's');
		assertEquals(2, game.hero.getX());
		assertEquals(2, game.hero.getY());
		
		game.createGuard(3,1);		
	
		
	}
	
}
