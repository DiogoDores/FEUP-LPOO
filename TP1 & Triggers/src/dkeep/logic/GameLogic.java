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
	private int wait = 0;

	public int startGame(char key){

		boolean lost = false;

		hero.move(currentMap, key);

		if(currentMap.getName() == "GuardMap"){
			guard.move(); 
		} 
		else if (currentMap.getName() == "OgreMap"){
			for(int i = 0; i < ogres.size(); i++){
				ogres.get(i).moveOgre(this);
				ogres.get(i).moveClub(this, ogres.get(i).getX(), ogres.get(i).getY());

			}
		}


		lost = checkPresence();

		if(lost){
			return 2;
		}

		if((hero.getY() == 0 && hero.getX() == 5) || (hero.getY() == 0 && hero.getX() == 6)){
			System.out.println("\n\nPhew! You escaped the guard!\nBut what's that?\nOh no! An ogre!\nGrab the key and escape!\nBe careful with his club!\n\n");
			setLevelTwo(0);
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
		} 

		else if(currentMap.getName() == "OgreMap"){
			for (int i = 0; i < ogres.size(); i++) {
				if(hero.getX() == ogres.get(i).getClubX() && hero.getY() == ogres.get(i).getClubY()){
					return true;
				}
				if((hero.getX() == ogres.get(i).getX() && hero.getY() == ogres.get(i).getY() + 1) || (hero.getX() == ogres.get(i).getX() && hero.getY() == ogres.get(i).getY() - 1)){
					ogres.get(i).stunOgre();
				} else if((hero.getY() == ogres.get(i).getY() && hero.getX() == ogres.get(i).getX() + 1) || (hero.getY() == ogres.get(i).getY() && hero.getX() == ogres.get(i).getX() - 1)){
					ogres.get(i).stunOgre();
				}

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

	public void setLevelTwo(int numOgres){
		currentMap = Map2;
		createHero(7, 1);
		hero.setSymbol('A');
		createOgres(numOgres);
	}

	public void createOgres(int numOgres) {

		int randomNumOgres = random.nextInt(4) + 1; 
		int x;
		int y;
		
		if(numOgres == 0){
			numOgres = randomNumOgres;
		}

		for(int i = 0; i < numOgres; i++){

			do{
				x = random.nextInt(7) + 1;
				y = random.nextInt(7) + 1;
			} while((x == hero.getX() && y == hero.getY()) || (x == hero.getX() - 1 && y == hero.getY()) || (x == hero.getX() && y == hero.getY() + 1));

			ogres.add(new Ogre(x,y));
		}
	}

	public void createOgre(int x, int y) {
		Ogre e = new Ogre(x,y);
		ogres.add(e);
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

	public void createCharacters(int level, String typeOfGuard, int i) {
		if(level == 1){
			createHero(1,1);
			createGuard(1, 8, typeOfGuard);
			this.currentMap = this.Map1;
		} else if (level == 2){
			setLevelTwo(i);
		}
	}
}
