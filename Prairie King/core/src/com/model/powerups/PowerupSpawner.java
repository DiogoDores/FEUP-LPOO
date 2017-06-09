package com.model.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.model.bodies.PowerupBody;
import com.PrairieKing;
import com.model.GameLogic;
import com.controller.entities.PowerupController;

import java.util.ArrayList;

public class PowerupSpawner {

    private ArrayList<PowerupController> powerupModels;
    private ArrayList<PowerupBody> powerupBodies;

    private GameLogic gameLogic;
    private float timeToSpawn;
    private World world;

    private float debugSpeed = 1;
    /** Constructor for a Powerup Spawner. This class is responsible for the
     * appropriate creation and treatment of all the powerups.
     *
     * @param gameLogic Current GameLogic.
     */
    public PowerupSpawner(GameLogic gameLogic) {
        powerupModels = new ArrayList<>();
        powerupBodies = new ArrayList<>();

        this.gameLogic = gameLogic;
        this.world = gameLogic.getWorld();

        timeToSpawn = MathUtils.random(10.0f, 17.0f);
    }

    /** Regularly checks whether
     */
    public void update() {
        timeToSpawn -= Gdx.graphics.getDeltaTime();
        checkPowerups();

        if (timeToSpawn <= 0) {
            spawn();
        }

    }

    public void spawn() {
        int r = MathUtils.random(4);
        Vector2 position = randomPos();

        PowerupController model = new PowerupController(position.x,position.y, gameLogic);

        if (r == 0) {
            model.powerupType("GUN SPEED");
            timeToSpawn = MathUtils.random(15.0f * debugSpeed,22.0f * debugSpeed);
        }
        else if (r == 1) {
            model.powerupType("GUN SHOTGUN");
            timeToSpawn = MathUtils.random(28.0f * debugSpeed,32.0f* debugSpeed);
        }
        else if (r == 2) {
            model.powerupType("GUN WHEEL");
            timeToSpawn = MathUtils.random(15.0f * debugSpeed,23.0f * debugSpeed);
        }
        else if (r == 3) {
            model.powerupType("HERO LIFE");
            timeToSpawn = MathUtils.random(30.0f * debugSpeed,35.0f * debugSpeed);
        }
        else if (r == 4) {
            model.powerupType("HERO SPEED");
            timeToSpawn = MathUtils.random(12.0f * debugSpeed,22.0f * debugSpeed);
        }
        powerupModels.add(model);
        powerupBodies.add(new PowerupBody(world,model));
    }

    private Vector2 randomPos() {

        float x = MathUtils.random(10, PrairieKing.PPM -10);
        float y = MathUtils.random(10, PrairieKing.PPM -10);

        return new Vector2(x,y);
    }

    public void checkPowerups() {
        for (int i = 0; i < powerupModels.size(); i++) {
            powerupModels.get(i).update();
            if (powerupModels.get(i).isFlaggedForDelete()) {
                for (int j = 0; j < powerupBodies.size(); j++) {
                       if (powerupBodies.get(j).getUserData() == powerupModels.get(i)) {
                           powerupBodies.get(j).destroy();
                           powerupBodies.remove(powerupBodies.get(j));
                    }
                }
                powerupModels.remove(powerupModels.get(i));

            }
        }
    }

    public ArrayList<PowerupController> getPowerupModels() {
        return powerupModels;
    }
}
