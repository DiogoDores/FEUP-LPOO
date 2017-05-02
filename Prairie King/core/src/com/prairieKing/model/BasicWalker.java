package com.prairieKing.model;

public class BasicWalker extends Enemy {

    private Behaviour behaviour;

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
    }

    public BasicWalker(int x, int y, String type) {
        super(x, y, "basicWalker");
        behaviour = new ChasingBehaviour();
    }

    @Override
    public void move(Enemy e, Hero h) {
        behaviour.move(this, h);
    }

    @Override
    public void setCurrentDirection(char currentDirection) {
        super.setCurrentDirection(currentDirection);
    }
}
