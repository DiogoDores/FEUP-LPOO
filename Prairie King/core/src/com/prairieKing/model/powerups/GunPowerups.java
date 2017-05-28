package com.prairieKing.model.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;

public class GunPowerups extends Gun {

    private Gun gun;
    private String type;

    private float effectTime;

    public GunPowerups(GameLogic gameLogic) {
        super(gameLogic);
        this.gun = gameLogic.getHero().getGun();
        effectTime = 7;
    }

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed);
    }

    @Override
    public void shoot(float posX, float posY, float vX, float vY) {
        System.out.println("Merda");
    }

    @Override
    public void setShape() {
        super.setShape();
    }

    public void update() {
        effectTime -= Gdx.graphics.getDeltaTime();
    }

    public float getEffectTime() {
        return effectTime;
    }

    public void removeEffect() {

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEffectTime(float effectTime) {
        this.effectTime = effectTime;
    }
}
