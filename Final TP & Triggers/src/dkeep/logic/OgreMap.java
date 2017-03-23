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
	public String drawMap(GameLogic game) {

		char[][] mapToDraw = levelTwoMap;
		String map = "";
		System.out.print("X ");
		//map += 'X' + " ";

		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){
				//	if (i != 0 && j != 0) {
				boolean foundOgre = false;
				boolean foundClub = false;

				for(int k = 0; k < game.ogres.size(); k++){

					if(!foundOgre && !foundClub && (game.currentMap.getMap()[game.ogres.get(k).getX()][game.ogres.get(k).getY()] != 'O') && (game.ogres.get(k).getX() == i && game.ogres.get(k).getY() == j)){
						System.out.print(game.ogres.get(k).getSymbol() + " ");
						map += game.ogres.get(k).getSymbol() + " ";
						foundOgre = true;
						continue;
					}
					if(!foundClub && !foundOgre && (game.ogres.get(k).getClubX() == i && game.ogres.get(k).getClubY() == j) && (game.currentMap.getMap()[game.ogres.get(k).getClubX()][game.ogres.get(k).getClubY()] != '*')){
						System.out.print(game.ogres.get(k).getClubSymbol() + " ");
						map += game.ogres.get(k).getClubSymbol() + " ";
						foundClub = true;
						continue;
					}
				}

				if (!(i== 0 && j == 0)) {
					if(!foundOgre && !foundClub){
						if(game.hero.getX() == i && game.hero.getY() == j){
							System.out.print(game.hero.getSymbol() + " ");
							map += game.hero.getSymbol() + " ";
							continue;
						} else {
							System.out.print(mapToDraw[i][j] + " ");
							map += mapToDraw[i][j] + " ";
						}
					}
				}
				//}
			}
			System.out.print("\n");
			map += "\n";
		}
		return map;
	}

	@Override
	public char possibleMove(int x, int y) {
		if (getMap()[x][y]  == 'X'){
			return 'X';
		} else if (getMap()[x][y] == 'k') {
			return 'E';
		} else if (getMap()[x][y] == ' '){
			return 'H';
		} else if (getMap()[x][y] == 'I'){
			return 'I';
		} else if (getMap()[x][y] == 'S') {
			return 'S';
		} else if (getMap()[x][y] == 'H') // AC
			return 'D';
		return levelTwoMap[x][y];
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
