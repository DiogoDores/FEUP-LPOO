package com.controller.entities;

import com.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.controller.AI.Behavior;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * Creates a default Enemy, to be fully implemented by
 * the methods of the specific enemy types.
 */
public class EnemyController extends EntityController implements Behavior {

    private String enemyType;
    private int health;
    private float animationDeath = Constants.DELAY_TIME_ON_COLLISION_WITH_HERO;
    private boolean wasKilled = false;

    private char currentDirection = 'n';

    private Sound sound;

    /**
     * Creates a default Enemy Controller.
     *
     * @param x Position X of the Controller.
     * @param y Position Y of the Controller.
     */
    public EnemyController(float x, float y) {
        super(x, y);
        health = 1;
        super.setType("ENEMY");
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/enemydeath.mp3"));
    }

    /**
     * Sets an Enemy Type as values in string. Necessary to View.
     *
     * @param enemyType Sets enemyType.
     */
    protected void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    /**
     * Returns the Enemy Type. Necessary to view
     *
     * @return enemyType.
     */
    public String getEnemyType() {
        return enemyType;
    }

    /**
     * Necessary to Animation
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    @Override
    public void move(EnemyController e, HeroController h) {

    }

    /**
     * Overrides kill method to kill only when health is 0. Useful
     * for some enemies.
     */
    @Override
    public void kill() {
        --health;
        if (health <= 0) {

            wasKilled = true;
            sound.play();
            super.kill();
        }
    }

    /**
     * Current Direction of the Enemy.
     *
     * @return Current Direction of the Enemy.
     */
    public char getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Sets current Enemy Direction.
     *
     * @param currentDirection enemyDirection.
     */
    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * Necessary for animation.
     */
    @Override
    public void initialBehaviour(EnemyController e, char direction) {
    }

    /**
     * Necessary for animation.
     */
    @Override
    public float getTimeToStop() {
        return 0;
    }

    /**
     * Necessary for animation.
     */
    public Behavior getBehaviour() {
        return null;
    }

    /**
     * Necessary to kill all enemies, including tough ones, on collision with hero.
     */
    public void permadeath() {
        health = 0;
        kill();
    }

    /** Decreases animationDeath
     */
    public void deathTime() {
        animationDeath -= Gdx.graphics.getDeltaTime();
    }

    /** Returns whether it has finished death animation.
     *
     * @return deathAnimation.
     */
    public float getAnimationDeath() {
        return animationDeath;
    }
}
