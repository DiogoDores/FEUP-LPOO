package com.prairieKing.model.powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.bodies.PowerupBody;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.entities.PowerupModel;

import java.util.ArrayList;

public class PowerupSpawner {

    private ArrayList<PowerupModel> powerupModels;
    private ArrayList<PowerupBody> powerupBodies;

    private GameLogic gameLogic;
    private float timeToSpawn;
    private World world;

    public PowerupSpawner(GameLogic gameLogic) {
        powerupModels = new ArrayList<>();
        powerupBodies = new ArrayList<>();


        this.gameLogic = gameLogic;
        this.world = gameLogic.getWorld();

        timeToSpawn = 4; //generateRandom();

    }

    public void update() {
        timeToSpawn -= Gdx.graphics.getDeltaTime();
        checkPowerups();

        if (timeToSpawn <= 0) {
            spawn();
        }

    }

    public float generateRandom() {
        float rand = MathUtils.random(10.0f, 20.0f);
        return rand;
    }

    public void spawn() {
        int r = MathUtils.random(4);
        Vector2 position = randomPos();

        PowerupModel model = new PowerupModel(position.x,position.y, gameLogic);

        if (r == 0) { // Speed
            model.powerupType("GUN SPEED");
            timeToSpawn = MathUtils.random(12.0f,22.0f);
        }
        else if (r == 1) {
            model.powerupType("GUN SHOTGUN");
            timeToSpawn = MathUtils.random(17.0f,25.0f);
        }
        else if (r == 2) {
            model.powerupType("GUN WHEEL");
            timeToSpawn = MathUtils.random(12.0f,22.0f);
        }
        else if (r == 3) {
            model.powerupType("HERO LIFE");
            timeToSpawn = MathUtils.random(12.0f,22.0f);
        }
        else if (r == 4) {
            model.powerupType("HERO SPEED");
            timeToSpawn = MathUtils.random(12.0f,22.0f);
        }
        powerupModels.add(model);
        powerupBodies.add(new PowerupBody(world,model));
    }

    public Vector2 randomPos() {

        float x = MathUtils.random(10, PrairieKing.PPM -10);
        float y = MathUtils.random(10, PrairieKing.PPM -10);

        Vector2 pos = new Vector2(x,y);

        return pos;
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

    public ArrayList<PowerupModel> getPowerupModels() {
        return powerupModels;
    }

    public void addPowerup(GunPowerups gun) {

    }


}
