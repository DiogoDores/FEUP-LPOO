package com.prairieKing.model;

/**
 * Created by petre on 30/04/2017.
 */

public class BasicWalker extends Enemy {

    private Behaviour behaviour;

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
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
}
