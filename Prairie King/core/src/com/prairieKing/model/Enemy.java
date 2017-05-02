package com.prairieKing.model;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor implements Behaviour {

    private int x, y;
    private String type;

    public Enemy(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }


    @Override
    public void move(Enemy e, Hero h) {

    }

    @Override
    public void attack(Enemy e, Hero h) {

    }

}
