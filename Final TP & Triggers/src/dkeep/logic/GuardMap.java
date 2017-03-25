package dkeep.logic;

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
	
	

	public void activateLever(Hero hero) {
		for (int i = 0; i < levelOneMap.length; i++) {
			for (int j = 0; j < levelOneMap[i].length; j++) {
				if (levelOneMap[i][j] == 'I')
					levelOneMap[i][j] = 'S';
			}
		}
	}
	



	@Override
	public char[][] getMap() {
		return levelOneMap;
	}

	@Override
	public String getName() {
		return mapName;
	}

	


	@Override
	public boolean checkWin(GameLogic game) {
		return (game.hero.getX() == 0 && (game.hero.getY() == 5  || game.hero.getY() == 6));
	}



	@Override
	public void resetPositions(GameLogic game) {
		// TODO Auto-generated method stub
		
	}
	
}
