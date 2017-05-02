package com.prairieKing.model;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor implements Behaviour {

    private char currentDirection = 'n';

    private float x, y;
    private String type;

    public Enemy(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }


    @Override
    public void move(Enemy e, Hero h) {

    }

    @Override
    public void attack(Enemy e, Hero h) {

    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    public String getType() {
        return type;
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }


}
