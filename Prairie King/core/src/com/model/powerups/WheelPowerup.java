package com.model.powerups;

import com.model.Gun;
import com.model.GameLogic;

/** Wheel powerup, makes the gun fire in all directions.
 *  Smaller effect time compared the shotgun.
 */
public class WheelPowerup extends GunPowerups {

    private int oldShape = 1;
    private int newShape = 9;

    private Gun gun;

    public WheelPowerup(GameLogic gameLogic) {
        super(gameLogic);
        super.setEffectTime(7);
        this.gun = gameLogic.getGun();
        gun.setShape(newShape);
        gun.setTypeGun("WHEEL");
    }


    @Override
    public void removeEffect() {
        gun.setShape(oldShape);
        gun.setTypeGun("NORMAL");
    }


}
