package com.prairieKing.model;

public class FlyingEnemy extends EnemyModel {

    private Behaviour behaviour;

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public FlyingEnemy(int x, int y) {
        super(x, y);
        super.setType("flyingEnemy");
        behaviour = new FlyingBehaviour();
    }

    @Override
    public void move(EnemyModel e, HeroModel h) {
        behaviour.move(this, h);
    }

    @Override
    public void setCurrentDirection(char currentDirection) {
        super.setCurrentDirection(currentDirection);
    }
}
