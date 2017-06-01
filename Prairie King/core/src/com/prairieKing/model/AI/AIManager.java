package com.prairieKing.model.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.controller.bodies.EnemyBody;
import com.prairieKing.PrairieKing;
import com.prairieKing.model.entities.BasicWalker;
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.FlyingEnemy;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.entities.HeroModel;
import com.prairieKing.model.entities.ToughEnemy;

import java.util.ArrayList;

public class AIManager {

    private GameLogic gameLogic; // Precisa disto para saber as posições do herói
    private float MAX_ENEMY_NUMBER = 5; // Can be changed as levels increase
    private ArrayList<EnemyModel> enemies = new ArrayList<>();
    private ArrayList<EnemyBody> enemiesBodies = new ArrayList<>();

    private int killCount;
    private boolean hasIncreased;
    private float timeToSpawn;
    private Pool<EnemyModel> enemyModelsPool;
    private char lastSpawned = 'n', last2spawned = 's';


    private int activeNumber;

    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        killCount = 0;
        hasIncreased = false;
        activeNumber = 0;
        timeToSpawn = .2f;

        enemyModelsPool = new Pool<EnemyModel>() {
            @Override
            protected EnemyModel newObject() {
                int random = MathUtils.random(1);
                String spawnPlaces = "nsew"; // Possível posição
                int randomSpawn = MathUtils.random(3);
                while ( spawnPlaces.charAt(randomSpawn) == lastSpawned && spawnPlaces.charAt(randomSpawn) == last2spawned) {
                    randomSpawn = MathUtils.random(3);
                }
                last2spawned = lastSpawned;
                lastSpawned =  spawnPlaces.charAt(randomSpawn);
                float placeRandom = MathUtils.random(-5.0f,5.0f);
                int x, y;
                if (spawnPlaces.charAt(randomSpawn) == 'n') {
                    x = (int) (PrairieKing.PPM / 2 +placeRandom);
                    y = (int) PrairieKing.PPM + 10;
                }
                else if (spawnPlaces.charAt(randomSpawn) == 's') {
                    x = (int) (PrairieKing.PPM / 2 +placeRandom);
                    y = -10;
                }
                else if (spawnPlaces.charAt(randomSpawn) == 'e') {
                    x = (int) PrairieKing.PPM + 10;
                    y = (int) (PrairieKing.PPM / 2 +placeRandom);
                }
                else {
                    x = -10;
                    y = (int) (PrairieKing.PPM / 2 +placeRandom);
                }
                random = 3;

                if (random == 0 && killCount < 100) {
                    return new BasicWalker(x,y,spawnPlaces.charAt(randomSpawn));
                }
                else if (random == 1 && killCount < 250) {
                    return new FlyingEnemy(x, y, spawnPlaces.charAt(randomSpawn));
                }
                else if (random == 0 &&  killCount > 100 && killCount < 400) {
                    return new ToughEnemy(x,y,spawnPlaces.charAt(randomSpawn));
                }
                return null;
            }
        };
    }

    public void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER + 1;
    }

    public void spawn() {
        if (killCount % 20 == 0 && killCount != 0 && !hasIncreased) {
            System.out.println("Aumentei com " + killCount + " e " + killCount % 10);
            hasIncreased = true;
            increaseDifficulty();
        }

        if (activeNumber < MAX_ENEMY_NUMBER && timeToSpawn <= 0.0f) {
            EnemyModel e = enemyModelsPool.obtain();
            System.out.println("Spawned");
            activeNumber++;
            enemies.add(e);
            enemiesBodies.add(new EnemyBody(gameLogic.getWorld(),e));
            timeToSpawn = MathUtils.random(5.0f/MAX_ENEMY_NUMBER, 7.0f/MAX_ENEMY_NUMBER);
        }
        timeToSpawn -= Gdx.graphics.getDeltaTime();

    }

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

    public void checkEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isFlaggedForDelete()) {
                for (int j = 0; j < enemiesBodies.size(); j++) {

                    if (enemiesBodies.get(j).getUserData() == enemies.get(i)) {

                        enemiesBodies.get(j).destroy();
                        enemiesBodies.remove(enemiesBodies.get(j));
                        activeNumber--;
                        killCount++;
                        hasIncreased = false;
                    }
                }
               // enemyModelsPool.free(enemies.get(i));
                enemies.remove(enemies.get(i));

            }
        }
    }

    public float getKillCount() {
        return killCount;
    }

    public ArrayList<EnemyBody> getEnemiesBodies() {
        return enemiesBodies;
    }

}
