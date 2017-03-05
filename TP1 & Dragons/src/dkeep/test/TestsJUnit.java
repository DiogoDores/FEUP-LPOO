package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.UserInterface;
import dkeep.logic.*;

public class TestsJUnit {

	public TestsJUnit(){};

	@Test
	public void moveIntoFreeCell() {
		System.out.println("\nTest Move to Free Cell.\n");
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
		testMap.drawMap(game);
	}
	
	
	@Test
	public void moveIntoAWall() {
		System.out.println("\nTest Move to A Wall.\n");
		GameLogic game = UserInterface.game;
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);

		char[][] map = game.currentMap.getMap();

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move(testMap, 'w');
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		testMap.drawMap(game);

		game.createGuard(3,1);		

	}
	
	@Test
	public void moveIntoGuard() {
		System.out.println("\nTest Move to A Guard.\n");
		GameLogic game = UserInterface.game;
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		game.createGuard(3,2);		

		char[][] map = game.currentMap.getMap();

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move(testMap, 's');
		assertEquals(true, game.checkPresence());
		testMap.drawMap(game);


	}

}
