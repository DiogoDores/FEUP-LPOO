package com.model.powerups;

import com.model.GameLogic;

/** Adds a life to the Hero.
 */
public class HeroExtraLife extends HeroPowerups {

    /** Creates powerup and addsLife to Hero.
     *
     * @param gameLogic Current active instance of GameLogic.
     */
    public HeroExtraLife(GameLogic gameLogic) {
        gameLogic.getHero().addLife();
        setEffectTime(10);
    }

}
