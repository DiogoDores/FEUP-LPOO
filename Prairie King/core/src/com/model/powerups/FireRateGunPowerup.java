package com.model.powerups;

import com.model.Gun;
import com.model.GameLogic;

/** FireRate Powerup, increases gun speed.
 */
public class FireRateGunPowerup extends GunPowerups {

    private float newSpeed = 2;
    private float oldSpeed;

    private Gun gun;

    /** Constructor for the increased Fire Rate Powerup.
     *
     * @param gameLogic Gets the gun from the gameLogic.
     */
    public FireRateGunPowerup(GameLogic gameLogic) {
        super(gameLogic);
        this.gun = gameLogic.getGun();
        oldSpeed = gun.getSpeed();
        gun.setTypeGun("FAST");
        gun.setSpeed(newSpeed);
    }

    /** Removed powerups effect from the gun.
     */
    @Override
    public void removeEffect() {
        gun.setSpeed(oldSpeed);
        gun.setTypeGun("NORMAL");
    }
}
