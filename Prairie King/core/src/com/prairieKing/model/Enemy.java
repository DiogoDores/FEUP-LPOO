package com.prairieKing.model;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor {

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
}
