package com.prairieKing.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.view.GameStage;

public class HeroModel extends EntityModel {
    private float x, y;
    private int lives;
    private boolean left, right, up, down;
    private boolean leftB, rightB, upB, downB; // Gun Methods
    private Gun gun;
    private int speed;

    public HeroModel(float x, float y) {
        super(x, y);
        this.x = x;
        this.speed = 8;
        this.y = y;
        left = false; right = false; up = false; down = false;
        this.lives = 3;
        super.setType("HERO");
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void move() {
        gun.update();
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
        int x = 0, y = 0;
        if (leftB)
            x = x - (int) PrairieKing.PPM * 2;
        if (rightB)
            x = x + (int) PrairieKing.PPM * 2;
        if (upB)
            y = y + (int) PrairieKing.PPM * 2;
        if (downB)
            y = y - (int) PrairieKing.PPM * 2;

        if ((x == 0 && y != 0) || (x != 0 && y == 0) || (x != 0 && y != 0)) {
            if (rightB) {
                if (upB)
                    gun.shoot(this.x + GameStage.HERO_WIDTH - GameStage.PROJECTILE_WIDTH / 2, this.y + GameStage.HERO_WIDTH - GameStage.PROJECTILE_WIDTH / 2, x, y);
                else if (downB)
                    gun.shoot(this.x + GameStage.HERO_WIDTH - GameStage.PROJECTILE_WIDTH / 2, this.y - GameStage.PROJECTILE_WIDTH / 2, x, y);
                else
                    gun.shoot(this.x + GameStage.HERO_WIDTH - GameStage.PROJECTILE_WIDTH / 2, this.y + GameStage.HERO_WIDTH / 2 - GameStage.PROJECTILE_WIDTH / 2, x, y);
            }
            if (leftB) {
                if (upB)
                    gun.shoot(this.x - GameStage.PROJECTILE_WIDTH / 2, this.y + GameStage.HERO_WIDTH - GameStage.PROJECTILE_WIDTH / 2, x, y);
                else if (downB)
                    gun.shoot(this.x - GameStage.PROJECTILE_WIDTH / 2, this.y - GameStage.PROJECTILE_WIDTH / 2, x, y);
                else
                    gun.shoot(this.x - GameStage.PROJECTILE_WIDTH / 2, this.y + GameStage.HERO_WIDTH / 2 - GameStage.PROJECTILE_WIDTH / 2, x, y);
            } else if (upB && !rightB && !leftB)
                gun.shoot(this.x + GameStage.HERO_WIDTH / 2 - GameStage.PROJECTILE_WIDTH / 2, this.y + GameStage.HERO_WIDTH - GameStage.PROJECTILE_WIDTH / 2, x, y);
            else if (downB && !rightB && !leftB)
                gun.shoot(this.x + GameStage.HERO_WIDTH / 2 - GameStage.PROJECTILE_WIDTH / 2, this.y - GameStage.PROJECTILE_WIDTH / 2, x, y);
        }

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

    public Gun getGun() {
        return gun;
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
}
