package com.model.powerups;

import com.model.GameLogic;

/** Increases the speed hero walks/runs.
 */
public class HeroSpeed extends HeroPowerups {

    private int newSpeed = 5;
    private int oldSpeed = 8;
    private GameLogic gameLogic;

    /** Constructor for a new Hero Speed Powerup.
     *
     * @param gameLogic Current active instance of GameLogic.
     */
    public HeroSpeed(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        gameLogic.getHero().setSpeed(newSpeed);
        setEffectTime(10);
    }

    /** Resets hero's speed.
     */
    @Override
    public void removeEffect() {
        gameLogic.getHero().setSpeed(oldSpeed);
    }
}
