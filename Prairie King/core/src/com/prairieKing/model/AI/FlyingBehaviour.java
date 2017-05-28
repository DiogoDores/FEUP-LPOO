package com.prairieKing.model.AI;

import com.badlogic.gdx.Gdx;
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.HeroModel;

/**
 * Created by petre on 02/05/2017.
 */

public class FlyingBehaviour implements Behaviour {

    private float ENEMY_SPEED = 10;

    @Override
    public void move(EnemyModel e, HeroModel h) {
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;

            if (x > h.getX()) {
               newX -= (ENEMY_SPEED* Gdx.graphics.getDeltaTime());
            }
            else if (x < h.getX()) {
                newX += (ENEMY_SPEED* Gdx.graphics.getDeltaTime());
            }
            if (y > h.getY()) {
                newY -= (ENEMY_SPEED*Gdx.graphics.getDeltaTime());
            }
            else if (y < h.getY()) {
                newY += (ENEMY_SPEED*Gdx.graphics.getDeltaTime());
            }

            e.setPosition(newX, newY);


    }

    @Override
    public void attack(EnemyModel e, HeroModel h) {

    }
}
