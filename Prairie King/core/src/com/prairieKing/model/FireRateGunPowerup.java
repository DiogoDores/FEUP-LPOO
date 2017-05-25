package com.prairieKing.model;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by petre on 25/05/2017.
 */

public class FireRateGunPowerup extends GunPowerups {

    private float newSpeed = 2;

    public FireRateGunPowerup(Gun gun) {
        super(gun);
        System.out.println("FireRate");
        super.setSpeed(newSpeed);
    }

}
