package com.prairieKing.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.powerups.HeroExtraLife;
import com.prairieKing.model.powerups.FireRateGunPowerup;
import com.prairieKing.model.powerups.HeroSpeed;
import com.prairieKing.model.powerups.ShotgunPowerup;
import com.prairieKing.model.powerups.WheelPowerup;

public class PowerupController extends EntityController {

    private String type;
    private GameLogic gameLogic;
    private Sound powerUp;

    private float time;

    /** Adds a Powerup Controller to the world, for the player to pick up.
     *
     * @param x X spawn position.
     * @param y Y spawn position.
     * @param gameLogic Needed to affect hero or gun on activation.
     */
    public PowerupController(float x, float y, GameLogic gameLogic) {
        super(x, y);
        super.setType("POWERUP");
        this.gameLogic = gameLogic;
        time = MathUtils.random(13.0f, 16.0f);
        powerUp = Gdx.audio.newSound(Gdx.files.internal("Sounds/start.mp3"));
    }

    /** Updates the time, so when a limit is reached, the powerup is destroyed.
     */
    public void update() {
        time -= Gdx.graphics.getDeltaTime();
        if (time <= 0)
            super.kill();

    }

    /** Important for GameStage, that needs to know what type of powerup it is
     * so it can display its sprite correctly.
     *
     * @param type Type of powerup.
     */
    public void powerupType(String type) {
        this.type = type;
    }

    /** Returns the type of powerup.
     *
     * @return type of powerup.
     */
    public String getPowerupType() {
        return type;
    }

    /** Activates the powerup.
     */
    @Override
    public void activate() {
        powerUp.play();
        switch (type) {
            case "GUN SPEED":
                gameLogic.getGun().addPowerup(new FireRateGunPowerup(gameLogic));
                break;
            case "GUN SHOTGUN":
                gameLogic.getGun().addPowerup(new ShotgunPowerup(gameLogic));
                break;
            case "GUN WHEEL":
                gameLogic.getGun().addPowerup(new WheelPowerup(gameLogic));
                break;
            case "HERO LIFE":
                gameLogic.getHero().addPowerup(new HeroExtraLife(gameLogic));
                break;
            case "HERO SPEED":
                gameLogic.getHero().addPowerup(new HeroSpeed(gameLogic));
                break;
        }

        super.kill();
    }
}
