package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by petre on 02/05/2017.
 */

public class FlyingBehaviour implements Behaviour {
    @Override
    public void move(Enemy e, Hero h) {
        float x = e.getX();
        float y = e.getY();


            if (x > h.getX()) {
                e.setX(x - (40* Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('a');
            }
            else if (x < h.getX()) {
                e.setX(x + (40*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                e.setY(y - (40*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('s');
            }
            else if (y < h.getY()) {
                e.setY(y + (40*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('w');
            }


    }

    @Override
    public void attack(Enemy e, Hero h) {

    }
}
