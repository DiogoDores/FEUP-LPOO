package dkeep.gui;

import java.awt.image.BufferedImage;

import dkeep.logic.GameMap;

public class Assets {

	public static final int width = 129, height = 163, wallWidth = 24, wallHeight = 24;

	public static BufferedImage 
	guardFront, guardLeft, guardRight, guardBack,
	ogreFront, ogreLeft, ogreRight, ogreBack,
	heroFront, heroLeft, heroRight, heroBack,
	topWall, leftWall, rightWall, bottomWall, 
	topLeftWall, topRightWall, bottomLeftWall, bottomRightWall, wall,
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
		wall = wallSheet.crop(wallWidth * 4, 0, wallWidth, wallHeight);

		closedLever = wallSheet.crop(wallWidth * 3, wallHeight, wallWidth, wallHeight);
		openLever = wallSheet.crop(wallWidth * 3, wallHeight*2, wallWidth, wallHeight);

		door = wallSheet.crop(wallWidth * 3, 0, wallWidth, wallHeight);
		openDoor = wallSheet.crop(wallWidth * 4, wallHeight, wallWidth, wallHeight);

	}
}
