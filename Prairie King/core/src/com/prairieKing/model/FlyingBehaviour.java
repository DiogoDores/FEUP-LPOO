package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by petre on 02/05/2017.
 */

public class FlyingBehaviour implements Behaviour {

    private int enemySpeed = 400;

    @Override
    public void move(EnemyModel e, HeroModel h) {
        float x = e.getX();
        float y = e.getY();

        float posX = 0;
        float posY = 0;
        if (x > h.getX()) {
            System.out.println("\n"+0);
            posX = x - (1 / (enemySpeed * Gdx.graphics.getDeltaTime()));
        } else if (x < h.getX()) {
            System.out.println("\n"+1);
            posX = x + (1 / (enemySpeed * Gdx.graphics.getDeltaTime()));
        }
        if (Math.abs(x - h.getX()) < .1f) {
            System.out.println("\n"+2);
            posX = h.getX();
        }
        if (y > h.getY()) {
            System.out.println("\n"+3);
            posY = y - (1 / (enemySpeed * Gdx.graphics.getDeltaTime()));
        } else if (y < h.getY()) {
            System.out.println("\n"+4);
            posY = y + (1 / (enemySpeed * Gdx.graphics.getDeltaTime()));
        }

        if (Math.abs(y - h.getY()) < .1f) {
            System.out.println("\n"+4);
            posY = h.getY();
        }

        e.setPosition(posX, posY);
    }

    @Override
    public void attack(EnemyModel e, HeroModel h) {

    }
}
