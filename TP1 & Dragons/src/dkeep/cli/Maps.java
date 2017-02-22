package dkeep.cli;

public class Maps {
	
	int column = 10;
	int row = 10;
	private final char WALL = 'X';
	private final char HERO = 'H';
	private final char GUARD = 'G';
	private final char OGRE = '0';
	private final char KEY = 'k';
	private final char DOOR = 'I';
	private final char STAIRS = 'S';
	private final char EMPTY = ' ';
	
	public static char[][] activateLever(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == 'I')
					a[i][j] = 'S';
			}
		}
		return a;
	}
		
	char[][] levelOneMap = { 
			{ WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL },
			{ WALL, HERO, EMPTY, EMPTY, DOOR, EMPTY, WALL, EMPTY, GUARD, WALL },
			{ WALL, WALL, WALL, EMPTY , WALL, WALL, WALL, EMPTY , EMPTY , WALL },
			{ WALL,EMPTY, DOOR,EMPTY, DOOR,EMPTY, WALL,EMPTY,EMPTY, WALL },
			{ WALL, WALL, WALL, EMPTY, WALL, WALL, WALL, EMPTY, EMPTY, WALL },
			{ DOOR, EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , WALL },
			{ DOOR, EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , EMPTY , WALL },
			{ WALL, WALL, WALL,EMPTY, WALL, WALL, WALL, WALL, EMPTY , WALL },
			{ WALL, EMPTY , 'I', EMPTY , 'I', EMPTY , WALL, KEY, EMPTY , WALL },
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
