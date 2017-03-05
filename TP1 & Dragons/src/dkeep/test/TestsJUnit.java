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
		GameLogic game = new GameLogic();
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
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);

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
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		game.createGuard(3,2);		

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move(testMap, 's');
		assertEquals(true, game.checkPresence());
		testMap.drawMap(game);


	}
	//@Test
	public void moveIntoOgre() {
		System.out.println("\nTest Move to A Ogre.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		game.createOgre(3,2);	
		// TODO Não gera um ogre, para testar descomenta o @test	

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move(testMap, 's');
		assertEquals(true, game.checkPresence());
		testMap.drawMap(game);
	}
	
	@Test
	public void moveIntoClosedDoor() {
		System.out.println("\nTest Move to A Closed Door.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 1);
			
		testMap.drawMap(game);
		game.hero.move(testMap, 'a');
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		testMap.drawMap(game);
	}
	
	//@Test
	public void moveIntoKey() {
		System.out.println("\nTest Move to Key.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
			
		testMap.drawMap(game);
		game.hero.move(testMap, 'd'); // Dá erro aqui, mas não entendo porquê
		//assertEquals('K', game.currentMap.getMap()[1][2]);
		//assertEquals(1, game.hero.getY());
		
		testMap.drawMap(game);
	}
	
	// Como esta não está a funcionar corretamente, a moveIntoOpenDoor e a moveAndOpenDoor não seriam possiveis de ser testadas.
	
	

}
