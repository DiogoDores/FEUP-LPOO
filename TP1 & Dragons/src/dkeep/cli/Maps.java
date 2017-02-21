package dkeep.cli;

public class Maps {
	
	int column = 10;
	int row = 10;
	private final char WALL = 'X';
	private final char HERO = 'H';
	private final char GUARD = 'G';
	private final char OGRE = '0';
	private final char LEVER = 'K';
	private final char KEY = 'k';
	private final char DOOR = 'I';
	private final char STAIRS = 'S';
	private final char EMPTY = ' ';

	char[][] levelOneMap = { 
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL },
			{ WALL, HERO, EMPTY, EMPTY, DOOR, EMPTY, WALL, EMPTY, GUARD, WALL },
			{ WALL, WALL, WALL, EMPTY , WALL, WALL, WALL, EMPTY , EMPTY , WALL },
			{ WALL,EMPTY, DOOR,EMPTY, DOOR,EMPTY, WALL,EMPTY,EMPTY, WALL },
			{ WALL, WALL, WALL, EMPTY, WALL, WALL, WALL, EMPTY, EMPTY, WALL },
			{ DOOR, EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , WALL },
			{ DOOR, EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , WALL },
			{ WALL, WALL, WALL,EMPTY, WALL, WALL, WALL, WALL, EMPTY , WALL },
			{ WALL, EMPTY , 'I', EMPTY , 'I', EMPTY , WALL, LEVER, EMPTY , WALL },
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL } };

	char[][] levelTwoMap = { 
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL },
			{ DOOR,EMPTY,EMPTY,EMPTY, OGRE,EMPTY,EMPTY, KEY, WALL },
			{ WALL,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY, WALL },
			{ WALL,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY, WALL },
			{ WALL,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY, WALL },
			{ WALL,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY, WALL },
			{ WALL,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY, WALL },
			{ WALL, HERO ,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY, WALL },
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL } };
	
	public char[][] getLevelOneMap(){
		return levelOneMap;
	}
	
	public char[][] getLevelTwoMap(){
		return levelTwoMap;
	}

}
