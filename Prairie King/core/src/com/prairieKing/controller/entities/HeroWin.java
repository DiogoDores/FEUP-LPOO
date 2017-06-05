package com.prairieKing.controller.entities;

import com.badlogic.gdx.Gdx;
import com.prairieKing.PrairieKing;

public class HeroWin extends HeroController {
    private float x, y;
    private float speed = 8;
    private float state;
    private float animationTime;
    private char activeChar;

    /** Hero that has a set Behavior when won.
     *
     * @param x
     * @param y
     */
    public HeroWin(float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
        activeChar = 's';
        animationTime = 0;
        state = 0;
    }

    /** Specific animation, regardless of where player wins.
     */
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
    }

    /**Determines the current facing direction for the HeroAnimator.
     */
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

    /** Alters hero's position.
     *
     * @param x Alter X coordinate.
     * @param y Alter Y coordinate.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /** Boolean for left value.
     *
     * @param left
     */
    @Override
    public void setLeft(boolean left) {
        super.setLeft(left);
    }

    /** Boolean for right value.
     *
     * @param right
     */
    @Override
    public void setRight(boolean right) {
        super.setRight(right);
    }

    /** Boolean for up value.
     *
     * @param up
     */
    @Override
    public void setUp(boolean up) {
        super.setUp(up);
    }

    /** Boolean for down value.
     *
     * @param down
     */
    @Override
    public void setDown(boolean down) {
        super.setDown(down);
    }

    /** Returns left value.
     *
     * @return
     */
    @Override
    public boolean getLeft() {
        return super.getLeft();
    }

    /** Returns right value.
     *
     * @return
     */
    @Override
    public boolean getRight() {
        return super.getRight();
    }

    /** Returns up value.
     *
     * @return
     */
    @Override
    public boolean getUp() {
        return super.getUp();
    }

    /** Returns down value.
     *
     * @return
     */
    @Override
    public boolean getDown() {
        return super.getDown();
    }

    /** Returns X coordinate.
     *
     * @return
     */
    public float getX() {
        return x;
    }

    /** Returns Y coordinate.
     *
     * @return
     */
    public float getY() {
        return y;
    }


    public float getState() {
        return state;
    }
}