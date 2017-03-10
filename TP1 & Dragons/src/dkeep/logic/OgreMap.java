package dkeep.logic;

public class OgreMap implements GameMap{

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
	public char possibleMove(int x, int y) { 

		if (levelTwoMap[x][y]  == 'X'){
			return 'X';
		} else if (levelTwoMap[x][y] == 'k') {
			return 'E';
		} else if (levelTwoMap[x][y] == ' '){
			return 'H';
		} else if (levelTwoMap[x][y] == 'I'){
			return 'I';
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

	public String getName(){
		return mapName;
	}

	@Override
	public void activateLever(Hero hero) {
		hero.symbol = 'K';

	}

	@Override
	public void drawMap(GameLogic game) {

		char[][] mapToDraw = levelTwoMap;

		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){

				boolean foundOgre = false;

				for(int k = 0; k < game.ogres.size(); k++){

					if(game.ogres.get(k).getX() == i && game.ogres.get(k).getY() == j){
						System.out.print(game.ogres.get(k).getSymbol() + " ");
						foundOgre = true;
						continue;
					} else if(game.ogres.get(k).getClubX() == i && game.ogres.get(k).getClubY() == j){
						System.out.print(game.ogres.get(k).getClubSymbol() + " ");
						foundOgre = true;
						continue;
					}
				}

				if(!foundOgre){
					if(game.hero.getX() == i && game.hero.getY() == j){
						System.out.print(game.hero.getSymbol() + " ");
						continue;
					} else {
						System.out.print(mapToDraw[i][j] + " ");
					}
				}
			}
			System.out.print("\n");
		}
	}
}
