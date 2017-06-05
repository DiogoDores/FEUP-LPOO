package com.prairieKing.model.powerups;

import com.badlogic.gdx.Gdx;
import com.prairieKing.controller.GameLogic;
import com.prairieKing.controller.entities.HeroController;

/**
 * Created by petre on 28/05/2017.
 */

public class HeroPowerups extends HeroController {

    private float effectTime;

    private GameLogic game;

    public HeroPowerups(GameLogic game) {
        super(0, 0);
        this.game = game;
        effectTime = 7;

    }

    public GameLogic getGame() {
        return game;
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
    }

    public void removeEffect()  {

    }

    public void update() {
        effectTime -= Gdx.graphics.getDeltaTime();
    }

    public void setEffectTime(float time) {
        effectTime = time;
    }

    public float getEffectTime() {
        return effectTime;
    }
}
