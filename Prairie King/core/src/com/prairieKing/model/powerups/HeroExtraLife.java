package com.prairieKing.model.powerups;

import com.prairieKing.model.GameLogic;


public class HeroExtraLife extends HeroPowerups {

    /** Creates powerup and addsLife to Hero.
     *
     * @param game Current GameLogic.
     */
    public HeroExtraLife(GameLogic game) {
        super(game);
        game.getHero().addLife();
        setEffectTime(10);
    }

}
