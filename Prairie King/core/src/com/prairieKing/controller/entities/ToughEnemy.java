package com.prairieKing.controller.entities;

import com.prairieKing.controller.AI.Behavior;
import com.prairieKing.controller.AI.ToughBehavior;

public class ToughEnemy extends EnemyModel {

    private Behavior behaviour;
    private int lives = 3;
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public ToughEnemy(int x, int y, char initialDirection) {
        super(x, y, initialDirection);
        super.setType("ENEMY");
        super.setEnemyType("TOUGH");
        behaviour = new ToughBehavior();
        behaviour.initialBehaviour(this,initialDirection);
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
    public void kill() {
        lives--;
        if(lives <= 0)
            super.kill();
    }

    @Override
    public Behavior getBehaviour() {
        return behaviour;
    }
}
