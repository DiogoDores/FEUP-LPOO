package dkeep.logic;

public class GuardMap implements GameMap {

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

	public void activateLever(Hero hero) {
		for (int i = 0; i < levelOneMap.length; i++) {
			for (int j = 0; j < levelOneMap[i].length; j++) {
				if (levelOneMap[i][j] == 'I')
					levelOneMap[i][j] = 'S';
			}
		}
	}

	@Override
	public char possibleMove(int x, int y) {
		
		//Era o antigo next

		if (levelOneMap[x][y]  == 'X'){
			return 'X';
		} else if (levelOneMap[x][y] == 'k') {
			return 'E';
		} else if (levelOneMap[x][y] == ' '){
			return 'H';
		} else if (levelOneMap[x][y] == 'I'){
			return 'X';
		} else if (levelOneMap[x][y] == 'S') {
			return 'S';
		} else if (levelOneMap[x][y] == 'H') // AC
			return 'D';
		return levelOneMap[x][y];
	}

	@Override
	public char[][] getMap() {
		return levelOneMap;
	}

}
