package com.controller.entities;

import com.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.PrairieKing;
import com.model.GameLogic;
import com.model.powerups.HeroPowerups;

import java.util.ArrayList;

/** The hero of the game. The player controls directly all the X and Y positions.
 */
public class HeroController extends EntityController {
    private int lives;
    private boolean left, right, up, down;
    private int speed;

    private GameLogic gameLogic;

    private float resetTime;

    private float MIN_WIDTH = 3.76f, MAX_WIDTH = 90;
    private float MIN_HEIGHT = 3.76f, MAX_HEIGHT = 90;

    private int timeToPlay = 15;

    private ArrayList<HeroPowerups> powerups = new ArrayList<>();
    private Sound footsteps;

    private boolean deathAnimation = false;

    /** New Hero Model, spawning on a specific position,
     * in this case the middle of the screen.
     *
     * @param x X coordinate of spawn location.
     * @param y Y coordinate of spawn location.
     */
    public HeroController(float x, float y) {
        super(x, y);
        this.speed = 8;
        left = false; right = false; up = false; down = false;
        this.lives = 3;
        super.setType("HERO");
        resetTime = 0;
        footsteps = Gdx.audio.newSound(Gdx.files.internal("sounds/footstep.mp3"));
    }

    /** Move based on current keys pressed.
     */
    public void move() {
        checkPowerups();
        if (resetTime > 0)
            resetTime -= Gdx.graphics.getDeltaTime();
        else
            resetTime = 0;
        if (resetTime <=  0) {

            float x = super.getX(), y = super.getY();
            if (left && x > MIN_WIDTH)
                x = (x - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
            if (right && x < MAX_WIDTH)
                x = (x + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
            if (up && y < MAX_HEIGHT)
                y = (y + (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));
            if (down && y > MIN_HEIGHT)
                y = (y - (PrairieKing.PPM / speed * Gdx.graphics.getDeltaTime()));

            setPosition(x, y);
        }

    }

    public void playSound() {

        if(timeToPlay <= 0){
            footsteps.setVolume(footsteps.play(), 0.02f);
            timeToPlay = 15;
        } else
            timeToPlay -= Gdx.graphics.getDeltaTime();
    }


    /** Sets boolean for key pressed.
     *
     * @param left A key pressed.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /** Sets boolean for key pressed.
     *
     * @param right D key pressed.
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /** Sets boolean for key pressed.
     *
     * @param up W key pressed.
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /** Sets boolean for key pressed.
     *
     * @param down S key pressed.
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /** Gets whether key is pressed.
     *
     * @return left
     */
    public boolean getLeft() {
        return this.left;
    }

    /** Gets whether key is pressed.
     *
     * @return right
     */
    public boolean getRight() {
        return this.right;
    }

    /** Gets whether key is pressed.
     *
     * @return up
     */
    public boolean getUp() {
        return this.up;
    }

    /** Gets whether key is pressed.
     *
     * @return down
     */
    public boolean getDown() {
        return this.down;
    }

    /** Determines the behaviour of the kill method.
     */
    @Override
    public void kill() {

        Music death = gameLogic.getPrairieKing().getDeathHeroSound();

        --lives;

        if(!gameLogic.isForTest()) {

            gameLogic.getPrairieKing().getMusic().pause();

            if (lives != 0) {
                death.setVolume(0.5f);
                death.play();
            }
        }

        deathAnimation = true;
        gameLogic.getAI().removeEnemies();
        resetTime = Constants.DELAY_TIME_ON_COLLISION_WITH_HERO;

        if (this.lives == 0)
            super.kill();
    }

    /** Adds a life to the hero, invoked by the extraLifePowerup.
     */
    public void addLife() {
        this.lives++;
    }

    /** Checks whether powerups have worn off.
     */
    public void checkPowerups() {
        for (int i = 0; i < powerups.size(); i++) {
            powerups.get(i).update();
            if (powerups.get(i).getEffectTime() <= 0) {
                powerups.get(i).removeEffect();
                powerups.remove(i);
            }
        }
    }

    /** Adds a powerup to the Hero.
     *
     * @param powerup powerup to Add.
     */
    public  void addPowerup(HeroPowerups powerup) {
        powerups.add(powerup);
    }

    /** Returns the number of lives.
     *
     * @return number of lives.
     */
    public int getLives() {
        return lives;
    }

    /** Sets the walking/running speed of the hero.
     *
     * @param speed speed to set.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /** Important to check final moments of the game.
     *
     * @return state.
     */
    public float getState() {
        return -1;
    }

    /** Set GameLogic.
     *
     * @param gameLogic Instance of the GameLogic class.
     */
    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    /** Gets a boolean for handling the hero's death animation.
     *
     * @return deathAnimation.
     */
    public boolean isDeathAnimation() {
        return deathAnimation;
    }

    /** Sets deathAnimation.
     *
     * @param deathAnimation For better handling of the hero's death animation.
     */
    public void setDeathAnimation(boolean deathAnimation) {
        this.deathAnimation = deathAnimation;
    }

    /** Returns time in case of death.
     *
     * @return resetTime.
     */
    public float getResetTime() {
        return resetTime;
    }
}
