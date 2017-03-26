package dkeep.logic;

/**
 * Handles the ogre's map
 * Extends the GameMap class
 * @author Diogo Dores, Pedro Reis
 *
 */

public class OgreMap extends GameMap{

	private String mapName = "OgreMap";

	static char[][] levelTwoMap = { 
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{ 'I',' ',' ',' ', ' ',' ',' ', 'k', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ', ' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ', ' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ', ' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ', ' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ', ' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ', ' ', 'X'},
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };
	
	@Override
	/**
	 * @return This returns the current map
	 */
	public char[][] getMap() {
		return levelTwoMap;
	}

	/**
	 * @return This returns the classe's name
	 */
	public String getName(){
		return mapName;
	}

	@Override
	/**
	 * Handle the hero's behavior with the key
	 * @param This is an object of the Hero class 
	 */
	public void activateLever(Hero hero) {
		hero.symbol = 'K';

	}

	/**
	 * @param game This is an object from the GameLogic class
	 * @return boolean This return true if the hero is past the level's doors
	 */
	@Override
	public boolean checkWin(GameLogic game) {
		return (game.hero.getY() == 0 && game.hero.getX() == 1);
	}

	/**
	 * Resets the hero's position
	 * @param game This is an object from the GameLogic class
	 */
	@Override
	public void resetPositions(GameLogic game) {
		game.hero.setX(7);
		game.hero.setY(1);
		
	}
	
	
}
