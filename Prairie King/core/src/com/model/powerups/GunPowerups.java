package com.model.powerups;

import com.badlogic.gdx.Gdx;
import com.model.Gun;
import com.model.GameLogic;

/** Default powerup, specific powerups extend this class.
 */
public class GunPowerups extends Gun {

    private float effectTime;

    /** Creates a default Powerup for the gun.
     *
     * @param gameLogic Current game.
     */
    public GunPowerups(GameLogic gameLogic) {
        super(gameLogic);
        effectTime = 7;
    }

    /** Decreases effect time as time passes so powerup can wear off.
     */
    public void update() {
        effectTime -= Gdx.graphics.getDeltaTime();
    }

    /** Declares a method that is implemented by the powerups.
     */
    public void removeEffect() {}

    /** Returns the current effect time.
     *
     * @return Effect time.
     */
    public float getEffectTime() {
        return effectTime;
    }

    /** Sets effect time to value.
     *
     * @param effectTime New effect time.
     */
    public void setEffectTime(float effectTime) {
        this.effectTime = effectTime;
    }
}
