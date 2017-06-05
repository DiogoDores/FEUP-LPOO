package com.prairieKing.controller.entities;

public class ProjectileModel extends EntityModel {

    float x, y;

    public ProjectileModel(float x, float y) {
        super(x, y);
        super.setType("PROJECTILE");
        this.x = x; this.y = y;

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
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
    public void kill() {
        super.kill();
    }
}
