package dkeep.logic;

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
	public char[][] getMap() {
		return levelTwoMap;
	}

	public String getName(){
		return mapName;
	}

	@Override
	public void activateLever(Hero hero) {
		hero.symbol = 'K';

	}

	@Override
	public boolean checkWin(GameLogic game) {
		return (game.hero.getY() == 0 && game.hero.getX() == 1);
	}

	@Override
	public void resetPositions(GameLogic game) {
		game.hero.setX(7);
		game.hero.setY(1);
		
	}
	
	
}
