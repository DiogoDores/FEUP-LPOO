package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.controller.EnemyBody;
import com.prairieKing.controller.PrairieKing;

import java.util.ArrayList;

public class AIManager {

    private GameLogic gameLogic; // Precisa disto para saber as posições do herói
    private int MAX_ENEMY_NUMBER = 1; // Can be changed as levels increase
    private ArrayList<EnemyModel> enemies = new ArrayList<>();
    private ArrayList<EnemyBody> enemiesBodies = new ArrayList<>();

    private Pool<EnemyModel> enemyModelsPool;

    private int activeNumber;

    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        activeNumber = 0;
        enemyModelsPool = new Pool<EnemyModel>() {
            @Override
            protected EnemyModel newObject() {
                int random = MathUtils.random(1);

                int randomSpawn = MathUtils.random(3);
                String spawnPlaces = "nsew"; // Possível posição
                random = 0;
                if (random == 0) {
                    if (spawnPlaces.charAt(randomSpawn) == 'n')
                        return new BasicWalker((int) PrairieKing.PPM / 2, (int) PrairieKing.PPM + 10);
                    else if (spawnPlaces.charAt(randomSpawn) == 's')
                        return new BasicWalker((int) PrairieKing.PPM / 2, -10);
                    else if (spawnPlaces.charAt(randomSpawn) == 'e')
                        return new BasicWalker((int) PrairieKing.PPM + 10, (int) PrairieKing.PPM / 2);
                    else
                        return new BasicWalker(-10, (int) PrairieKing.PPM / 2);
                } else if (random == 1) {
                    System.out.println(spawnPlaces.charAt(randomSpawn));

                    if (spawnPlaces.charAt(randomSpawn) == 'n')
                        return new FlyingEnemy((int) PrairieKing.PPM / 2, (int) PrairieKing.PPM + 10);
                    else if (spawnPlaces.charAt(randomSpawn) == 's')
                        return new FlyingEnemy((int) PrairieKing.PPM / 2, -10);
                    else if (spawnPlaces.charAt(randomSpawn) == 'e')
                        return new FlyingEnemy((int) PrairieKing.PPM + 10, (int) PrairieKing.PPM / 2);
                    else
                        return new FlyingEnemy(-10, (int) PrairieKing.PPM / 2);
                }
                return null;
            }
        };
    }


    public void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER + 2;
    }

    public void spawn() {
        if (activeNumber < MAX_ENEMY_NUMBER) {
            EnemyModel e = enemyModelsPool.obtain();

            activeNumber++;
            enemies.add(e);
            enemiesBodies.add(new EnemyBody(gameLogic.getWorld(),e));
        }

    }

    public ArrayList<EnemyModel> getEnemies() {
        return enemies;
    }

    public void move() {

        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            for (int j = enemies.size()-1; j >= 0; j--) {
                enemies.remove(enemies.get(j));
                enemiesBodies.remove(enemies.get(j));
            }
        }

        HeroModel hero = gameLogic.getHero();
        if (enemies != null)
            for (EnemyModel e : enemies) {
                int index = enemies.indexOf(e);
                e.move(e,hero);
                enemiesBodies.get(index).setTransform(e.getX(),e.getY());
        }
    }

    public void checkEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isFlaggedForDelete()) {
                for (EnemyBody body : enemiesBodies) {

                    if (body.getUserData() == enemies.get(i)) {
                        System.out.println("Tentou aceder aqui");
                        enemiesBodies.remove(body);
                    }
                }
                enemies.remove(enemies.get(i));

            }
        }
    }

}
