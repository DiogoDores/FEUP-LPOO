package dkeep.logic;

public interface GameMap {

	public char possibleMove(int x, int y);
	public void activateLever(Hero hero);
	public void drawMap(GameLogic game);
	public String getName();
	public char[][] getMap();

}
