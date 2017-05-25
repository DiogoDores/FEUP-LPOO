package com.prairieKing.model;

import com.badlogic.gdx.physics.box2d.World;

public class GunPowerups extends Gun {

    private Gun gun;

    public GunPowerups(Gun gun) {
        super(null);
        this.gun = gun;
    }

    @Override
    public void setSpeed(float speed) {
        System.out.println("PowerUp");
        gun.setSpeed(speed);
    }

    @Override
    public void setShape() {
        super.setShape();
    }
}
