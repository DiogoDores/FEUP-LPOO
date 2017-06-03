package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;


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
