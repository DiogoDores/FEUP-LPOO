package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.cli.UserInterface;
import dkeep.logic.DrunkenGuard;
import dkeep.logic.GameLogic;
import dkeep.logic.GuardMap;
import dkeep.logic.OgreMap;
import dkeep.logic.RookieGuard;
import dkeep.logic.SuspiciousGuard;

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
		assertEquals(false, game.checkPresence());
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
		System.out.println("\nTest Move OGRE.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createOgre(2, 2);

		testMap.drawMap(game);
		game.ogres.get(0).moveOgre(game);
		assertEquals(false, game.checkPresence());
		testMap.drawMap(game);
	} 

	

	
	
	@Test
	public void followPathRookie() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8, "Drunken");
		game.guard.move();
		boolean test = false;
		if ((game.guard.getX() == 1 && game.guard.getY() == 7) || (game.guard.getX() == 2 && game.guard.getY() == 8))
			test = true;
		
		assertEquals(true,test);
		game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	

	} 
	
	@Test
	public void followPathDrunken() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8, "Drunken");
		game.guard.move();
		boolean test = false;
		if ((game.guard.getX() == 1 && game.guard.getY() == 7) || (game.guard.getX() == 2 && game.guard.getY() == 8) ||  (game.guard.getX() == 1 && game.guard.getY() == 8))
			test = true;
		
		assertEquals(true,test);
		game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	
	} 
	
	
	@Test
	public void followPathSuspicious() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8, "Suspicious");
		game.guard.move();
		boolean test = false;
		if ((game.guard.getX() == 1 && game.guard.getY() == 7) || (game.guard.getX() == 2 && game.guard.getY() == 8))
			test = true;
		
		assertEquals(true,test);
		game.currentMap.drawMap(game);
		game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	game.guard.move(); 	

	} 
	
	@Test
	public void levelTwo() {
		System.out.println("\nTwo.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8);
		game.currentMap.drawMap(game);
	} 
	

	@Test
	public void createOgres() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgres(0);
		for(int i = 0; i < game.ogres.size();i++){
			game.ogres.get(i).moveOgre(game);
		}
		game.currentMap.drawMap(game);
	} 

/*

	@Test
	public void testMoveTwo() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgre(2, 2);

		testMap.drawMap(game);
		game.ogres.get(0).moveOgre(game);
		
		assertEquals(true, game.checkPresence());
		testMap.drawMap(game);
	}
	*/
	/*
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
	} */



	

	
	
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
	public void testToRandomOgreMove() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgres(0);
		for(int i = 0; i < game.ogres.size();i++){
			game.ogres.get(i).moveOgre(game); game.ogres.get(i).moveOgre(game); game.ogres.get(i).moveOgre(game);game.ogres.get(i).moveOgre(game);
			game.ogres.get(i).moveOgre(game); game.ogres.get(i).moveOgre(game); game.ogres.get(i).moveOgre(game);game.ogres.get(i).moveOgre(game);
			game.ogres.get(i).moveOgre(game); game.ogres.get(i).moveOgre(game); game.ogres.get(i).moveOgre(game);

		}
		game.currentMap.drawMap(game);
	}
	
	@Test
	public void testToRandomGuardMove() {
		System.out.println("\nTest Move.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createGuard(2,3);
		game.createGuard(1,3);
		game.guard.move();game.guard.move();game.guard.move();game.guard.move();game.guard.move();game.guard.move();game.guard.move();game.guard.move();
		
		game.currentMap.drawMap(game);
	}


	
	@Test
	public void checkPresence() {
		System.out.println("WOW");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		
		game.changeCurrentMap(testMap);
		game.createHero(1, 1);
		game.createOgres(0);
		game.currentMap.drawMap(game);

		assertEquals(false, game.checkPresence());
		
		assertEquals(0,game.startGame('d'));
		
		
		GameLogic game2 = new GameLogic();
		GuardMap testMap2 = new GuardMap();
		
		game2.changeCurrentMap(testMap2);
		game2.createHero(4, 1);
		game2.createGuard(4, 2);
		assertEquals(true,game2.checkPresence());
		
		assertEquals(0,game2.startGame('a'));
		assertEquals(false, game.stunOgre());
		
		GameLogic game3 = new GameLogic();
		TestMap testMap3 = new TestMap();
		
		game3.changeCurrentMap(testMap3);
		game3.createHero(4, 1);
		game3.createGuard(4, 2);
		assertEquals(true,game3.checkPresence());
		
	}
	
	@Test
	public void moveOgre() {
		System.out.println("\nTest Move Ogre.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		
		game.changeCurrentMap(testMap);
		game.setLevelTwo(0);
		System.out.println(game.ogres.size());
		for (int i = 0; i < game.ogres.size(); i++) {
			
			for (int j = 0; j < 5; j++) {
				game.ogres.get(i).moveOgre(game);
				game.ogres.get(i).moveClub(game, game.ogres.get(i).getX(), game.ogres.get(i).getY());;
			}
		}

		testMap.drawMap(game);

		game.hero.move(testMap, 'd'); game.hero.move(testMap, 'd'); game.hero.move(testMap, 's');  game.hero.move(testMap, 's');  game.hero.move(testMap, 's');  game.hero.move(testMap, 's');  game.hero.move(testMap, 's'); 
		game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');game.hero.move(testMap, 'd');
		game.hero.move(testMap, 's');game.hero.move(testMap, 's');game.hero.move(testMap, 'a');
		game.hero.move(testMap, 'w');game.hero.move(testMap, 'w');game.hero.move(testMap, 'a');
		game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');game.hero.move(testMap, 'a');		 
	
		testMap.drawMap(game);
	}








}
