package com.prairieKing.controller.entities;

import com.prairieKing.controller.AI.Behavior;
import com.prairieKing.controller.AI.ChasingBehavior;

public class BasicWalker extends EnemyController {

    private Behavior behaviour;

    /** Constructor for a Basic Walker Enemy.
     *
     * @param x X in which he spawns.
     * @param y Y in which he spawns.
     * @param initialDirection Direction to the center of the map.
     */
    public BasicWalker(float x, float y, char initialDirection) {
        super(x, y);
        super.setEnemyType("BASIC");
        behaviour = new ChasingBehavior();
        behaviour.initialBehaviour(this, initialDirection);
    }

    /**Triggers the behaviour movement.
     *
     * @param e This Enemy.
     * @param h The hero, needs its position.
     */
    @Override
    public void move(EnemyController e, HeroController h) {
        behaviour.move(this, h);
    }


    /** Gets the direction of the enemy.
     *
     * @return Direction of the enemy.
     */
    @Override
    public Behavior getBehaviour() {
        return behaviour;
    }
}
