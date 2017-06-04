package com.prairieKing.model.entities;

import com.prairieKing.model.AI.Behavior;
import com.prairieKing.model.AI.ChasingBehavior;

public class BasicWalker extends EnemyModel {

    private Behavior behaviour;

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public BasicWalker(int x, int y, char initialDirection) {
        super(x, y, initialDirection);
        super.setType("ENEMY");
        super.setEnemyType("BASIC");
        behaviour = new ChasingBehavior();
        behaviour.initialBehaviour(initialDirection);
    }

    @Override
    public void move(EnemyModel e, HeroModel h) {
        behaviour.move(this, h);
    }

    @Override
    public void setCurrentDirection(char currentDirection) {
        super.setCurrentDirection(currentDirection);
    }

    @Override
    public Behavior getBehaviour() {
        return behaviour;
    }
}
