package com.prairieKing.model.powerups;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.prairieKing.model.GameLogic;
import com.prairieKing.controller.entities.HeroController;

/** Default Powerup for a hero.
 */
public class HeroPowerups extends HeroController {

    private float effectTime;

    /** Creates a default Powerup for the hero.
     */
    public HeroPowerups() {
        super(0, 0);
        effectTime = 7;
    }

    /** Declares a method that is implemented by the powerups.
     */
    public void removeEffect()  {}

    /** Decreases time so it can wear off.
     */
    public void update() {
        effectTime -= Gdx.graphics.getDeltaTime();
    }

    /** Sets the powerup effect time.
     *
     * @param time Effect time.
     */
    public void setEffectTime(float time) {
        effectTime = time;
    }

    /** Gets the powerup effect ime.
     *
     * @return Effect time.
     */
    public float getEffectTime() {
        return effectTime;
    }

}
