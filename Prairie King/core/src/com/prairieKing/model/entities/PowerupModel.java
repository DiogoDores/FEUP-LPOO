package com.prairieKing.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.powerups.FireRateGunPowerup;
import com.prairieKing.model.powerups.ShotgunPowerup;
import com.prairieKing.model.powerups.WheelPowerup;

public class PowerupModel extends EntityModel {

    private String type;
    private Gun gun;
    private HeroModel hero;
    private GameLogic gameLogic;

    private float time;

    public PowerupModel(float x, float y, GameLogic gameLogic) {
        super(x, y);
        super.setType("POWERUP");
        hero = gameLogic.getHero();
        this.gameLogic = gameLogic;
        gun = gameLogic.getGun();
        time = MathUtils.random(13.0f, 16.0f);
    }

    public void update() {
        time -= Gdx.graphics.getDeltaTime();
        if (time <= 0)
            super.kill();

    }

    public void powerupType(String type) {
        this.type = type;
    }

    public String getPowerupType() {
        return type;
    }

    @Override
    public void activate() {
        if (type == "GUN SPEED") {
            gun.addPowerup(new FireRateGunPowerup(gameLogic));
        }
        else if(type == "GUN SHOTGUN") {
            gun.addPowerup(new ShotgunPowerup(gameLogic));
        }
        else if(type == "GUN WHEEL") {
            gun.addPowerup(new WheelPowerup(gameLogic));
        }

        super.kill();
    }
}
