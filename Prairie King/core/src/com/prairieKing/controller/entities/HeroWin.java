package com.prairieKing.controller.entities;

import com.badlogic.gdx.Gdx;
import com.prairieKing.PrairieKing;

/** Custom hero, created only when the player wins.
 */
public class HeroWin extends HeroController {
    private float speed = 8;
    private float state;
    private float animationTime;
    private char activeChar;

    /** Hero that has a set Behavior when won.
     *
     * @param x HeroController's X.
     * @param y HeroController's Y.
     */
    public HeroWin(float x, float y) {
        super(x, y);
        activeChar = 's';
        animationTime = 0;
        state = 0;
    }

    /** Specific animation, regardless of where player wins.
     */
    public void move() {
       if (state == 0)
           isLeavingTheZone();

       else if (state == 1)
           headsToBridge();

       else if (state == 1.5f)
           goesToWife();

       else if (state == 2)
           waitsForKiss();

       else if (state == 3)
          kisses();

       else if (state == 4)
          ends();

       updateState();
    }

    /** Starts heading down the map.
     */
    private void isLeavingTheZone() {
        float newX = super.getX(), newY = super.getY();
        float x = newX, y = newY;
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
        super.setPosition(newX,newY);
    }

    /** Heads to bridge of the new zone.
     */
    private void headsToBridge() {
        float newX = super.getX(), newY = super.getY();
        float x = newX, y = newY;
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
        super.setPosition(newX,newY);
    }

    /** Path to see his wife.
     */
    private void goesToWife() {
        float newX = super.getX(), newY = super.getY();
        float x = newX, y = newY;
        if (x < PrairieKing.PPM/2 -5) {
            newX = (x + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
            activeChar = 'd';
        }
        else {
            state = 2;
            animationTime = 1;
        }
        super.setPosition(newX,newY);
    }

    /** Waits a bit for a kiss.
     */
    private void waitsForKiss() {
        animationTime -= Gdx.graphics.getDeltaTime();
        if (animationTime <= 0) {
            state = 3;
            animationTime = 1.5f;
        }
    }

    /** Smooch, cute kiss!
     *  In the GameStage transition to the end state.
     */
    private void kisses() {
        animationTime -= Gdx.graphics.getDeltaTime();
        if (animationTime <= 0) {
            state = 4;
            animationTime = 9f;
        }
    }

    /** Reached the end of the animation.
     */
    private void ends() {
        animationTime -= Gdx.graphics.getDeltaTime();
        if (animationTime <= 0) {
            state = 5;
        }
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

    /** Returns the state of the animation.
     *
     * @return state of the animation.
     */
    public float getState() {
        return state;
    }
}