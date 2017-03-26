package dkeep.logic;

/**
 * Handles the Guard's level
 * Extends GameMap class
 * @author Diogo Dores, Pedro Reis
 *
 */

public class GuardMap extends GameMap {
	
	private String mapName = "GuardMap";
	 
	static char[][] levelOneMap = { 
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ' , 'X', 'X', 'X', ' ' , ' ' , 'X' },
			{ 'X',' ', 'I',' ', 'I',' ', 'X',' ',' ', 'X' }, 
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , 'X' },
			{ 'I', ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , 'X' },
			{ 'X', 'X', 'X',' ', 'X', 'X', 'X', 'X', ' ' , 'X' },
			{ 'X', ' ' , 'I', ' ' , 'I', ' ' , 'X', 'k', ' ' , 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	

	/**
	 * Opens all the map's doors
	 *@param hero This is an object from the Hero class
	 */
	public void activateLever(Hero hero) {
		for (int i = 0; i < levelOneMap.length; i++) {
			for (int j = 0; j < levelOneMap[i].length; j++) {
				if (levelOneMap[i][j] == 'I')
					levelOneMap[i][j] = 'S';
			}
		}
	}
	


	/**
	 * @return char[][] This returns the guard's map
	 */
	@Override
	public char[][] getMap() {
		return levelOneMap;
	}

	/**
	 * @return String This returns the classe's name
	 */
	@Override
	public String getName() {
		return mapName;
	}

	

	/**
	 * @param game This is an object from the GameLogic class
	 * @return boolean This return true if the hero is past the level's doors
	 */
	@Override
	public boolean checkWin(GameLogic game) {
		return (game.hero.getY() == 0 && (game.hero.getX() == 5  || game.hero.getX() == 6));
	}



	@Override
	public void resetPositions(GameLogic game) {
		game.hero.setX(1);
		game.hero.setY(1);
		
		game.guard.x = 1;
		game.guard.y = 8;
	}
	
}
