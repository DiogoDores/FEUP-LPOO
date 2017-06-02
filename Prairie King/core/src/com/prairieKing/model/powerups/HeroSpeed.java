package com.prairieKing.model.powerups;

import com.badlogic.gdx.Game;
import com.prairieKing.model.GameLogic;


public class HeroSpeed extends HeroPowerups {

    private int newSpeed = 5;
    private int oldSpeed = 8;
    private GameLogic game;


    public HeroSpeed(GameLogic game) {
        super(game);
        this.game = game;
        game.getHero().setSpeed(newSpeed);
        setEffectTime(10);
    }

    @Override
    public void removeEffect() {
        super.removeEffect();
        game.getHero().setSpeed(oldSpeed);
    }
}
