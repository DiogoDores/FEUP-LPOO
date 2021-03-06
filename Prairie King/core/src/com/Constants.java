package com;


import com.badlogic.gdx.Gdx;

/** Class housing all static constants to be accessed by most of the other classes.
 */
public class Constants {
    public final static short CATEGORY_PLAYER = 0x0001;
    public final static short CATEGORY_ENEMY = 0x0002;
    public final static short CATEGORY_PROJECTILE = 0x0004;
    public final static short CATEGORY_POWERUP = 0x0008;

    public final static short MASK_PLAYER = ~(CATEGORY_PROJECTILE);
    public final static short MASK_ENEMY = CATEGORY_PLAYER | CATEGORY_PROJECTILE;
    public final static short MASK_PROJECTILE = (short) ~(CATEGORY_PROJECTILE);
    public final static short MASK_POWERUP = CATEGORY_PLAYER;
    public final static short MASK_EVERYTHING = 0;

    public static final float RATIO = ((float) Gdx.graphics.getHeight())/((float) Gdx.graphics.getWidth());
    public static final float DELAY_TIME_ON_COLLISION_WITH_HERO = 1;

    public static final float HEART_WIDTH = PrairieKing.PPM/13;
    public static final float HERO_WIDTH = PrairieKing.PPM/17;
    public static final float ENEMY_WIDTH = PrairieKing.PPM/17;
    public static final float PROJECTILE_WIDTH = PrairieKing.PPM/50;
    public static final float POWERUP_WIDTH = PrairieKing.PPM/18;

}
