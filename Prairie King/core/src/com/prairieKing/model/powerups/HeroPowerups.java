package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;
import com.prairieKing.model.entities.HeroModel;

/**
 * Created by petre on 28/05/2017.
 */

public class HeroPowerups extends HeroModel {

    public float x, y;

    public HeroPowerups(float x, float y, GameLogic game) {
        super(x, y);
    }

    @Override
    public void setSpeed(int speed) {
        super.setSpeed(speed);
    }

    public void spawn(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
