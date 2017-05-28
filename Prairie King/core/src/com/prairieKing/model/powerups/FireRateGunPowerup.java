package com.prairieKing.model.powerups;

import com.prairieKing.model.Gun;
import com.prairieKing.model.powerups.GunPowerups;

/**
 * Created by petre on 25/05/2017.
 */

public class FireRateGunPowerup extends GunPowerups {

    private float newSpeed = 2;
    private float oldSpeed;

    private Gun gun;

    public FireRateGunPowerup(Gun gun) {
        super(gun);
        this.gun = gun;
        oldSpeed = gun.getSPEED();
        gun.setSpeed(newSpeed);
        super.setType("SPEED");
    }

    @Override
    public void removeEffect() {
        gun.setSpeed(oldSpeed);
    }
}
