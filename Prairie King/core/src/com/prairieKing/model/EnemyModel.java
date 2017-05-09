package com.prairieKing.model;

public class EnemyModel extends EntityModel implements Behaviour {

    private char currentDirection = 'n';
    private String type = null;
    private float x, y;

    public EnemyModel(float x, float y) {
        super(x,y);
        this.x = x;
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void move(EnemyModel e, HeroModel h) {

    }

    @Override
    public void attack(EnemyModel e, HeroModel h) {

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
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }


}
