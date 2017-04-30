package com.prairieKing.model;

public class ChasingBehaviour implements Behaviour {

    @Override
    public void move(Enemy e, Hero h) {
        float x = e.getX();
        float y = e.getY();
        if (x > h.getX())
            e.setX(x - 1);
        else if (x < h.getX())
            e.setX(x + 1);
        if (y > h.getY())
            e.setY(y - 1);
        else if (y < h.getY())
            e.setY(y + 1);


    }

    @Override
    public void attack(Enemy e, Hero h) {

    }

}
