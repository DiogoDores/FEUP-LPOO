package dkeep.logic;

public abstract class GameMap { 
	
	public abstract char possibleMove(int x, int y);
	
	public void activateLever(Hero hero) {
	}
	public String drawMap(GameLogic game) {
		return null;
	}
	public abstract String getName();
	public abstract char[][] getMap();
	
	public abstract boolean checkWin(GameLogic game);
	public abstract void resetPositions(GameLogic game);
	public char possibleMove(int x, int y, GameLogic game) { 
		for (int i = 0; i < game.ogres.size(); i++) {
			if (game.ogres.get(i).getX() == x && game.ogres.get(i).getY() == y )
				return 'O';

		}

		if (game.currentMap.getMap()[x][y]  == 'X'){
			return 'X';
		} else if (game.currentMap.getMap()[x][y] == 'k') {
			return 'E';
		} else if (game.currentMap.getMap()[x][y] == ' '){
			return 'H';
		} else if (game.currentMap.getMap()[x][y] == 'I'){
			return 'I';
		} else if (game.currentMap.getMap()[x][y] == 'S') {
			return 'S';
		} else if (game.currentMap.getMap()[x][y] == 'H') // AC
			return 'D';
		return 'H';

	}
}
