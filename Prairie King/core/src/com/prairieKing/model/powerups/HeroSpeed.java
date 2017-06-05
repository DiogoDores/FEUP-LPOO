package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;


public class HeroSpeed extends HeroPowerups {

    private int newSpeed = 5;
    private int oldSpeed = 8;
    private GameLogic game;

    /** Constructor for a new Hero Speed Powerup.
     *
     * @param game Current GameLogic.
     */
    public HeroSpeed(GameLogic game) {
        super(game);
        this.game = game;
        game.getHero().setSpeed(newSpeed);
        setEffectTime(10);
    }

    /** Resets hero's speed.
     */
    @Override
    public void removeEffect() {
        game.getHero().setSpeed(oldSpeed);
    }
}
