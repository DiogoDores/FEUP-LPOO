 package dkeep.gui;

import java.awt.image.BufferedImage;

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
	club,
	upArrow, downArrow, rightArrow, leftArrow;

	public static BufferedImage[][] structures = new BufferedImage[128][128];

	public static void init(){
		SpriteSheet guardSheet = new SpriteSheet(ImageLoader.loadImage("/Guard.png"));
		SpriteSheet heroSheet = new SpriteSheet(ImageLoader.loadImage("/Hero.png"));
		SpriteSheet wallSheet = new SpriteSheet(ImageLoader.loadImage("/Structures.png"));
		SpriteSheet mechaSheet = new SpriteSheet(ImageLoader.loadImage("/Mecha.png"));
		SpriteSheet arrowSheet = new SpriteSheet(ImageLoader.loadImage("/Arrows.png"));
		
		heroFront = heroSheet.crop(0, 0, width, height);
		heroLeft = heroSheet.crop(0, height, width, height);
		heroRight = heroSheet.crop(width*2, height, width, height);
		heroBack = heroSheet.crop(0, height*2, width, height);

		guardFront = guardSheet.crop(0, 0, width, height);
		guardLeft = guardSheet.crop(0, height, width, height);
		guardRight = guardSheet.crop(width*2, height, width, height);
		guardBack = guardSheet.crop(0, height*2, width, height);
		
		ogreFront = mechaSheet.crop(0, 0, width, height);
		ogreLeft = mechaSheet.crop(0, height, width, height);
		ogreRight = mechaSheet.crop(width*2, height, width, height);
		ogreBack = mechaSheet.crop(0, height*2, width, height);
		
		club = wallSheet.crop(wallWidth * 4, wallHeight, wallWidth, wallHeight);

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
		
		key = wallSheet.crop(wallWidth * 4, wallHeight * 2, wallWidth, wallHeight);

		door = wallSheet.crop(wallWidth * 3, 0, wallWidth, wallHeight);
		openDoor = wallSheet.crop(wallWidth * 5, 0, wallWidth, wallHeight);
		
		upArrow = arrowSheet.crop(0, 0, wallWidth, wallHeight);
		downArrow = arrowSheet.crop(0, wallHeight, wallWidth, wallHeight);
		rightArrow = arrowSheet.crop(wallWidth, wallHeight, wallWidth, wallHeight);
		leftArrow = arrowSheet.crop(wallWidth, 0, wallWidth, wallHeight);
		
	}
}
