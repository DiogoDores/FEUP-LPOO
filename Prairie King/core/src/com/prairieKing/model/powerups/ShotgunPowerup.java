package com.prairieKing.model.powerups;

import com.prairieKing.controller.bodies.ProjectileBody;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.entities.ProjectileModel;



public class ShotgunPowerup extends GunPowerups {

    private float newSpeed = 2;
    private int oldShape = 1;
    private int newShape = 3;


    private Gun gun;

    public ShotgunPowerup(GameLogic gameLogic) {
        super(gameLogic);
        super.setEffectTime(20);
        this.gun = gameLogic.getGun();
        gun.setShape(newShape);
    }


    @Override
    public void removeEffect() {
        gun.setShape(oldShape);
    }


}