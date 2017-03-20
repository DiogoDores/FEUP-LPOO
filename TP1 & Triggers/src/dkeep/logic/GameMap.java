package dkeep.logic;

public interface GameMap {
	
	public char possibleMove(int x, int y);
	public char possibleMove(int x, int y, GameLogic game); // Quando � preciso averiguar posi��es do GameLogic

	public void activateLever(Hero hero);
	public String drawMap(GameLogic game);
	public String getName();
	public char[][] getMap();
}
