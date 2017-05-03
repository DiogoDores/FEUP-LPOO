package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hero extends Actor {
    private float  x, y;
    private int lives;
    private boolean left, right, up, down;
    private boolean leftB, rightB, upB, downB; // Gun Methods
    private Gun gun;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
        left = false; right = false; up = false; down = false;
        this.lives = 3;
        gun = new Gun();
    }


    public void move() {
        if (left)
            setX(x-(100*Gdx.graphics.getDeltaTime())) ;
        if (right)
            setX(x+(100*Gdx.graphics.getDeltaTime())) ;
        if (up)
            setY(y+(100*Gdx.graphics.getDeltaTime())) ;
        if (down)
            setY(y-(100*Gdx.graphics.getDeltaTime())) ;
        shoot();
    }

    public void shoot() {
        int x = 0, y = 0;
        if(leftB)
            x = x - 100;
        if(rightB)
            x = x + 100;
        if (upB)
            y = y + 100;
        if (downB)
            y = y-100;

        if (x != 0 || y != 100) {
            Vector2 bulletDirection = new Vector2(x,y);
            Vector2 currPos = new Vector2(this.x, this.y);
            gun.shoot(currPos, bulletDirection);
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

    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

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
