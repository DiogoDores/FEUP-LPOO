package com.prairieKing;


import com.prairieKing.controller.PrairieKing;

public class Constants {
    public final static short CATEGORY_PLAYER = 0x0001;  // 0000000000000001 in binary
    public final static short CATEGORY_ENEMY = 0x0002; // 0000000000000010 in binary
    public final static short CATEGORY_PROJECTILE = 0x0004; // 0000000000000100 in binary
    public final static short CATEGORY_POWERUP = 0x0008; // 0000000000000100 in binary

    public final static short MASK_PLAYER = CATEGORY_ENEMY | CATEGORY_POWERUP;
    public final static short MASK_ENEMY = CATEGORY_PLAYER | CATEGORY_PROJECTILE;
    public final static short MASK_PROJECTILE = (short) 0xFFFF;//~(CATEGORY_PLAYER | CATEGORY_PROJECTILE);
    public final static short MASK_POWERUP = CATEGORY_PLAYER;

    public static final float HERO_WIDTH = PrairieKing.PPM/15;
    public static final float ENEMY_WIDTH = PrairieKing.PPM/15;
    public static final float PROJECTILE_WIDTH = PrairieKing.PPM/35;
    public static final float POWERUP_WIDTH = PrairieKing.PPM/20;

}
