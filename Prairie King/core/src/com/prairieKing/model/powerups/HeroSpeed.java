package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;

/** Increases the speed hero walks/runs.
 */
public class HeroSpeed extends HeroPowerups {

    private int newSpeed = 5;
    private int oldSpeed = 8;
    private GameLogic game;

    /** Constructor for a new Hero Speed Powerup.
     */
    public HeroSpeed() {
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
