package com.prairieKing.model.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.Gun;

public class GunPowerups extends Gun {

    private Gun gun;
    private String type;

    private float effectTime;

    public GunPowerups(Gun gun) {
        super(null);
        this.gun = gun;
        effectTime = 7;
    }

  /*  @Override
    public void setSpeed(float speed) {
        gun.setSpeed(speed);
    }
*/
    @Override
    public void setShape() {
        super.setShape();
    }

    public void update() {
        effectTime -= Gdx.graphics.getDeltaTime();
    }

    public float getEffectTime() {
        return effectTime;
    }

    public void removeEffect() {

    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
