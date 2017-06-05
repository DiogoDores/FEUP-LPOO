package com.prairieKing.controller.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.controller.entities.EnemyModel;
import com.prairieKing.controller.entities.HeroModel;

public class ChasingBehavior implements Behavior {

    private float ENEMY_SPEED = 400;
    private float initialTime;
    private char initialDirection;


    /** Moves an Enemy.
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    @Override
    public void move(EnemyModel e, HeroModel h) {
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;
        int r = MathUtils.random(40);


        if (initialTime >= 0) {
            if (initialDirection == 'n') {
                newY = y - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('s');
            }
            else if (initialDirection == 's') {
                newY = y + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('w');
            }
            else if (initialDirection == 'e') {
                newX = x - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('a');
            }
            else {
                newX = x + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('d');
            }

            initialTime -= Gdx.graphics.getDeltaTime();
            e.setPosition(newX, newY);

        }

        else if (Math.abs(x - h.getX()) < 3 && Math.abs(y - h.getY()) < 3) {  // Está próximo

            if (x > h.getX()) {
                newX = x - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('a');
            } else if (x < h.getX()) {
                newX = x + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                newY = y - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('s');
            } else if (y < h.getY()) {
                newY = y + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
                e.setCurrentDirection('w');
            }
            e.setPosition(newX, newY);
        } else if (r == 2) {

            if (x > h.getX()) {
                e.setPosition(x - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('a');
            } else if (x < h.getX()) {
                e.setPosition(x + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('d');
            }
            if (y > h.getY()) {
                e.setPosition(x, y - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('s');
            } else if (y < h.getY()) {
                e.setPosition(x, y + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('w');
            }
        } else
            continueMove(e, h);
    }

    private void continueMove(EnemyModel e, HeroModel h) {

        float x = e.getX();
        float y = e.getY();
        float hx = h.getX();
        float hy = h.getY();
        char c = e.getCurrentDirection();

        if (c == 'a') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setPosition(x, y + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                    e.setCurrentDirection('w');
                } else {
                    e.setPosition(x, y - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                    e.setCurrentDirection('s');
                }
            } else {
                e.setPosition(x - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                e.setCurrentDirection('a');
            }
        } else if (c == 'd') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setPosition(x, y + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                    e.setCurrentDirection('w');
                } else {
                    e.setPosition(x, y - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                    e.setCurrentDirection('s');
                }
            } else {
                e.setPosition(x + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                e.setCurrentDirection('d');
            }
        } else if (c == 's') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setPosition(x + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                    e.setCurrentDirection('d');
                } else {
                    e.setPosition(x - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                    e.setCurrentDirection('a');
                }
            } else {
                e.setPosition(x, y - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                e.setCurrentDirection('s');
            }
        } else if (c == 'w') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setPosition(x + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                    e.setCurrentDirection('d');
                } else {
                    e.setPosition(x - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                    e.setCurrentDirection('a');
                }
            } else {
                e.setPosition(x, y + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                e.setCurrentDirection('w');
            }
        }

    }


    /** Sets the initial movement in direction of the middle of the screen.
     *
     * @param direction Direction in which the enemy moves.
     */
    @Override
    public void initialBehaviour(EnemyModel e, char direction) {
        initialDirection = direction;
        initialTime = 3;
    }

    /** Important for animation.
     */
    @Override
    public float getTimeToStop() {
        return 0;
    }
}