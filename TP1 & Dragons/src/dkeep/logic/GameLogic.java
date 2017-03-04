package dkeep.logic;

import java.util.Random;

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
			guard.followPath(); //Ainda não está a funcionar como eu quero
								//Faltam as personalidades diferentes,
								//Mas penso que já sei como implementar
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

	public void createGuard(){
		String type = "G";
		Random random = new Random();
		int r = random.nextInt(type.length());
		char typeOfGuard = type.charAt(r);

		if(typeOfGuard == 'G'){
			guard = new RookieGuard(1, 8);
		}
	}

	public boolean checkPresence() {

		if (guard.getY() == hero.getY() && (guard.getX() == hero.getX() - 1 || guard.getX() == hero.getX() + 1)) {
			return true;

		}
		else if (guard.getX() == hero.getX() && (guard.getY() == hero.getY() - 1 || guard.getY() == hero.getY() + 1)){
			return true;

		} else if (ogre.getY() == hero.getY() && (ogre.getX() == hero.getX() - 1 || ogre.getX() == hero.getX() + 1)) {
			return true;

		}
		else if (ogre.getX() == hero.getX() && (ogre.getY() == hero.getY() - 1 || ogre.getY() == hero.getY() + 1)){
			return true;

		}
		return false;
	}
	
	public void setLevelTwo(){
		currentMap = Map2;
		hero = new Hero(7, 1);
		ogre = new Ogre(1, 5);
	}

	public void createOgre(int x, int y) {
		ogre = new Ogre(x, y);
	}
}
