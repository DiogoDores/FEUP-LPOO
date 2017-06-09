package com.controller.AI;

import com.badlogic.gdx.Gdx;
import com.controller.entities.EnemyController;
import com.controller.entities.HeroController;

/** Flying Behaviour for the Flying Enemy.
 */
public class FlyingBehavior implements Behavior {

    private float ENEMY_SPEED = 7;

    private float initialTime;
    private char initialDirection;


    /** Moves an Enemy.
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    @Override
    public void move(EnemyController e, HeroController h) {
        float x = e.getX();
        float y = e.getY();

        float newX = x;
        float newY = y;

        if (initialTime >= 0) {
            initialBehaviour(e, initialDirection);
        }
        else {
            if (Math.abs(x - h.getX()) > 3) {
                if (x > h.getX()) {
                    newX -= (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                } else if (x < h.getX()) {
                    newX += (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                }
            }
            if (Math.abs(y - h.getY()) > 3) {
                if (y > h.getY()) {
                    newY -= (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                } else if (y < h.getY()) {
                    newY += (ENEMY_SPEED * Gdx.graphics.getDeltaTime());
                }
            }
            e.setPosition(newX, newY);
        }
    }

    /**Sets the initial movement in direction of the middle of the screen.
     *
     * @param e Enemy associated with this behaviour.
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

    /** Important for animation.
     */
    @Override
    public float getTimeToStop() {
        return 0;
    }
}
