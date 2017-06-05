package com.prairieKing.model.powerups;

import com.prairieKing.controller.GameLogic;


public class HeroExtraLife extends HeroPowerups {

    private float newSpeed = 2;
    private int oldShape = 1;
    private int newShape = 3;


    public HeroExtraLife(GameLogic game) {
        super(game);
        game.getHero().addLife();
        setEffectTime(10);
    }

    @Override
    public void update() {

    }
}
