package com.prairieKing.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.controller.GameLogic;
import com.prairieKing.controller.Gun;
import com.prairieKing.model.powerups.HeroExtraLife;
import com.prairieKing.model.powerups.FireRateGunPowerup;
import com.prairieKing.model.powerups.HeroSpeed;
import com.prairieKing.model.powerups.ShotgunPowerup;
import com.prairieKing.model.powerups.WheelPowerup;

public class PowerupController extends EntityController {

    private String type;
    private Gun gun;
    private HeroController hero;
    private GameLogic gameLogic;
    private Sound powerUp;

    private float time;

    public PowerupController(float x, float y, GameLogic gameLogic) {
        super(x, y);
        super.setType("POWERUP");
        hero = gameLogic.getHero();
        this.gameLogic = gameLogic;
        gun = gameLogic.getGun();
        time = MathUtils.random(13.0f, 16.0f);
        powerUp = Gdx.audio.newSound(Gdx.files.internal("Sounds/start.mp3"));

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
        powerUp.play();
        if (type == "GUN SPEED") {
            gun.addPowerup(new FireRateGunPowerup(gameLogic));
        }
        else if(type == "GUN SHOTGUN") {
            gun.addPowerup(new ShotgunPowerup(gameLogic));
        }
        else if(type == "GUN WHEEL") {
            gun.addPowerup(new WheelPowerup(gameLogic));
        }
        else if(type == "HERO LIFE") {
            hero.addPowerup(new HeroExtraLife(gameLogic));
        }
        else if(type == "HERO SPEED") {
            hero.addPowerup(new HeroSpeed(gameLogic));
        }


        super.kill();
    }
}
