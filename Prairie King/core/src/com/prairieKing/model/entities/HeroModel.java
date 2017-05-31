package com.prairieKing.model.entities;

import com.badlogic.gdx.Gdx;
import com.prairieKing.PrairieKing;

public class HeroModel extends EntityModel {
    private float x, y;
    private int lives;
    private boolean left, right, up, down;
    private int speed;

    public HeroModel(float x, float y) {
        super(x, y);
        this.x = x;
        this.speed = 8;
        this.y = y;
        left = false; right = false; up = false; down = false;
        this.lives = 1;
        super.setType("HERO");
    }

    public void move() {
        float x = this.x, y = this.y;
        if (left)
            x = (x - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
        if (right)
            x = (x + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
        if (up)
            y = (y + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
        if (down)
            y = (y - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));

        setPosition(x, y);

        shoot();
    }

    public void shoot() {


    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
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

    public void isHit() {
        --lives;
    }

    @Override
    public void kill() {
        isHit();
        if (this.lives == 0)
            super.kill();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
