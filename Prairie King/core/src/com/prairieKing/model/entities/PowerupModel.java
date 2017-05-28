package com.prairieKing.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.model.Gun;

public class PowerupModel extends EntityModel {

    private String type;
    private Gun gun;
    private HeroModel hero;

    private float time;

    public PowerupModel(float x, float y) {
        super(x, y);
        super.setType("POWERUP");
        time = MathUtils.random(10.0f, 13.0f);
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void setHero(HeroModel hero) {
        this.hero = hero;
    }

    public void update() {
        time -= Gdx.graphics.getDeltaTime();
        if (time <= 0) {
            super.kill();
            System.out.println("Am I dead now?");
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
