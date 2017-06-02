package com.prairieKing.model.AI;

import com.badlogic.gdx.Gdx;
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.HeroModel;

public class FlyingBehaviour implements Behaviour {

    private float ENEMY_SPEED = 7;

    private float initialTime;
    private char initialDirection;

    @Override
    public void move(EnemyModel e, HeroModel h) {
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;

        if (initialTime >= 0) {
            if (initialDirection == 'n')
                newY -= (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
            else if (initialDirection == 's')
                newY += (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
            else if (initialDirection == 'e')
                newX -= (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
            else
                newX += (ENEMY_SPEED * Gdx.graphics.getDeltaTime());

            initialTime -= Gdx.graphics.getDeltaTime();
            e.setPosition(newX, newY);

        }

        else {
            if (Math.abs(x - h.getX()) > 3) {
                if (x > h.getX()) {
                    newX -= (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                } else if (x < h.getX()) {
                    newX += (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                }
            }
            if (Math.abs(y - h.getY()) > 3) {
                if (y > h.getY()) {
                    newY -= (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                } else if (y < h.getY()) {
                    newY += (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                }
            }
        }
        e.setPosition(newX, newY);


    }

    @Override
    public void attack(EnemyModel e, HeroModel h) {

    }

    @Override
    public void initialBehaviour(char direction) {
        initialDirection = direction;
        initialTime = 3;
    }

    @Override
    public float getTimeToStop() {
        return 0;
    }
}
