package com.prairieKing.controller.entities;

import com.badlogic.gdx.Gdx;
import com.prairieKing.PrairieKing;
import com.prairieKing.model.powerups.HeroPowerups;

import java.util.ArrayList;

public class HeroModel extends EntityModel {
    private float x, y;
    private int lives;
    private boolean left, right, up, down;
    private int speed;

    private boolean hasWon = false;


    private float MIN_WIDTH = 3.76f, MAX_WIDTH = 90;
    private float MIN_HEIGHT = 3.76f, MAX_HEIGHT = 90;

    private ArrayList<HeroPowerups> powerups = new ArrayList<>();


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
        checkPowerups();
        float x = this.x, y = this.y;
        if (left && x > MIN_WIDTH)
            x = (x - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
        if (right && x < MAX_WIDTH)
            x = (x + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
        if (up && y < MAX_HEIGHT)
            y = (y + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
        if (down && y > MIN_HEIGHT)
            y = (y - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));

        setPosition(x, y);

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

    public boolean getLeft() {
        return this.left;
    }

    public boolean getRight() {
        return this.right;
    }

    public boolean getUp() {
        return this.up;
    }

    public boolean getDown() {
        return this.down;
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

    public void addLife() {
        this.lives++;
    }

    public void checkPowerups() {
        for (int i = 0; i < powerups.size(); i++) {
            powerups.get(i).update();
            if (powerups.get(i).getEffectTime() <= 0) {
                powerups.get(i).removeEffect();
                powerups.remove(i);
            }
        }
    }

    public void addPowerup(HeroPowerups powerup) {
        powerups.add(powerup);
    }

    public int getLives() {
        return lives;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getState() {
        return -1;
    }
}
