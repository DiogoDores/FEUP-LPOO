package com.prairieKing.model.powerups;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.powerups.GunPowerups;
import com.prairieKing.model.powerups.HeroPowerups;

import java.util.ArrayList;

/**
 * Created by petre on 28/05/2017.
 */

public class PowerupSpawner {

    private ArrayList<GunPowerups> gunPowerups;
    private GameLogic gameLogic;
    private ArrayList<HeroPowerups> heroPowerups;
    private float timeToSpawn;

    public PowerupSpawner(GameLogic gameLogic) {
        gunPowerups = new ArrayList<>();
        heroPowerups = new ArrayList<>();
        this.gameLogic = gameLogic;
    }

    public void update() {
        timeToSpawn -= Gdx.graphics.getDeltaTime();
        System.out.println(timeToSpawn);

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

        int r = MathUtils.random(3);
    }

}
