package com.prairieKing.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.prairieKing.controller.AI.Behavior;

/** Creates a default Enemy, to be fully implemented by
 * the methods of the specific enemy types.
 */
public class EnemyController extends EntityController implements Behavior {

    private String enemyType;
    private int health;

    private char currentDirection = 'n';

    private Sound sound;

    /** Creates a default Enemy Controller.
     *
     * @param x Position X of the Controller.
     * @param y Position Y of the Controller.
     */
    public EnemyController(float x, float y) {
        super(x,y);
        health = 1;
        super.setType("ENEMY");
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/enemyDeath.mp3"));
    }

    /** Sets an Enemy Type as values in string. Necessary to View.
     *
     * @param enemyType Sets enemyType.
     */
     protected void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    /** Returns the Enemy Type. Necessary to view
     *
     * @return enemyType.
     */
    public String getEnemyType() {
        return enemyType;
    }

    /** Necessary to Animation
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    @Override
    public void move(EnemyController e, HeroController h) {}

    /** Overrides kill method to kill only when health is 0. Useful
     * for some enemies.
     */
    @Override
    public void kill() {
        --health;
        if (health == 0) {
            sound.play();
            super.kill();
        }
    }

    /** Current Direction of the Enemy.
     *
     * @return Current Direction of the Enemy.
     */
    public char getCurrentDirection() {
        return currentDirection;
    }

    /** Sets current Enemy Direction.
     *
     * @param currentDirection enemyDirection.
     */
    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }

    /** Necessary for animation.
     */
    @Override
    public void initialBehaviour(EnemyController e, char direction) {}

    /** Necessary for animation.
     */
    @Override
    public float getTimeToStop() {
        return 0;
    }

    /** Necessary for animation.
     */
    public Behavior getBehaviour() {
        return null;
    }

}
