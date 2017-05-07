package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class ChasingBehaviour implements Behaviour {

    @Override
    public void move(Enemy e, Hero h) {
        float x = e.getX();
        float y = e.getY();

        int r = MathUtils.random(40);

        if (Math.abs(x - h.getX()) < 3 && Math.abs(y - h.getY()) < 3) {  // Está próximo

            if (x > h.getX()) {
                e.setX(x - (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('a');
            }
            else if (x < h.getX()) {
                e.setX(x + (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                e.setY(y - (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('s');
            }
            else if (y < h.getY()) {
                e.setY(y + (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('w');
            }
        }


        else if (r == 2) {

            if (x > h.getX()) {
                e.setX(x - (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('a');
            }
            else if (x < h.getX()) {
                e.setX(x + (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                e.setY(y - (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('s');
            }
            else if (y < h.getY()) {
                e.setY(y + (50*Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('w');
            }
        }
        else
            continueMove(e, h);
    }

    public void continueMove(Enemy e, Hero h) {

        float x = e.getX();
        float y = e.getY();
        float hx = h.getX();
        float hy = h.getY();
        char c = e.getCurrentDirection();
      //  System.out.println(c);
        if (c == 'a') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setY(y + (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('w');
                }
                else {
                    e.setY(y - (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('s');
                }
            }
            else {
                e.setX(x - (50 * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('a');
            }
        }
        else if (c == 'd') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setY(y + (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('w');
                }
                else {
                    e.setY(y - (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('s');
                }
            }
            else {
                e.setX(x + (50 * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('d');
            }
        }
        else if (c == 's') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setX(x + (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('d');
                }
                else {
                    e.setX(x - (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('a');
                }
            }
            else {
                e.setY(y - (50 * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('s');
            }
        }
        else if (c == 'w') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setX(x + (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('d');
                }
                else {
                    e.setX(x - (50 * Gdx.graphics.getDeltaTime()));
                    e.setCurrentDirection('a');
                }
            }
            else {
                e.setY(y + (50 * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('w');
            }
        }
    }

    @Override
    public void attack(Enemy e, Hero h) {

    }

}
