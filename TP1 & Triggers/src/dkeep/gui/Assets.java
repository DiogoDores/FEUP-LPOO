package dkeep.gui;

import java.awt.image.BufferedImage;

import dkeep.logic.GameMap;

public class Assets {

	private static final int width = 129, height = 163, wallWidth = 24, wallHeight = 24;

	public static BufferedImage 
	guardFront, guardLeft, guardRight, guardBack,
	ogreFront, ogreLeft, ogreRight, ogreBack,
	heroFront, heroLeft, heroRight, heroBack,
	topWall, leftWall, rightWall, bottomWall, 
	topLeftWall, topRightWall, bottomLeftWall, bottomRightWall,
	floor,
	door, openDoor,
	closedLever, openLever,
	key,
	club;

	public static BufferedImage[][] structures = new BufferedImage[128][128];

	public static void init(GameMap map){
		SpriteSheet guardSheet = new SpriteSheet(ImageLoader.loadImage("/Guard.png"));
		SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/Hero.png"));
		SpriteSheet wallSheet = new SpriteSheet(ImageLoader.loadImage("/Structures.png"));

		heroFront = heroSheet.crop(0, 0, width, height);
		heroLeft = heroSheet.crop(0, height, width, height);
		heroRight = heroSheet.crop(width*2, height, width, height);
		heroBack = heroSheet.crop(0, height*2, width, height);

		guardFront = guardSheet.crop(0, 0, width, height);
		guardLeft = guardSheet.crop(0, height, width, height);
		guardRight = guardSheet.crop(width*2, height, width, height);
		guardBack = guardSheet.crop(0, height*2, width, height);

		floor = wallSheet.crop(wallWidth, wallHeight, wallWidth, wallHeight);

		topWall = wallSheet.crop(wallWidth, 0, wallWidth, wallHeight);
		leftWall = wallSheet.crop(0, wallHeight, wallWidth, wallHeight);
		rightWall = wallSheet.crop(wallWidth*2, wallHeight, wallWidth, wallHeight);
		bottomWall = wallSheet.crop(wallWidth, wallHeight*2, wallWidth, wallHeight);
		topLeftWall = wallSheet.crop(0, 0, wallWidth, wallHeight);
		topRightWall = wallSheet.crop(wallWidth*2, 0, wallWidth, wallHeight);
		bottomLeftWall = wallSheet.crop(0, wallHeight*2, wallWidth, wallHeight);
		bottomRightWall = wallSheet.crop(wallWidth*2, wallHeight*2, wallWidth, wallHeight);
		
		closedLever = wallSheet.crop(wallWidth * 3, wallHeight, wallWidth, wallHeight);
		openLever = wallSheet.crop(wallWidth * 3, wallHeight*2, wallWidth, wallHeight);
		
		door = wallSheet.crop(wallWidth * 3, 0, wallWidth, wallHeight);
		
		setStructures(map);

	}

	public static void setStructures(GameMap map){

		char[][] mapToDraw = map.getMap();

		for (int y = 0; y < mapToDraw.length; y++) {
			for(int x = 0; x < mapToDraw[y].length; x++){
				if(mapToDraw[y][x] == 'X'){
					if(x == 0){
						if(y == 0)
							structures[y][x] = topLeftWall;
						else if(y == mapToDraw.length - 1)
							structures[y][x] = topRightWall;
						else 
							structures[y][x] = topWall;
					} else if(x == mapToDraw[y].length - 1){
						if(y == 0)
							structures[y][x] = bottomLeftWall;
						else if(y == mapToDraw.length -1)
							structures[y][x] = bottomRightWall;
						else 
							structures[y][x] = bottomWall;
					} else if (y == 0){
						structures[y][x] = leftWall;
					} else if (y == mapToDraw.length - 1){
						structures[y][x] = rightWall;
					} else {
						structures[x][y] = rightWall;
					}
				} else if (mapToDraw[y][x] == ' '){
					structures[x][y] = floor;
				} else if (mapToDraw[y][x] == 'k'){
					structures[x][y] = closedLever;
				} else if (mapToDraw[y][x] == 'I'){
					structures[x][y] = door;
				} else {
					structures[y][x] = openLever;
				}
			}
		}
	}

}
