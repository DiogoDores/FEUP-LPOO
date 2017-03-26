package dkeep.logic;

/**
 * Abstract class that handles all the maps created for the game
 */

public abstract class GameMap { 
	
	public void activateLever(Hero hero) {}
	public abstract String getName();
	public abstract char[][] getMap();
	
	public abstract boolean checkWin(GameLogic game);
	public abstract void resetPositions(GameLogic game);
	
	/**
	 * Evaluates what char is present in those coordinates.
	 * Is aware of other game components.
	 * @param x This is the x coordinate
	 * @param y This is the y coordinate
	 * @param game This is an object of the GameLogic class
	 * @return char This returns a char representative of a character's next move position
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
	
	/** 
	 * Draws the current game map
	 * @param game This is an object of the GameLogic class
	 * @return String This returns the game map written in a string
	 */
	public String drawMap(GameLogic game) {
		char[][] mapToDraw = game.currentMap.getMap();
		String map = "";
		
		boolean hasGuard = false;
		boolean hasDrawnGuard = false;

		if (game.guard.getX() >= 0)
			hasGuard = true;
		
		
		for (int i = 0; i < mapToDraw.length; i++) {
			for(int j = 0; j < mapToDraw[i].length; j++){

				boolean foundOgre = false;
				boolean foundClub = false;

				if(game.guard.getX() == i && game.guard.getY() == j){
					System.out.print(game.guard.getSymbol() + " ");
					map += game.hero.getSymbol() + " ";
					hasDrawnGuard = true;
				}
				for(int k = 0; k < game.ogres.size(); k++){

					if(!foundOgre && (game.currentMap.getMap()[game.ogres.get(k).getX()][game.ogres.get(k).getY()] != 'O') && (game.ogres.get(k).getX() == i && game.ogres.get(k).getY() == j)){
						System.out.print(game.ogres.get(k).getSymbol() + " ");
						map += game.ogres.get(k).getSymbol() + " ";
						foundOgre = true;
						continue;
					}
					if(!foundClub && (game.ogres.get(k).getClubX() == i && game.ogres.get(k).getClubY() == j) && (game.currentMap.getMap()[game.ogres.get(k).getClubX()][game.ogres.get(k).getClubY()] != '*')){
						System.out.print(game.ogres.get(k).getClubSymbol() + " ");
						map += game.ogres.get(k).getClubSymbol() + " ";
						foundClub = true;
						continue;
					}
				}

				if(!foundOgre && !foundClub && !hasDrawnGuard){
					if(game.hero.getX() == i && game.hero.getY() == j){
						System.out.print(game.hero.getSymbol() + " ");
						map += game.hero.getSymbol() + " ";
						continue;
					} else {
						System.out.print(mapToDraw[i][j] + " ");
						map += mapToDraw[i][j] + " ";
					}
				}
				hasDrawnGuard = false;
			}
			System.out.print("\n");
			map += "\n";
		}
		
		return map;
	
	}

}
