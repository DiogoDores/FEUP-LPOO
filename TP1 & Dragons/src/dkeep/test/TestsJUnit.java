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
		System.out.println(game.currentMap.getName());
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
