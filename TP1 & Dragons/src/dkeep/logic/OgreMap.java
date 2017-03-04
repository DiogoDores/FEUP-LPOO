package dkeep.logic;

public class OgreMap implements GameMap{
	
	static char[][] levelTwoMap = { 
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I',' ',' ',' ', ' ',' ',' ','k', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ',' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ',' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ',' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ',' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ',' ', 'X'},
			{ 'X',' ',' ',' ',' ',' ',' ',' ', 'X'},
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	@Override
	public char possibleMove(int x, int y) {
		
		if (levelTwoMap[x][y]  == 'X'){
			return 'X';
		} else if (levelTwoMap[x][y] == 'k') {
			return 'E';
		} else if (levelTwoMap[x][y] == ' '){
			return 'H';
		} else if (levelTwoMap[x][y] == 'I'){
			return 'X';
		} else if (levelTwoMap[x][y] == 'S') {
			return 'S';
		} else if (levelTwoMap[x][y] == 'H') // AC
			return 'D';
		return levelTwoMap[x][y];
	}

	@Override
	public char[][] getMap() {
		return levelTwoMap;
	}

	@Override
	public void activateLever(Hero hero) {
		hero.symbol = 'K';
		
	}
	
}
