package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hero extends Actor {
    private float  x, y;
    private int lives;
    private boolean left, right, up, down;

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


    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
        left = false; right = false; up = false; down = false;
        this.lives = 3;
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

    public void move() {
        if (left)
            setX(x-(100*Gdx.graphics.getDeltaTime())) ;
        if (right)
            setX(x+(100*Gdx.graphics.getDeltaTime())) ;
        if (up)
            setY(y+(100*Gdx.graphics.getDeltaTime())) ;
        if (down)
            setY(y-(100*Gdx.graphics.getDeltaTime())) ;


    }

    public int die() {
        lives--;
        if (lives < 0)
            return -1; // Has Died
        return 0;  // Is still alive
    }
}
