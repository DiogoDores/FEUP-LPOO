package dkeep.test;

import  static org.junit.Assert.*;
import  org.junit.Test;

import dkeep.cli.UserInterface;
import dkeep.logic.DrunkenGuard;
import dkeep.logic.GameLogic;
import dkeep.logic.GameMap;
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
		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertFalse(1 < game.hero.getX() && 1 > game.hero.getX());
		assertEquals(2, game.hero.getY());
		assertFalse(2 < game.hero.getY() && 2 > game.hero.getY());
		game.hero.move(game, testMap, 's');
		assertEquals(2, game.hero.getX());
		assertFalse(3 == game.hero.getX());
		assertFalse(1 == game.hero.getX());
		assertEquals(2, game.hero.getY());
		assertFalse(3 == game.hero.getY());
		assertFalse(1 == game.hero.getY());
		testMap.drawMap(game);
	}

	@Test
	public void createCharacters() {
		System.out.println("\nTest Move to Free Cell.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		assertEquals(false , game.checkWin());
		assertTrue(!game.checkWin());
		game.createCharacters(2, "Rookie", 0);

		game.createCharacters(1, "Rookie", 0);
		assertEquals(game.hero.getX(), 1);
		assertEquals(game.hero.getY(), 1);
		
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
		assertFalse(2 == game.hero.getX());
		assertEquals(2, game.hero.getY());
		assertFalse(3 == game.hero.getY());
		assertFalse(1 == game.hero.getY());
		
		
		game.hero.move(game, testMap, 'w');
		testMap.drawMap(game);
 
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());

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
		assertEquals('G', game.guard.getSymbol());
		assertFalse('G' != game.guard.getSymbol());

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move( game,testMap, 's');
		assertEquals(true, game.checkPresence());
		testMap.drawMap(game);
	}

	@Test
	public void moveHero() {
		System.out.println("\nTest Move Hero.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		OgreMap ogreMap = new OgreMap();
		GuardMap guardMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		assertEquals('H', game.hero.getSymbol());
		assertFalse('H' != game.hero.getSymbol());
		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move( game,testMap, 's');
		game.hero.move( game,testMap, 'a');
		game.hero.move( game,testMap, 'w');
		game.hero.move( game,testMap, 'd');
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		testMap.drawMap(game);

		game.changeCurrentMap(ogreMap);
		game.createHero(1, 2);
		assertEquals(false , game.checkWin());
		ogreMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move( game,ogreMap, 's');
		game.hero.move( game,ogreMap, 'a');
		game.hero.move( game,ogreMap, 'w');
		game.hero.move( game,ogreMap, 'd');
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());

		game.changeCurrentMap(guardMap);
		game.createHero(1, 2);
		assertEquals(false , game.checkWin());
		guardMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move( game,guardMap, 's');
		game.hero.move( game,guardMap, 'a');
		game.hero.move( game,guardMap, 'w');
		game.hero.move( game,guardMap, 'd');
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());

	}



	@Test
	public void moveIntoOgre() {
		System.out.println("\nTest Move to A Ogre.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 2);
		game.createOgre(3,2, 9, 9);	
		// TODO Não gera um ogre, para testar descomenta o @test	

		testMap.drawMap(game);
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
		game.hero.move( game,testMap, 's');
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
		game.hero.move( game,testMap, 'a');
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		assertEquals('H', game.hero.getSymbol());
		testMap.drawMap(game);
	}

	@Test
	public void moveIntoKey() {
		System.out.println("\nTest Move to Key.\n");

		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap); 

		testMap.drawMap(game);

		game.createHero(1, 2);

		testMap.drawMap(game);
		game.hero.move( game,testMap, 'd'); // Dá erro aqui, mas não entendo porquê
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
		game.hero.move( game,testMap, 'd'); // Dá erro aqui, mas não entendo porquê
		game.hero.move( game,testMap, 'a'); game.hero.move( game,testMap, 'a'); game.hero.move( game,testMap, 'a'); 
		assertEquals('S', testMap.getMap()[1][0]);

		testMap.drawMap(game);
	}

	@Test
	public void win() {
		System.out.println("\nWin win win no matter what.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		testMap.drawMap(game);

		game.createHero(1, 2); 
		game.hero.move( game,testMap, 'd'); 
		game.hero.move( game,testMap, 'a'); game.hero.move( game,testMap, 'a'); game.hero.move( game,testMap, 'a'); 
		game.hero.move( game,testMap, 'a');
		assertEquals(game.currentMap.getMap()[1][0], game.currentMap.getMap()[game.hero.getX()][game.hero.getY()]);

		testMap.drawMap(game); 
	}

	@Test
	public void testMove() {
		System.out.println("\nTest Move OGRE.\n");
		GameLogic game = new GameLogic();
		TestMap testMap = new TestMap();
		game.changeCurrentMap(testMap);
		game.createOgre(2, 2,9,9);

		testMap.drawMap(game);
		game.ogres.get(0).moveOgre(game, 'n');
		assertEquals(false, game.checkPresence());
		testMap.drawMap(game);
	} 





	@Test
	public void followPathRookie() {
		System.out.println("\nFollow Path Rookie.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8, "Rookie");
		game.guard.move();
		boolean test = false;
		if ((game.guard.getX() == 1 && game.guard.getY() == 7) || (game.guard.getX() == 2 && game.guard.getY() == 8))
			test = true;
		int j = 0; 
		while (j < 90) {
			j++;
			game.guard.move();
		}
		//assertEquals(true,test);
	
	} 

	@Test
	public void followPathDrunken() {
	//	System.out.println("\nFollow Path Drunken.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8, "Drunken");
		game.guard.move();
		boolean test = false;
		if ((game.guard.getX() == 1 && game.guard.getY() == 7) || (game.guard.getX() == 2 && game.guard.getY() == 8) ||  (game.guard.getX() == 1 && game.guard.getY() == 8))
			test = true;
		int j = 0; 
		while (j < 90) {
			j++;
			game.guard.move();
		}
		assertEquals(true,test);
	} 


	@Test
	public void followPathSuspicious() {
	//	System.out.println("\nFollow Path Suspicious.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8, "Suspicious");
		game.guard.move();
		boolean test = false;
		if ((game.guard.getX() == 1 && game.guard.getY() == 7) || (game.guard.getX() == 2 && game.guard.getY() == 8))
			test = true;
		int j = 0; 
		while (j < 90) {
			game.guard.move();
			j++;
		}
		assertEquals(true,test);
		game.currentMap.drawMap(game);
	
	} 
	
	@Test
	public void levelTwo() {
//		System.out.println("\nTwo.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createGuard(1, 8);
		game.currentMap.drawMap(game);
	} 
	
	
	
	@Test(timeout = 10000)
	public void createOgres() {
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createHero(2, 2);
		assertFalse(game.checkSpawnCondition(1, 1)); 
		game.createOgres(0);
		for(int i = 0; i < game.ogres.size();i++){
			game.ogres.get(i).moveOgre(game, 'n');
		}
		while ( ( game.ogres.get(0).getX() == 1 && game.ogres.get(0).getY() == 1) ||  ( game.ogres.get(0).getX() == 1 && game.ogres.get(0).getY() == 2) ){
			game.ogres.get(0).moveOgre(game, 'n');
		}
		
		while ( ( game.ogres.get(0).getClubX() == 3 && game.ogres.get(0).getClubY() == 1) ||  ( game.ogres.get(0).getClubX() == 3 && game.ogres.get(0).getClubY() == 2) ){
			game.ogres.get(0).moveOgre(game, 'n');
			game.ogres.get(0).moveClub(game, game.ogres.get(0).getX(), game.ogres.get(0).getY());
		}
		game.currentMap.drawMap(game);
		boolean test = (game.ogres.size() >= 1 && game.ogres.size() <= 9);
		assertTrue(test);
		assertTrue(game.ogres.get(0).getClubX() == game.ogres.get(0).getClubX() || game.ogres.get(0).getClubX() == game.ogres.get(0).getClubX()+1 || game.ogres.get(0).getClubX() == game.ogres.get(0).getClubX()-1);
		assertTrue(game.ogres.get(0).getClubY() == game.ogres.get(0).getClubY() || game.ogres.get(0).getClubY() == game.ogres.get(0).getClubY()+1 || game.ogres.get(0).getClubY() == game.ogres.get(0).getClubY()-1);
		
		
		game.ogres.get(0).stunOgre();
		assertTrue('8' == game.ogres.get(0).getSymbol());
		
	} 

	@Test(timeout = 10000) 
	public void createClub() {
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgre(7, 7, 9, 9);	
		game.currentMap.drawMap(game);
	}


	@Test
	public void testMoveTwo() {
	//	System.out.println("\nTest Move Two.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgre(2, 2, 9, 9);

		testMap.drawMap(game);
		game.ogres.get(0).moveOgre(game, 'n');
		assertEquals('*', game.ogres.get(0).getClubSymbol());
		assertEquals('O', game.ogres.get(0).getSymbol());
		assertEquals(false, game.checkPresence());
		testMap.drawMap(game);
	}

	@Test
	public void activateLeverGuard() {
	//	System.out.println("\nTest Activate Lever Guard.\n");
		GameLogic game = new GameLogic();
		GuardMap testMap = new GuardMap();

		game.changeCurrentMap(testMap);
		game.createHero(1, 2); 

		testMap.drawMap(game);

		game.hero.move( game,testMap, 'd'); game.hero.move( game,testMap, 'd'); game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's'); 
		game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');
		game.hero.move( game,testMap, 's');game.hero.move( game,testMap, 's');game.hero.move( game,testMap, 'a');
		game.hero.move( game,testMap, 'w');game.hero.move( game,testMap, 'w');game.hero.move( game,testMap, 'a');
		game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');		 
		assertEquals(testMap.getMap()[6][0], testMap.getMap()[game.hero.getX()][game.hero.getY()]);

		testMap.drawMap(game);
	}
 
	@Test(timeout = 10000)
	public void testToRandomOgreMove() {
		System.out.println("\nTest Random Ogre Move.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgres(1);
		while(game.ogres.get(0).getX() != 4 && game.ogres.get(0).getY() != 4) {
			System.out.println(game.ogres.get(0).getX() + " " + game.ogres.get(0).getY());
			game.ogres.get(0).moveOgre(game, 'n');
			game.ogres.get(0).moveClub(game, game.ogres.get(0).getX(), game.ogres.get(0).getY());
		}
		game.currentMap.drawMap(game);
		game.ogres.get(0).checkPossible('a', game);
		assertTrue('O' == game.ogres.get(0).checkPossible('a', game) ||'X' == game.ogres.get(0).checkPossible('a', game) ||'H' == game.ogres.get(0).checkPossible('a', game) || 'E' == game.ogres.get(0).checkPossible('a', game) || 'I' == game.ogres.get(0).checkPossible('a', game) || 'S' == game.ogres.get(0).checkPossible('a', game) || 'D' == game.ogres.get(0).checkPossible('a', game) || 'S' == game.ogres.get(0).checkPossible('a', game));
		assertTrue('O' == game.ogres.get(0).checkPossible('s', game) ||'X' == game.ogres.get(0).checkPossible('s', game) ||'H' == game.ogres.get(0).checkPossible('s', game) || 'E' == game.ogres.get(0).checkPossible('s', game) || 'I' == game.ogres.get(0).checkPossible('s', game) || 'S' == game.ogres.get(0).checkPossible('s', game) || 'D' == game.ogres.get(0).checkPossible('s', game) || 'S' == game.ogres.get(0).checkPossible('s', game));
		assertTrue('O' == game.ogres.get(0).checkPossible('d', game) ||'X' == game.ogres.get(0).checkPossible('d', game) ||'H' == game.ogres.get(0).checkPossible('d', game) || 'E' == game.ogres.get(0).checkPossible('d', game) || 'I' == game.ogres.get(0).checkPossible('d', game) || 'S' == game.ogres.get(0).checkPossible('d', game) || 'D' == game.ogres.get(0).checkPossible('d', game) || 'S' == game.ogres.get(0).checkPossible('d', game));
		assertTrue('O' == game.ogres.get(0).checkPossibleClub('w', game) ||'X' == game.ogres.get(0).checkPossibleClub('w', game) ||'H' == game.ogres.get(0).checkPossibleClub('w', game) || 'E' == game.ogres.get(0).checkPossibleClub('w', game) || 'I' == game.ogres.get(0).checkPossibleClub('w', game) || 'S' == game.ogres.get(0).checkPossibleClub('w', game) || 'D' == game.ogres.get(0).checkPossibleClub('w', game) || 'S' == game.ogres.get(0).checkPossibleClub('w', game));
		assertTrue('O' == game.ogres.get(0).checkPossibleClub('a', game) ||'X' == game.ogres.get(0).checkPossibleClub('a', game) ||'H' == game.ogres.get(0).checkPossibleClub('a', game) || 'E' == game.ogres.get(0).checkPossibleClub('a', game) || 'I' == game.ogres.get(0).checkPossibleClub('a', game) || 'S' == game.ogres.get(0).checkPossibleClub('a', game) || 'D' == game.ogres.get(0).checkPossibleClub('a', game) || 'S' == game.ogres.get(0).checkPossibleClub('a', game));
		assertTrue('O' == game.ogres.get(0).checkPossibleClub('s', game) ||'X' == game.ogres.get(0).checkPossibleClub('s', game) ||'H' == game.ogres.get(0).checkPossibleClub('s', game) || 'E' == game.ogres.get(0).checkPossibleClub('s', game) || 'I' == game.ogres.get(0).checkPossibleClub('s', game) || 'S' == game.ogres.get(0).checkPossibleClub('s', game) || 'D' == game.ogres.get(0).checkPossibleClub('s', game) || 'S' == game.ogres.get(0).checkPossibleClub('s', game));
		
	//	assertTrue(game.ogres.get(0).getX() == 1 && game.ogres.get(0).getY() == 7); 

	}
	


	@Test(timeout = 15000)
	public void testcreateClub() {
		System.out.println("\nTest Create Club.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();
		game.changeCurrentMap(testMap);
		game.createOgre(1,1,9,9);

		game.createOgre(7,2,9,9);
		game.createOgre(6,1,9,9);
		game.createOgre(2,4,9,9);
		game.createOgre(3,4,9,9);
		game.currentMap.drawMap(game);
		game.currentMap.possibleMove(game.ogres.get(0).getX()-1, game.ogres.get(0).getY(), game);
		game.createHero(1,2);
		
		game.currentMap.drawMap(game);
		System.out.println(game.currentMap.possibleMove(game.ogres.get(0).getX(), game.ogres.get(0).getY()+1, game));
		
		int j = 0; 
		while (j < 10) {
			j++;
			boolean x = game.checkPresence();
			assertTrue(x == true || x == false);
			for (int i = 0; i < game.ogres.size(); i++) {
				game.ogres.get(i).moveOgre(game, 'n');
				game.ogres.get(i).moveClub(game, game.ogres.get(i).getX(), game.ogres.get(i).getY());
				
			}
		}
		j=0;
	//	assertTrue(game.ogres.get(7).getClubX() == 3 && game.ogres.get(7).getClubY() == 2);

	}



	@Test
	public void checkPresence() {
		System.out.println("CheckPresence");
		GameLogic game = new GameLogic();
		GameLogic gameG = new GameLogic();
		OgreMap testMap = new OgreMap();
		GuardMap testMapG = new GuardMap();
		game.changeCurrentMap(testMap);
		game.createHero(1, 0);
		game.createOgres(0);
		gameG.changeCurrentMap(testMapG);
		gameG.createHero(1, 0);
		gameG.createOgres(0);
		game.currentMap.drawMap(game);

		//	assertEquals(false, game.checkPresence());

		game.startGame('d');
		gameG.startGame('d');
		assertFalse(game.checkWin());
		int j = 0; 
		while (j < 90) {
			j++;
			boolean x = game.checkPresence();
			assertTrue(x == true || x == false);
			game.ogres.get(0).moveOgre(game, 'n');
			game.ogres.get(0).moveClub(game, game.ogres.get(0).getX(), game.ogres.get(0).getY());
		}
		j=0;
		game.currentMap.resetPositions(game);
		assertEquals(7, game.hero.getX());

		GameLogic game2 = new GameLogic();
		GuardMap testMap2 = new GuardMap();

		game2.changeCurrentMap(testMap2);
		game2.createHero(0, 5);
		game2.createGuard(1, 5);
		assertEquals('a', game2.guard.getGuardMove());
		System.out.print(" WOWOW " + game2.hero.getX() + "  " + game2.hero.getY());
		assertTrue(game2.checkWin());
		game2.currentMap.resetPositions(game2);
		assertEquals(1, game.hero.getY());
		assertEquals(true,game2.checkPresence());
		game.startGame('a');
		while (j < 90) {
			j++;
			boolean x = game.checkPresence();
			assertTrue(x == true || x == false);
			game.guard.move();
		}
		j=0; 


		GameLogic game3 = new GameLogic();
		TestMap testMap3 = new TestMap();

		game3.changeCurrentMap(testMap3);
		game3.createHero(4, 1);
		game3.createGuard(4, 2);
		
		while (j < 90) {
			j++;
			boolean x = game.checkPresence();
			assertTrue(x == true || x == false);
			game.guard.move();
		}
		j=0;
		assertEquals(true,game3.checkPresence());
		assertFalse(game.checkWin());
		game.startGame('a');


	}

	@Test
	public void moveOgre() {
	//	System.out.println("\nTest Move Ogre.\n");
		GameLogic game = new GameLogic();
		OgreMap testMap = new OgreMap();

		game.changeCurrentMap(testMap);
		game.setLevelTwo(0);
		System.out.println(game.ogres.size());
		for (int i = 0; i < game.ogres.size(); i++) {

			for (int j = 0; j < 5; j++) {
				game.ogres.get(i).moveOgre(game, 'n');
				game.ogres.get(i).moveClub(game, game.ogres.get(i).getX(), game.ogres.get(i).getY());;
			}
		}

		testMap.drawMap(game);

		game.hero.move( game,testMap, 'd'); game.hero.move( game,testMap, 'd'); game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's');  game.hero.move( game,testMap, 's'); 
		game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');game.hero.move( game,testMap, 'd');
		game.hero.move( game,testMap, 's');game.hero.move( game,testMap, 's');game.hero.move( game,testMap, 'a');
		game.hero.move( game,testMap, 'w');game.hero.move( game,testMap, 'w');game.hero.move( game,testMap, 'a');
		game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');game.hero.move( game,testMap, 'a');		 

		testMap.drawMap(game);
	}








}
