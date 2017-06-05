package com.prairieKing.controller.entities;

import com.prairieKing.controller.AI.Behavior;
import com.prairieKing.controller.AI.FlyingBehavior;

/** Flying enemy, extends from a default Enemy, has a
 * flying behavior associated.
 */
public class FlyingEnemy extends EnemyController {

    private Behavior behaviour;

    /** Constructor for a FLying Enemy.
     *
     * @param x X in which he spawns.
     * @param y Y in which he spawns.
     * @param initialDirection Direction to the center of the map.
     */
    public FlyingEnemy(float x, float y, char initialDirection) {
        super(x, y);
        super.setEnemyType("FLYING");
        behaviour = new FlyingBehavior();
        behaviour.initialBehaviour(this,initialDirection);
    }

    /**Triggers the behaviour movement.
     *
     * @param e The Enemy associated with this behaviour.
     * @param h The hero, needs its position.
     */
    @Override
    public void move(EnemyController e, HeroController h) {
        behaviour.move(this, h);
    }

    /** Gets the behavior of the enemy.
     *
     * @return Behavior of the enemy.
     */
    @Override
    public Behavior getBehaviour() {
        return behaviour;
    }

}