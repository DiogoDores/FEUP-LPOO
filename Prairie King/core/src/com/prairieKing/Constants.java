package com.prairieKing;


import com.badlogic.gdx.Gdx;

public class Constants {
    public final static short CATEGORY_PLAYER = 0x0001;  // 0000000000000001 in binary
    public final static short CATEGORY_ENEMY = 0x0002; // 0000000000000010 in binary
    public final static short CATEGORY_PROJECTILE = 0x0004; // 0000000000000100 in binary
    public final static short CATEGORY_POWERUP = 0x0008; // 0000000000000100 in binary

    public final static short MASK_PLAYER = ~(CATEGORY_PROJECTILE);
    public final static short MASK_ENEMY = CATEGORY_PLAYER | CATEGORY_PROJECTILE;
    public final static short MASK_PROJECTILE = (short) ~(CATEGORY_PROJECTILE);
    public final static short MASK_POWERUP = CATEGORY_PLAYER;

    public static final float RATIO = ((float) Gdx.graphics.getHeight())/((float) Gdx.graphics.getWidth());

    public static final float HEART_WIDTH = PrairieKing.PPM/13;
    public static final float HERO_WIDTH = PrairieKing.PPM/17;
    public static final float ENEMY_WIDTH = PrairieKing.PPM/17;
    public static final float PROJECTILE_WIDTH = PrairieKing.PPM/36;
    public static final float POWERUP_WIDTH = PrairieKing.PPM/18;

}
