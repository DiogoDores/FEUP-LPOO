package com.prairieKing.model.entities;

public class EntityModel {

    private boolean flaggedForDelete;

    private float x, y;
    String type;

    public EntityModel(float x, float y) {
        this.x = x;
        this.y = y;
        flaggedForDelete = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void kill() {
        flaggedForDelete = true;
    }

    public boolean isFlaggedForDelete() {
        return flaggedForDelete;
    }

    public void activate() { }
}
