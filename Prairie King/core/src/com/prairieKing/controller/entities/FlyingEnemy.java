package com.prairieKing.controller.entities;

import com.prairieKing.controller.AI.Behavior;
import com.prairieKing.controller.AI.FlyingBehavior;

public class FlyingEnemy extends EnemyController {

    private Behavior behaviour;

    public FlyingEnemy(float x, float y, char initialDirection) {
        super(x, y);
        super.setEnemyType("FLYING");
        behaviour = new FlyingBehavior();
        behaviour.initialBehaviour(this,initialDirection);

    }

    @Override
    public void move(EnemyController e, HeroController h) {
        behaviour.move(this, h);
    }

    @Override
    public Behavior getBehaviour() {
        return behaviour;
    }

}