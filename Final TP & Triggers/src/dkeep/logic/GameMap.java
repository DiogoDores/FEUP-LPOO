package dkeep.logic;

public abstract class GameMap { 
	
	public void activateLever(Hero hero) {}
	public String drawMap(GameLogic game) {
		return null;
	}
	public abstract String getName();
	public abstract char[][] getMap();
	
	public abstract boolean checkWin(GameLogic game);
	public abstract void resetPositions(GameLogic game);
	
	/**
	 * Evaluates what char is present in those coordinates.
	 * Is aware of other game components.
	 */
	
	public char possibleMove(int x, int y, GameLogic game) {
		for (int i = 0 ; i < game.ogres.size(); i++) {
			if (game.ogres.get(i).getX() == x && game.ogres.get(i).getY() == y)
				return 'O';
		}
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
		return ' ';
	}

}
