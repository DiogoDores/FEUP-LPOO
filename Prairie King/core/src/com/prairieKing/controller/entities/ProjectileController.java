package com.prairieKing.controller.entities;

public class ProjectileController extends EntityController {

    float x, y;

    public ProjectileController(float x, float y) {
        super(x, y);
        this.x = x; this.y = y;
        super.setType("PROJECTILE");
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
