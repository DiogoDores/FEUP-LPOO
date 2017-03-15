package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;
import dkeep.test.*;

public class GameLogic {

	Random random = new Random();
	public Guard guard = new Guard();
	public Hero hero = new Hero();
	public Ogre ogre = new Ogre();
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	public GameMap Map1 = new GuardMap(); 
	public GameMap Map2 = new OgreMap();
	public GameMap currentMap;
	private boolean isStunned = false;
	private int wait = 0;

	public int startGame(char key){

		boolean lost = false;

		hero.move(currentMap, key);

		if(currentMap.getName() == "GuardMap"){
			guard.move(); 
		} else {
			if(wait == 0){
				for(int i = 0; i < ogres.size(); i++){
					ogres.get(i).moveOgre(this);

				}
				for(int i = 0; i < ogres.size(); i++){
					ogres.get(i).moveClub(this, ogres.get(i).getX(), ogres.get(i).getY());
				}

			}
		}

		isStunned = stunOgre();

		if(isStunned){
			if(wait == 2){
				ogre.symbol = 'O';
				wait = 0;
			}else{
				ogre.symbol = '8';
				wait++;
			}
		}

		lost = checkPresence();

		if(lost){
			return 2;
		}

		if(hero.getY() == 0 && (hero.getX() == 5 || hero.getX() == 6)){
			System.out.println("\n\nPhew! You escaped the guard!\nBut what's that?\nOh no! An ogre!\nGrab the key and escape!\nBe careful with his club!\n\n");
			setLevelTwo();
		}

		if(hero.getX() == 1 && hero.getY() == 0){
			return 1;
		}

		return 0;
	}

	public boolean stunOgre() {
		if(hero.getX() == ogre.getX() && (hero.getY() == ogre.getY() + 1 || hero.getY() == ogre.getY() - 1)){
			return true;
		} else if(hero.getY() == ogre.getY() && (hero.getX() == ogre.getX() + 1 || hero.getX() == ogre.getX() - 1)){
			return true;
		}
		return false;
	}

	public void changeCurrentMap(GameMap gameMap){
		currentMap = gameMap;
	}

	public void createHero(int x, int y){
		hero = new Hero(x, y);
	}

	public void createGuard(int x, int y, String typeOfGuard){		
		if(typeOfGuard == "Rookie"){
			guard = new RookieGuard(x, y);
		} else if(typeOfGuard == "Drunken"){
			guard = new DrunkenGuard(x, y);
		} else if(typeOfGuard == "Suspicious"){
			guard = new SuspiciousGuard(x, y);
		}
	}

	public boolean checkPresence() {

		if(currentMap.getName() == "GuardMap"){
			if(guard.symbol == 'G' && hero.getX() == guard.getX() && (hero.getY() == guard.getY() + 1 || hero.getY() == guard.getY() - 1)){
				return true;
			} else if(hero.getY() == guard.getY() && (hero.getX() == guard.getX() + 1 || hero.getX() == guard.getX() - 1)){
				return true;
			}
		} else if (currentMap.getName() == "OgreMap"){
			if(currentMap.getMap()[hero.getX()][hero.getY()] == '*'){
				return true;
			}
		} 
		else if (currentMap.getName() == "TestMap") {
			if(hero.getX() == guard.getX() && (hero.getY() == guard.getY() + 1 || hero.getY() == guard.getY() - 1)){
				return true;
			} else if(hero.getY() == guard.getY() && (hero.getX() == guard.getX() + 1 || hero.getX() == guard.getX() - 1)){
				return true;
			}
			else if(hero.getX() == ogre.getX() && (hero.getY() == ogre.getY() + 1 || hero.getY() == ogre.getY() - 1)){
				return true;
			} else if(hero.getY() == ogre.getY() && (hero.getX() == ogre.getX() + 1 || hero.getX() == ogre.getX() - 1)){
				return true;

			}
		}

		return false;
	}

	public void setLevelTwo(){
		currentMap = Map2;
		createHero(7, 1);
		hero.setSymbol('A');
		createOgres();
	}

	public void createOgres() {

		int numOgres = random.nextInt(4) + 1; 
		int x;
		int y;

		for(int i = 0; i < numOgres; i++){

			do{
				x = random.nextInt(7) + 1;
				y = random.nextInt(7) + 1;
			} while((x == hero.getX() && y == hero.getY()) || (x == hero.getX() - 1 && y == hero.getY()) || (x == hero.getX() && y == hero.getY() + 1));

			ogres.add(new Ogre(x,y));
		}
	}

	public void createOgre(int x, int y) {
		ogre = new Ogre(x, y);
	}

	public char createGuard(int x, int y) {
		String type = "DSG";
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
}
