package dkeep.logic;

import java.util.Random;
import dkeep.test.*;

public class GameLogic {

	public Guard guard = new Guard();
	public Hero hero = new Hero();
	public Ogre ogre = new Ogre();
	public GameMap Map1 = new GuardMap();
	public GameMap Map2 = new OgreMap();
	public GameMap currentMap;

	public int startGame(char key){

		boolean lost = false;

		hero.move(currentMap, key);

		if(currentMap.getName() == "GuardMap"){
			guard.move(); 
		} else {
			ogre.moveOgre(currentMap);
		}

		lost = checkPresence();

		if(lost){
			return 2;
		}

		if(hero.getY() == 0 && (hero.getX() == 5 || hero.getX() == 6)){
			setLevelTwo();
		}

		if(hero.getX() == 1 && hero.getY() == 0){
			return 1;
		}

		return 0;
	}

	public void changeCurrentMap(GameMap gameMap){
		currentMap = gameMap;
	}

	public void createHero(int x, int y){
		hero = new Hero(x, y);
	}

	public char createGuard(int x, int y){
		String type = "S";
		Random random = new Random();
		int r = random.nextInt(type.length());
		char typeOfGuard = type.charAt(r);

		if(typeOfGuard == 'G'){
			guard = new RookieGuard(x, y);
		} else if(typeOfGuard == 'D'){
			guard = new DrunkenGuard(x, y);
		} else if(typeOfGuard == 'S'){
			guard = new SuspiciousGuard(x, y);
		}

		return typeOfGuard;
	}

	public boolean checkPresence() {

		/*if (guard.getY() == hero.getY() && (guard.getX() == hero.getX() - 1 || guard.getX() == hero.getX() + 1)) {
			return true;

		}
		else if (guard.getX() == hero.getX() && (guard.getY() == hero.getY() - 1 || guard.getY() == hero.getY() + 1)){
			return true;

		} else if (ogre.getY() == hero.getY() && (ogre.getX() == hero.getX() - 1 || ogre.getX() == hero.getX() + 1)) {
			return true;

		} else if (ogre.getX() == hero.getX() && (ogre.getY() == hero.getY() - 1 || ogre.getY() == hero.getY() + 1)){
			return true;

		}*/
		if(currentMap.getName() == "GuardMap" || currentMap.getName() == "TestMap"){
			if(hero.getX() == guard.getX() && (hero.getY() == guard.getY() + 1 || hero.getY() == guard.getY() - 1)){
				return true;
			} else if(hero.getY() == guard.getY() && (hero.getX() == guard.getX() + 1 || hero.getX() == guard.getX() - 1)){
				return true;
			}
		} else if (currentMap.getName() == "OgreMap" || currentMap.getName() == "TestMap"){
			if(hero.getX() == ogre.getX() && (hero.getY() == ogre.getY() + 1 || hero.getY() == ogre.getY() - 1)){
				return true;
			} else if(hero.getY() == ogre.getY() && (hero.getX() == ogre.getX() + 1 || hero.getX() == ogre.getX() - 1)){
				return true;
			} else if(currentMap.getMap()[hero.getX()][hero.getY()] == '*'){
				return true;
			}
		}


		return false;
	}

	public void setLevelTwo(){
		System.out.println("\n\nPhew! You escaped the guard!\nBut what's that?\nOh no! An ogre!\nGrab the key and escape!\nBe careful with his club!\n\n");
		currentMap = Map2;
		hero = new Hero(7, 1);
		ogre = new Ogre(1, 5);
	}

	public void createOgre(int x, int y) {
		ogre = new Ogre(x, y);
	}
}
