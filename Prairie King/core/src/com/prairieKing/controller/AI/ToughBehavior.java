package com.prairieKing.controller.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.controller.entities.EnemyController;
import com.prairieKing.controller.entities.HeroController;

/** Tough Behaviour for the Tough Enemy.
 */
public class ToughBehavior implements Behavior {

    private float ENEMY_SPEED = 400;
    private float initialTime;
    private char initialDirection;
    private float timeToStop, totalStopTime, updateTime = 2;

    /**
     * Important for animation.
     */
    public ToughBehavior() {
        timeToStop = 3;
    }

    /**
     * Moves an Enemy.
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    @Override
    public void move(EnemyController e, HeroController h) {
        float x = e.getX();
        float y = e.getY();

        updateTime -= Gdx.graphics.getDeltaTime();
        timeToStop -= Gdx.graphics.getDeltaTime();
        if (timeToStop <= 0 && totalStopTime <= 0) {
            totalStopTime = MathUtils.random(1.0f, 2.0f);
            stop();
        } else if (totalStopTime > 0)
            stop();
        else if (initialTime >= 0)
            initialBehaviour(e, initialDirection);
        else if (Math.abs(x - h.getX()) < 3 && Math.abs(y - h.getY()) < 3) {  // Está próximo
            isClose(e, h);
        } else if (updateTime <= 0) {
            correctTrajectory(e, h);

        } else
            continueMove(e, h);
    }

    /** Enemy stops for a little while, just to avoid overpowering the hero.
     */
    private void stop() {
        totalStopTime -= Gdx.graphics.getDeltaTime();
        if (totalStopTime <= 0) {
            timeToStop = MathUtils.random(1.0f, 3.0f);
        }
    }

    /**
     * Narrows down on the player, preventing him from escaping due to the random
     * interval of correction.
     *
     * @param e Enemy associated with this behaviour.
     * @param h Hero.
     */
    private void isClose(EnemyController e, HeroController h) {
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;

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
    }

    /**
     * Corrects trajectory to come closer to player.
     *
     * @param e Enemy associated with this behaviour.
     * @param h Hero.
     */
    private void correctTrajectory(EnemyController e, HeroController h) {
        float x = e.getX(), y = e.getY();
        updateTime = MathUtils.random(1.0f, 2.0f);
        int r = MathUtils.random(1);
        if (r == 0) {
            if (x > h.getX()) {
                e.setPosition(x - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('a');
            } else if (x < h.getX()) {
                e.setPosition(x + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())), y);
                e.setCurrentDirection('d');
            }
        } else {
            if (y > h.getY()) {
                e.setPosition(x, y - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('s');
            } else if (y < h.getY()) {
                e.setPosition(x, y + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime())));
                e.setCurrentDirection('w');
            }
        }
    }

    /** Continues move in the direction it's going.
     *
     * @param e Enemy associated with this behaviour.
     * @param h Hero.
     */
    private void continueMove(EnemyController e, HeroController h) {

        float x = e.getX();
        float y = e.getY();
        float hx = h.getX();
        float hy = h.getY();
        char c = e.getCurrentDirection();

        if (c == 'a' || c == 'd') {
            if (Math.abs(x - hx) < 5) {
                if (y < hy) {
                    e.setPosition(x, y + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                    e.setCurrentDirection('w');
                } else {
                    e.setPosition(x, y - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                    e.setCurrentDirection('s');
                }
            } else if (c == 'a'){
                e.setPosition(x - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                e.setCurrentDirection('a');
            }
            else {
                e.setPosition(x + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                e.setCurrentDirection('d');
            }
        } else if (c == 's' || c == 'w') {
            if (Math.abs(y - hy) < 5) {
                if (x < hx) {
                    e.setPosition(x + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                    e.setCurrentDirection('d');
                } else {
                    e.setPosition(x - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)), y);
                    e.setCurrentDirection('a');
                }
            } else if (c == 's'){
                e.setPosition(x, y - (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                e.setCurrentDirection('s');
            }
            else {
                e.setPosition(x, y + (1 / (Gdx.graphics.getDeltaTime() * ENEMY_SPEED)));
                e.setCurrentDirection('w');
            }
        }
    }

    /** Sets the initial movement in direction of the middle of the screen.
     *
     * @param e         Enemy associated with this behaviour.
     * @param direction Direction to the center of the screen.
     */
    @Override
    public void initialBehaviour(EnemyController e, char direction) {
        initialDirection = direction;
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;

        if (initialDirection == 'n') {
            newY = y - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
            e.setCurrentDirection('s');
        } else if (initialDirection == 's') {
            newY = y + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
            e.setCurrentDirection('w');
        } else if (initialDirection == 'e') {
            newX = x - (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
            e.setCurrentDirection('a');
        } else {
            newX = x + (1 / (ENEMY_SPEED * Gdx.graphics.getDeltaTime()));
            e.setCurrentDirection('d');
        }

        initialTime -= Gdx.graphics.getDeltaTime();
        e.setPosition(newX, newY);

    }

    /**
     * Important for animation.
     */
    public float getTimeToStop() {
        return timeToStop;
    }
}