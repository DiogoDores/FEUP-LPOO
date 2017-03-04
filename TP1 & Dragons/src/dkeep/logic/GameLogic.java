package dkeep.logic;

import java.util.Random;
import dkeep.cli.*;

public class GameLogic {

	public Guard guard = new Guard();
	public Hero hero = new Hero();
	public GameMap Map1 = new GuardMap();
	public GameMap Map2 = new OgreMap();
	public GameMap currentMap;

	public int startGame(char key){

		boolean lost = false;
		
		hero.move(currentMap, key);
		guard.followPath(); //Ainda não está a funcionar como eu quero
							//Faltam as personalidades diferentes,
							//Mas penso que já sei como implementar
		
		lost = checkPresence();
		
		if(lost){
			return -1;
		}
		
		if(hero.getY() == 0 && (hero.getX() == 5 || hero.getX() == 6)){
			currentMap = Map2;
			hero = new Hero(7, 1);
			//Falta inicializar o ogre e apagar o guarda
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

	public void drawMap() {

		char[][] mapToDraw = currentMap.getMap();

		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){
				if(guard.getX() == i && guard.getY() == j){
					System.out.print(guard.getSymbol());
				}else if(hero.getX() == i && hero.getY() == j){
					System.out.print(hero.getSymbol());
				} else {
					System.out.print(mapToDraw[i][j]);
				}
			}
			System.out.print("\n");
		}
	}

	public boolean checkPresence() {

		if (guard.getY() == hero.getY() && (guard.getX() == hero.getX() - 1 || guard.getX() == hero.getX() + 1)) {
			return true;

		}
		else if (guard.getX() == hero.getX() && (guard.getY() == hero.getY() - 1 || guard.getY() == hero.getY() + 1)){
			return true;

		}
		return false;
	}
	
	public void setLevelTwo(){
		currentMap = Map2;
		hero = new Hero(7, 1);
	}
}
