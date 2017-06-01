package com.prairieKing.model.entities;

import com.prairieKing.model.AI.Behaviour;
import com.prairieKing.model.AI.ChasingBehaviour;
import com.prairieKing.model.AI.ToughBehaviour;

public class ToughEnemy extends EnemyModel {

    private Behaviour behaviour;
    private int lives = 3;
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    public ToughEnemy(int x, int y, char initialDirection) {
        super(x, y, initialDirection);
        super.setType("ENEMY");
        super.setEnemyType("TOUGH");
        behaviour = new ToughBehaviour();
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
    public void kill() {
        lives--;
        if(lives <= 0)
            super.kill();
    }
}
