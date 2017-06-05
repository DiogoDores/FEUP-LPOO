package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;


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
        gun.setTypeGun("SHOTGUN");
    }


    @Override
    public void removeEffect() {
        gun.setShape(oldShape);
        gun.setTypeGun("NORMAL");
    }


}
