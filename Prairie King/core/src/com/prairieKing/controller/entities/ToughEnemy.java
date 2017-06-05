package com.prairieKing.controller.entities;

import com.prairieKing.controller.AI.Behavior;
import com.prairieKing.controller.AI.ToughBehavior;

public class ToughEnemy extends EnemyController {

    private Behavior behaviour;
    private int lives = 3;

    public ToughEnemy(float x, float y, char initialDirection) {
        super(x, y);
        super.setEnemyType("TOUGH");
        behaviour = new ToughBehavior();
        behaviour.initialBehaviour(this,initialDirection);
    }

    @Override
    public void move(EnemyController e, HeroController h) {
        behaviour.move(this, h);
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
