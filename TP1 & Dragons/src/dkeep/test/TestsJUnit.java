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

	@Test
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
		testMap.drawMap(game);
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
	
	@Test
	public void moveIntoKey() {
		System.out.println("\nTest Move to Key.\n");

		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		testMap.drawMap(game);

		game.changeCurrentMap(testMap); 
		game.createHero(1, 2);
			
		testMap.drawMap(game);
		game.hero.move(testMap, 'd'); // Dá erro aqui, mas não entendo porquê
		assertEquals('K', game.hero.getSymbol());
	
		testMap.drawMap(game);
	}
	
	@Test
	public void openDoor() {
		System.out.println("\nTest Open Door.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();

		game.changeCurrentMap(testMap);
		game.createHero(1, 2); 
			
		testMap.drawMap(game);
		game.hero.move(testMap, 'd'); // Dá erro aqui, mas não entendo porquê
		game.hero.move(testMap, 'a'); game.hero.move(testMap, 'a'); game.hero.move(testMap, 'a'); 
		assertEquals('S', testMap.getMap()[1][0]);
	
		testMap.drawMap(game);
	}
	
	@Test
	public void win() {
		System.out.println("\nWin win win no matter what.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2); 
		testMap.drawMap(game);
		game.hero.move(testMap, 'd'); // Dá erro aqui, mas não entendo porquê
		game.hero.move(testMap, 'a'); game.hero.move(testMap, 'a'); game.hero.move(testMap, 'a'); 
		game.hero.move(testMap, 'a');
		assertEquals(game.currentMap.getMap()[1][0], game.currentMap.getMap()[game.hero.getX()][game.hero.getY()]);

		testMap.drawMap(game);
	}
	
	@Test
	public void testMove() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createOgre(2, 2);

		testMap.drawMap(game);
		game.ogre.moveOgre(testMap);
		assertEquals(true, gamePresence(game, 2,2));
		testMap.drawMap(game);
	} 
	@Test
	public void testMoveTwo() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgre(2, 2);

		testMap.drawMap(game);
		game.ogre.moveOgre(testMap);
		assertEquals(true, gamePresence(game, 2,2));
		testMap.drawMap(game);
	}
	
	@Test
	public void testMoveTrois() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(2, 2);

		testMap.drawMap(game);
		game.ogre.moveOgre(testMap);
		assertEquals(true, gamePresence(game, 2,2));
		testMap.drawMap(game);
	} 
	
	
	private boolean gamePresence(GameLogic game, int x, int y){
		if(game.ogre.getX() == x + 1 && game.ogre.getY()== y)
			return true;
		else if(game.ogre.getX() == x -1 && game.ogre.getY()== y)
			return true;
		else if(game.ogre.getX() == x  && game.ogre.getY()== y+1)
			return true; 
		else if(game.ogre.getX() == x  && game.ogre.getY()== y-1)
			return true; 
		return false;
		
	}
	
	@Test
	public void activateLever() {
		System.out.println("\nTest Open Door.\n");
		GameLogic game = new GameLogic();
		TestMapGuard testMap = new TestMapGuard();

		game.changeCurrentMap(testMap);
		game.createHero(1, 2); 
			
		testMap.drawMap(game);
		game.hero.move(testMap, 'd'); // Dá erro aqui, mas não entendo porquê
		game.hero.move(testMap, 'a'); game.hero.move(testMap, 'a'); game.hero.move(testMap, 'a'); 
		assertEquals('S', testMap.getMap()[1][0]);
	
		testMap.drawMap(game);
	}
	
	@Test
	public void activateLeverGuard() {
		System.out.println("\nTest Open Door.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();

		game.changeCurrentMap(testMap);
		game.createHero(1, 2); 
			
		testMap.drawMap(game);
		
		game.hero.move(testMap, 'd'); game.hero.move(testMap, 'd'); game.hero.move(testMap, 's');  game.hero.move(testMap, 's');  game.hero.move(testMap, 's');  game.hero.move(testMap, 's');  game.hero.move(testMap, 's'); 
		 game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');
		 game.hero.move(testMap, 's');game.hero.move(testMap, 's');game.hero.move(testMap, 'a');
		 game.hero.move(testMap, 'w');game.hero.move(testMap, 'w');game.hero.move(testMap, 'a');
		  game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');		 
		assertEquals(testMap.getMap()[6][0], testMap.getMap()[game.hero.getX()][game.hero.getY()]);
	
		testMap.drawMap(game);
	}
	
	@Test
	public void mainTest() {
		System.out.println("\nMain.\n");
		
		assertEquals(1, UserInterface.selectLevel());
	
	}
	
	

	
	
	
	
	
}
