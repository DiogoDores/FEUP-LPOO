package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;

public class FireRateGunPowerup extends GunPowerups {

    private float newSpeed = 2;
    private float oldSpeed;

    private Gun gun;

    public FireRateGunPowerup(GameLogic gameLogic) {
        super(gameLogic);
        this.gun = gameLogic.getGun();
        oldSpeed = gun.getSPEED();
        gun.setTypeGun("FAST");
        gun.setSpeed(newSpeed);
    }


    @Override
    public void removeEffect() {
        gun.setSpeed(oldSpeed);
        gun.setTypeGun("NORMAL");
    }
}
