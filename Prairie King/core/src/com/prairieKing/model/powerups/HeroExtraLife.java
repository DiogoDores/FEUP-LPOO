package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;

/** Adds a life to the Hero.
 */
public class HeroExtraLife extends HeroPowerups {

    /** Creates powerup and addsLife to Hero.
     *
     */
    public HeroExtraLife(GameLogic gameLogic) {
        gameLogic.getHero().addLife();
        setEffectTime(10);
    }

}
