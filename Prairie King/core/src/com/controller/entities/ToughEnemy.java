package com.controller.entities;

import com.controller.AI.ToughBehavior;
import com.controller.AI.Behavior;

/** Tough enemy, extends from a default Enemy, has a
 * tough behavior associated.
 */
public class ToughEnemy extends EnemyController {

    private Behavior behaviour;
    private int lives = 3;

    /** Constructor for a Tough Enemy.
     *
     * @param x X in which he spawns.
     * @param y Y in which he spawns.
     * @param initialDirection Direction to the center of the map.
     */
    public ToughEnemy(float x, float y, char initialDirection) {
        super(x, y);
        super.setEnemyType("TOUGH");
        behaviour = new ToughBehavior();
        behaviour.initialBehaviour(this,initialDirection);
    }

    /**Triggers the behaviour movement.
     *
     * @param e The Enemy associated with this behaviour.
     * @param h The hero, needs its position.
     */
    @Override
    public void move(EnemyController e, HeroController h) {
        super.move(e,h);
        behaviour.move(this, h);
    }

    /** Only can be killed when it has lost all lives.
     */
    @Override
    public void kill() {
        lives--;
        if(lives <= 0)
            super.kill();
    }

    /** Gets the behavior of the enemy.
     *
     * @return Behavior of the enemy.
     */
    @Override
    public Behavior getBehaviour() {
        return behaviour;
    }

    /** Needs to be killed, regardless of lives.
     */
    @Override
    public void permadeath() {
        lives = 0;
        kill();
    }
}
