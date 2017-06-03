package com.prairieKing.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.PrairieKing;
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.EntityModel;
import com.prairieKing.model.entities.HeroModel;

public class HeroWin extends HeroModel {

    private float ENEMY_SPEED = 400;

    private float initialTime;
    private boolean hasStarted = false;
    private char initialDirection;
    private float speed = 8;
    private float state;

    private float animationTime;

    private char activeChar;

    private float x, y;

    public HeroWin(float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
        activeChar = 's';
        animationTime = 0;
        state = 0;
    }

    public void move() {
        float newX = x, newY = y;
       if (state == 0) { // Is leaving

            if (x - PrairieKing.PPM/2  > 1) {
                newX = (x - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
                activeChar = 'a';
            }
            else if (x - PrairieKing.PPM/2  < -1) {
                newX = (x + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
                activeChar = 'd';
            }
            else {
               if (y < -10) {
                   state =1;
                   newY = PrairieKing.PPM;
               }
               else {
                   newY = (y - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
                   activeChar = 's';
               }
            }
       }
       else if (state == 1) { // Has reached the new zone

            if (y > PrairieKing.PPM/1.25f) {
                newY = (y - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
                activeChar = 's';
            }
            else if (x > (PrairieKing.PPM / 11 - 15)) {
                newX = (x - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
                activeChar = 'a';
            }
            else if (y > PrairieKing.PPM/2 -10) {
                newY = (y - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
                activeChar = 's';
            }
            else
                state = 1.5f;

       }
       else if (state == 1.5f) {
           if (x < PrairieKing.PPM/2 -5) {
               newX = (x + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
               activeChar = 'd';
           }
           else {
               state = 2;
               animationTime = 1;
           }
       }
       else if (state == 2) { // Has reached wife
            animationTime -= Gdx.graphics.getDeltaTime();
           if (animationTime <= 0) {
               state = 3;
               animationTime = 1.5f;
           }
       }

       else if (state == 3) { // Is kissing her
           animationTime -= Gdx.graphics.getDeltaTime();
           if (animationTime <= 0) {
               state = 4;
               animationTime = 9f;
           }
       }

       else if (state == 4) {
           animationTime -= Gdx.graphics.getDeltaTime();
           if (animationTime <= 0) {
               state = 5; // ends here
           }
       }

       updateState();
       setPosition(newX, newY);

       //System.out.println("x "+ x + " y " + y);

    }

    public void updateState() {
        setDown(false);
        setUp(false);
        setRight(false);
        setLeft(false);
        if (activeChar == 'a')
            setLeft(true);
        else if (activeChar == 's')
            setDown(true);
        else if (activeChar == 'd')
            setRight(true);
        else if (activeChar == 'w')
            setUp(true);

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void setLeft(boolean left) {
        super.setLeft(left);
    }

    @Override
    public void setRight(boolean right) {
        super.setRight(right);
    }

    @Override
    public void setUp(boolean up) {
        super.setUp(up);
    }

    @Override
    public void setDown(boolean down) {
        super.setDown(down);
    }

    @Override
    public boolean getLeft() {
        return super.getLeft();
    }

    @Override
    public boolean getRight() {
        return super.getRight();
    }

    @Override
    public boolean getUp() {
        return super.getUp();
    }

    @Override
    public boolean getDown() {
        return super.getDown();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public float getState() {
        return state;
    }
}