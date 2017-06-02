package com.prairieKing.model.entities;

import com.prairieKing.model.AI.Behaviour;
import com.prairieKing.model.AI.ChasingBehaviour;

public class BasicWalker extends EnemyModel {

    private Behaviour behaviour;

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public BasicWalker(int x, int y, char initialDirection) {
        super(x, y, initialDirection);
        super.setType("ENEMY");
        super.setEnemyType("BASIC");
        behaviour = new ChasingBehaviour();
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
    public Behaviour getBehaviour() {
        return behaviour;
    }
}
