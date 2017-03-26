 package dkeep.gui;

import java.awt.image.BufferedImage;

public class Assets {
	/**
	 * Initializes all assets. 
	 */
	public static final int width = 127, height = 127, heroWidth = 163, heroHeight = 163, wallWidth = 24, wallHeight = 24;

	public static BufferedImage 
	guardFront, guardLeft, guardRight, guardBack,
	sGuardFront, sGuardLeft, sGuardRight, sGuardBack,
	ogreFront, ogreLeft, ogreRight, ogreBack,
	sOgreFront, sOgreLeft, sOgreRight,
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
		SpriteSheet sGuardSheet = new SpriteSheet(ImageLoader.loadImage("/GuardAsleep.png"));
		SpriteSheet sOgreSheet = new SpriteSheet(ImageLoader.loadImage("/MechaAsleep.png"));
		
		
		heroFront = heroSheet.crop(0, 0, heroWidth, heroHeight);
		heroBack = heroSheet.crop(0, heroHeight, heroWidth, heroHeight);
		heroLeft = heroSheet.crop(heroWidth, 0, heroWidth, heroHeight);
		heroRight = heroSheet.crop(heroWidth*2, 0, heroWidth, heroHeight);

		guardFront = guardSheet.crop(0, 0, width, height);
		guardLeft = guardSheet.crop(0, height, width, height);
		guardRight = guardSheet.crop(width*2, height, width, height);
		guardBack = guardSheet.crop(0, height*2, width, height);
		
		sGuardFront = sGuardSheet.crop(0, 0, width, height);
		sGuardLeft = sGuardSheet.crop(0, height, width, height);
		sGuardRight = sGuardSheet.crop(width*2, height, width, height);
		sGuardBack = sGuardSheet.crop(0, height*2, width, height);
		
		ogreFront = mechaSheet.crop(0, 0, heroWidth, heroHeight);
		ogreBack = mechaSheet.crop(0, heroHeight, heroWidth, heroHeight);
		ogreLeft = mechaSheet.crop(heroWidth, 0, heroWidth, heroHeight);
		ogreRight = mechaSheet.crop(heroWidth*2, 0, heroWidth, heroHeight);
		
		sOgreFront = sOgreSheet.crop(0, 0, heroWidth, heroHeight);
		sOgreLeft = sOgreSheet.crop(heroWidth, 0, heroWidth, heroHeight);
		sOgreRight = sOgreSheet.crop(heroWidth*2, 0, heroWidth, heroHeight);
		
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
