package com.prairieKing.model.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.HeroModel;

public class ToughBehavior implements Behavior {

    private float ENEMY_SPEED = 400;

    private float initialTime;
    private char initialDirection;
    private float timeToStop, totalStopTime;

    public ToughBehavior() {
        timeToStop = 3;
    }

    @Override
    public void move(EnemyModel e, HeroModel h) {
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;
        int r = MathUtils.random(40);

        timeToStop-=Gdx.graphics.getDeltaTime();
        if(timeToStop <=0 && totalStopTime <= 0) {
            totalStopTime = MathUtils.random(1.0f,2.0f);
            stop();
        }
        else if (totalStopTime > 0)
            stop();
        else if (initialTime >= 0) {
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

    public void stop() {
        totalStopTime-= Gdx.graphics.getDeltaTime();
        if(totalStopTime<= 0) {
            timeToStop = MathUtils.random(1.0f, 3.0f);
        }
    }

    public void continueMove(EnemyModel e, HeroModel h) {

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

    @Override
    public void attack(EnemyModel e, HeroModel h) {

    }

    @Override
    public void initialBehaviour(char direction) {
        initialDirection = direction;
        initialTime = 3;
    }

    public float getTimeToStop() {
        return timeToStop;
    }
}