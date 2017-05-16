package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.controller.EnemyBody;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.controller.ProjectileBody;

import java.util.ArrayList;

public class AIManager {
    private GameLogic gameLogic; // Precisa disto para saber as posições do herói

    private ArrayList<EnemyModel> enemies = new ArrayList<>();
    private ArrayList<EnemyBody> enemiesBodies = new ArrayList<>();

    private Pool<EnemyModel> enemyModelsPool;
    private int activeNumber;


    private int MAX_ENEMY_NUMBER = 1; // Can be changed as levels increase


    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        activeNumber = 0;
        enemyModelsPool = new Pool<EnemyModel>() {
            @Override
            protected EnemyModel newObject() {
                int random = MathUtils.random(1);
                System.out.println("Tentou obter  " + random);

                int randomSpawn = MathUtils.random(3);
                String spawnPlaces = "nsew"; // Possível posição
                random = 0;
                if (random == 0) {
                    System.out.println("Tentou obter BasicWalker");
                    System.out.println(spawnPlaces.charAt(randomSpawn));

                    if (spawnPlaces.charAt(randomSpawn) == 'n')
                        return new BasicWalker((int) PrairieKing.PPM / 2, (int) PrairieKing.PPM + 10);
                    else if (spawnPlaces.charAt(randomSpawn) == 's')
                        return new BasicWalker((int) PrairieKing.PPM / 2, -10);
                    else if (spawnPlaces.charAt(randomSpawn) == 'e')
                        return new BasicWalker((int) PrairieKing.PPM + 10, (int) PrairieKing.PPM / 2);
                    else
                        return new BasicWalker(-10, (int) PrairieKing.PPM / 2);
                } else if (random == 1) {
                    System.out.println("Tentou obter FlyingEnemy");
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
            if (e == null)
                System.out.println("MERDA CRL");
            activeNumber++;
            enemies.add(e);
            enemiesBodies.add(new EnemyBody(gameLogic.getWorld(),e));
        }

    }

    /*
        public void killedEnemy() {
            for (int i = 0 ; i < enemyModelsPool.length; i++) {
                if (enemyModelsPool[i].isFlaggedForDelete) {
                    enemyModelsPool[i] = null;
                    enemyBodies[i] = null
                }
            }
        }
    */
    public ArrayList<EnemyModel> getEnemies() {
        return enemies;
    }

    public void move() {
        HeroModel hero = gameLogic.getHero();
        if (enemies != null)
            for (EnemyModel e : enemies) {
                int index = enemies.indexOf(e);
                e.move(e,hero);
                enemiesBodies.get(index).setTransform(e.getX(),e.getY());

            }

    }

}
