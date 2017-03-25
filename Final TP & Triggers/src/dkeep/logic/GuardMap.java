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

	public String drawMap(GameLogic game) {
		
		char[][] mapToDraw = levelOneMap;
		String map = "";

		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){
				if(game.guard.getX() == i && game.guard.getY() == j){
					System.out.print(game.guard.getSymbol() + " ");
					map += game.guard.getSymbol() + " ";
				}else if(game.hero.getX() == i && game.hero.getY() == j){
					System.out.print(game.hero.getSymbol() + " ");
					map += game.hero.getSymbol() + " ";
				} else {
					System.out.print(mapToDraw[i][j] + " ");
					map += mapToDraw[i][j] + " ";
				}
			}
			System.out.print("\n");
			map += "\n";
		}
		
		return map;
	}

	@Override
	public String getName() {
		return mapName;
	}

	


	@Override
	public boolean checkWin(GameLogic game) {
		return (game.hero.getY() == 0 && (game.hero.getX() == 5  || game.hero.getX() == 6));
	}



	@Override
	public void resetPositions(GameLogic game) {
		// TODO Auto-generated method stub
		
	}
	
}
