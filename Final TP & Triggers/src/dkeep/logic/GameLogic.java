package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the basic game logic
 */

public class GameLogic {

	Random random = new Random();
	public Guard guard = new Guard();
	public Hero hero = new Hero();
	public Ogre ogre = new Ogre();
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	public GameMap Map1 = new GuardMap(); 
	public GameMap Map2 = new OgreMap();
	public GameMap currentMap;

	/**
	 * Starts a game, and handles the movements of all characters, and returns the state of the game (playing, lost, won).
	 * @param key This is the character ('w', 'a', 's' or 'd') the user has inputed in the console
	 * @return int This returns the state of the game (if the hero proceeds to the next level or if it loses)
	 */

	public int startGame(char key){

		boolean lost = false;

		hero.move(this, key);

		if(currentMap.getName() == "GuardMap"){
			guard.move(); 
		} 
		if (currentMap.getName() == "OgreMap"){
			for(int i = 0; i < ogres.size(); i++){
				ogres.get(i).moveOgre(this, 'n');
				ogres.get(i).moveClub(this, ogres.get(i).getX(), ogres.get(i).getY());

			} 
		}

		lost = checkPresence();

		if(lost){
			return 2;
		}

		boolean hx = checkWin();
		if(hx) {
			if(currentMap.getName() == "GuardMap"){
				System.out.println("\n\nPhew! You escaped the guard!\nBut what's that?\nOh no! An ogre!\nGrab the key and escape!\nBe careful with his club!\n\n");
				setLevelTwo(0);
			}

			else if(currentMap.getName() == "OgreMap")
				return -1;
		}

		return 0;
	}

	/**
	 * Checks whether player was won the specific map.
	 * @return boolean This returns whether or not the hero has won the game
	 */

	public boolean checkWin() {
		if (currentMap.getName() == "TestMap" || currentMap.getName() == "OgreMap")
			return (hero.getX() == 0 && hero.getY() == 1);
		else if (currentMap.getName() == "GuardMap" )
			return (hero.getX() == 0 && (hero.getY() == 5 || hero.getY() == 6));
		else
			return false;
	}

	/**
	 * Changes the current level map
	 * @param gameMap This is the map that will be in use
	 */
	public void changeCurrentMap(GameMap gameMap){
		currentMap = gameMap;
	}

	
	/**
	 * Creates a new instance of the game's hero
	 * @param x This is the x coordinates
	 * @param y This is the y coordinates
	 */
	public void createHero(int x, int y){
		hero = new Hero(x, y);
	}

	/**
	 * Creates a new instance of the game's guard
	 * @param x This is the x coordinates
	 * @param y This is the y coordinates
	 * @param typeOfGuard This is the guard's personality, selected by the user
	 */
	public void createGuard(int x, int y, String typeOfGuard){		
		if(typeOfGuard == "Rookie"){
			guard = new RookieGuard(x, y);
		} else if(typeOfGuard == "Drunken"){
			guard = new DrunkenGuard(x, y); 
		} else if(typeOfGuard == "Suspicious"){
			guard = new SuspiciousGuard(x, y);
		}
	}

	/**
	 * Checks surroundings of enemies and compares them to hero's.
	 * @return boolean This returns whether or not the hero has lost the game.
	 */

	public boolean checkPresence() {

		if(currentMap.getName() == "GuardMap"){
			if(guard.symbol == 'G' && (hero.getX() == guard.getX()) && ((hero.getY() == guard.getY() + 1) || (hero.getY() == guard.getY() - 1))){
				return true;
			} else if(hero.getY() == guard.getY() && (hero.getX() == guard.getX() + 1 || hero.getX() == guard.getX() - 1)){
				return true;
			}
		} 

		else {
			for (int i = 0; i <= ogres.size()-1; i++) {
				if(hero.getX() == ogres.get(i).getClubX() && hero.getY() == ogres.get(i).getClubY()){
					return true;
				}
				else if (hero.getX() == ogres.get(i).getX() && hero.getY() == ogres.get(i).getY())
					return true;
				if((hero.getX() == ogres.get(i).getX() && hero.getY() == ogres.get(i).getY() + 1) || (hero.getX() == ogres.get(i).getX() && hero.getY() == ogres.get(i).getY() - 1)){
					ogres.get(i).stunOgre();
				} else if((hero.getY() == ogres.get(i).getY() && hero.getX() == ogres.get(i).getX() + 1) || (hero.getY() == ogres.get(i).getY() && hero.getX() == ogres.get(i).getX() - 1)){
					ogres.get(i).stunOgre();
				}

			}
		}

		/*else {
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

		}*/
		return false;

	}

	/**
	 * Player won first level.
	 * @param numOgres This is the number of Ogres to be set up for the next level
	 */

	public void setLevelTwo(int numOgres){
		currentMap = Map2;
		createHero(7, 1);
		hero.setSymbol('A');
		createOgres(numOgres);
	}

	/**
	 * Randomly creates Ogres in the scene.
	 * @param numOgres This is the number of Ogres to be set up for the next level
	 */

	public void createOgres(int numOgres) {

		int randomNumOgres = random.nextInt(4) + 1; 
		int x; 
		int y;

		if(numOgres == 0){
			numOgres = randomNumOgres;
		}
		boolean condition;

		for(int i = 0; i < numOgres; i++){
			do{
				x = random.nextInt(7) + 1;
				y = random.nextInt(7) + 1;
				condition = checkSpawnCondition(x, y);
			} while(condition);

			ogres.add(new Ogre(x,y, 9, 9));
		}
	}

	/**
	 * Checks whether spawn isn't an insta-lose for the player.
	 * @param x This is the x coordinate
	 * @param y This is the y coordinate
	 * @return boolean This returns whether or not the ogre can spawn in a certain position
	 */
	
	public boolean checkSpawnCondition(int x, int y) {
		boolean condition;
		condition = (x == hero.getX() && y == hero.getY());
		condition = condition || (x == hero.getX() - 1 && y == hero.getY()) ;
		condition = condition || (x == hero.getX() && y == hero.getY() + 1); 
		return condition;
	}

	/**
	 * Creates one ogre and adds it to an ArrayList.
	 * @param x This is the x coordinate
	 * @param y This is the y coordinate
	 * @param width This is the width of the map
	 * @param height This is the height of the map
	 */
	public void createOgre(int x, int y, int width, int height) {
		Ogre e = new Ogre(x,y, width, height);
		ogres.add(e);
	}

	/**
	 * Creates a new instance of the game's guard
	 * @param x This is the x coordinates
	 * @param y This is the y coordinates
	 * @return char This is the guard's personality, that was randomly selected
	 */
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

	/**
	 * Creates a new instance of every living character in the game
	 * @param level This is the current level
	 * @param typeOfGuard This is the guard's personality
	 * @param i This is the number of ogres to be spawned
	 */
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
