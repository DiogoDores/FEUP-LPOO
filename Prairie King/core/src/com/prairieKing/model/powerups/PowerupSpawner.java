package com.prairieKing.model.powerups;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.controller.bodies.PowerupBody;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.entities.HeroModel;
import com.prairieKing.model.entities.PowerupModel;
import com.prairieKing.model.powerups.GunPowerups;
import com.prairieKing.model.powerups.HeroPowerups;

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

        timeToSpawn = 5;  // TODO Trocar este 5 por generateRandom(), só para testes

    }

    public void update() {
        timeToSpawn -= Gdx.graphics.getDeltaTime();
        checkPowerups();

        if (timeToSpawn <= 0) {
            spawn();
            timeToSpawn = generateRandom();
        }

    }

    public float generateRandom() {
        float rand = MathUtils.random(10.0f, 20.0f);
        return rand;
    }

    public void spawn() {

        // TODO ADICIONAR AQUI POWERUPS FUTUROS

        int r = MathUtils.random(1);
        Vector2 position = randomPos();

        PowerupModel model = new PowerupModel(position.x,position.y, gameLogic);

        r = 1;
        if (r == 0) { // Speed
            model.powerupType("GUN SPEED");
        }
        else if (r == 1)
            model.powerupType("GUN SHOTGUN");
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
