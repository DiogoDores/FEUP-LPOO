package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.prairieKing.controller.HeroBody;
import com.prairieKing.controller.PrairieKing;

public class HeroModel extends EntityModel {
    private float x, y;
    private int lives;
    private boolean leftB, rightB, upB, downB; // Gun Methods
    private Gun gun;

    public HeroModel(float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.lives = 3;

    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void shoot() {
        gun.update();

        int x = 0, y = 0;
        if (leftB)
            x = x - (int) PrairieKing.PPM*100;
        if (rightB)
            x = x + (int) PrairieKing.PPM*100;
        if (upB)
            y = y + (int) PrairieKing.PPM*100;
        if(downB)
            y = y - (int) PrairieKing.PPM*100;

        if ((x == 0 && y != 0) || (x!= 0 && y == 0) || (x != 0 && y != 0))
              gun.shoot(this.x, this.y, x,y);

    }

    public void setLeftB(boolean leftB) {
        this.leftB = leftB;
    }

    public void setRightB(boolean rightB) {
        this.rightB = rightB;
    }

    public void setUpB(boolean upB) {
        this.upB = upB;
    }

    public void setDownB(boolean downB) {
        this.downB = downB;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public float getY() {
        return y;
    }

    public Gun getGun() { return gun; }

    public int getLives() {
        return lives;
    }

    public int die() {
        lives--;
        if (lives < 0)
            return -1; // Has Died
        return 0;  // Is still alive
    }
}
