package com.prairieKing.model.powerups;

import com.prairieKing.controller.GameLogic;
import com.prairieKing.controller.Gun;


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
