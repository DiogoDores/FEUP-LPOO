package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import dkeep.test.*;

public class GameLogic {

	Random random = new Random();
	public Guard guard = new Guard();
	public Hero hero = new Hero();
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();
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
		String type = "D";
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
		
		if(currentMap.getName() == "GuardMap"){
			if(hero.getX() == guard.getX() && (hero.getY() == guard.getY() + 1 || hero.getY() == guard.getY() - 1)){
				return true;
			} else if(hero.getY() == guard.getY() && (hero.getX() == guard.getX() + 1 || hero.getX() == guard.getX() - 1)){
				return true;
			}
		} else {
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
		createOgre();
	}

	public void createOgre() {

		int numOgres = random.nextInt(5);
		int x;
		int y;

		for(int i = 0; i < numOgres; i++){

			do{
				x = random.nextInt(9) + 1;
				y = random.nextInt(9) + 1;
			} while((x == hero.getX() && y == hero.getY()) || (x == hero.getX() - 1 && y == hero.getY()) || (x == hero.getX() && y == hero.getY() + 1));
			
			ogres = new ArrayList<Ogre>();
		}
	}
}
