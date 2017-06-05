package com.prairieKing.model.powerups;

import com.badlogic.gdx.Gdx;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;

public class GunPowerups extends Gun {

    private float effectTime;

    public GunPowerups(GameLogic gameLogic) {
        super(gameLogic);
        effectTime = 7;
    }

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed);
    }

    @Override
    public void shoot(float posX, float posY, float vX, float vY) {
        super.shoot(posX, posY, vX, vY);
    }

    /*@Override
    public void setShape() {
        super.setShape(1);
    }*/

    @Override
    public void update() {
        super.update();
        effectTime -= Gdx.graphics.getDeltaTime();
    }

    public float getEffectTime() {
        return effectTime;
    }

    public void removeEffect() {

    }

    public void setEffectTime(float effectTime) {
        this.effectTime = effectTime;
    }
}
