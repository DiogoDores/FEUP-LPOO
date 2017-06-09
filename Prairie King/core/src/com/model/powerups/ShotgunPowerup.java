package com.model.powerups;

import com.model.GameLogic;
import com.model.Gun;

/** Shotgun Powerup, changes gun bullet shape.
 */
public class ShotgunPowerup extends GunPowerups {

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
