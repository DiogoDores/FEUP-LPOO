package com.prairieKing.controller.AI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.model.bodies.EnemyBody;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.entities.BasicWalker;
import com.prairieKing.controller.entities.EnemyController;
import com.prairieKing.controller.entities.FlyingEnemy;
import com.prairieKing.model.GameLogic;
import com.prairieKing.controller.entities.HeroController;
import com.prairieKing.controller.entities.ToughEnemy;

import java.util.ArrayList;

public class AIManager {

    private GameLogic gameLogic;
    private float MAX_ENEMY_NUMBER = 5;
    private ArrayList<EnemyController> enemies = new ArrayList<>();
    private ArrayList<EnemyBody> enemiesBodies = new ArrayList<>();

    private int killCount;
    private boolean hasIncreased;
    private float timeToSpawn;
    private Pool<EnemyController> enemyModelsPool;
    private char lastSpawned = 'n', last2spawned = 's';

    private boolean hasWon;

    private int activeNumber;

    /** Constructor for AIManager. All the responsabilites for difficulty, spawn,
     * and active enemies are here.
     *
     * @param gameLogic GameLogic is necessary to access hero's position.
     */
    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        killCount =0;
        hasIncreased = false;
        hasWon = false;
        activeNumber = 0;
        timeToSpawn = .2f;

        enemyModelsPool = new Pool<EnemyController>() {
            @Override
            protected EnemyController newObject() {
                int random = MathUtils.random(1);
                String spawnPlaces = "nsew"; // Possível posição
                int randomSpawn = MathUtils.random(3);
                while ( spawnPlaces.charAt(randomSpawn) == lastSpawned && spawnPlaces.charAt(randomSpawn) == last2spawned) {
                    randomSpawn = MathUtils.random(3);
                }

                last2spawned = lastSpawned;
                lastSpawned =  spawnPlaces.charAt(randomSpawn);

                Vector2 position = randomizeSpawn(spawnPlaces.charAt(randomSpawn));

                if (random == 0 && killCount < 100) {
                    return new BasicWalker(position.x, position.y,spawnPlaces.charAt(randomSpawn));
                }
                else if (random == 1 && killCount < 250) {
                    return new FlyingEnemy(position.x, position.y, spawnPlaces.charAt(randomSpawn));
                }
                else if (random == 0 &&  killCount >= 100 && killCount < 400) {
                    return new ToughEnemy(position.x, position.y,spawnPlaces.charAt(randomSpawn));
                }
                return null;
            }
        };
    }

    /** Increases difficulty.
     */
    private void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER + 1; if(killCount == 160) {
            MAX_ENEMY_NUMBER -= 4;
        }
    }

    /**Returns a vector with random values holding the position
     * to spawn.
     *
     * @param direction Initial direction the enemy will follow.
     * @return Vector holding coordinates.
     */
    private Vector2 randomizeSpawn(char direction) {
        float x, y;
        float placeRandom = MathUtils.random(-3.0f,3.0f);

        if (direction == 'n') {
            x = (int) (PrairieKing.PPM / 2 +placeRandom);
            y = (int) PrairieKing.PPM + 10;
        }
        else if (direction == 's') {
            x = (int) (PrairieKing.PPM / 2 +placeRandom);
            y = -10;
        }
        else if (direction == 'e') {
            x = (int) PrairieKing.PPM + 10;
            y = (int) (PrairieKing.PPM / 2 +placeRandom);
        }
        else {
            x = -10;
            y = (int) (PrairieKing.PPM / 2 +placeRandom);
        }

        return new Vector2(x,y);

    }

    /**
     * Spawns an enemy when it is necessary, and checks the Win condition.
     */
    public void spawn() {

        if (killCount > 1  ) {
            if (activeNumber == 0 && !hasWon) {
                gameLogic.win();
                hasWon = true;
            }
        }
        else {
            if (killCount % 20 == 0 && killCount != 0 && !hasIncreased) {
               // System.out.println("Aumentei com " + killCount + " e " + killCount % 10);
                hasIncreased = true;
                increaseDifficulty();
            }

            if (activeNumber < MAX_ENEMY_NUMBER && timeToSpawn <= 0.0f) {
                EnemyController e = enemyModelsPool.obtain();
                activeNumber++;
                enemies.add(e);
                enemiesBodies.add(new EnemyBody(gameLogic.getWorld(), e));
                timeToSpawn = MathUtils.random(5.0f / MAX_ENEMY_NUMBER, 7.0f / MAX_ENEMY_NUMBER);
            }
            timeToSpawn -= Gdx.graphics.getDeltaTime();
        }
    }

    /**Returns the active Enemy list for the GameStage.
     *
     * @return Active Enemy list.
     */
    public ArrayList<EnemyController> getEnemies() {
        return enemies;
    }

    /**
     * Invokes all the move methods from all active Enemies.
     */
    public void move() {
        HeroController hero = gameLogic.getHero();
        if (enemies != null)
            for (EnemyController e : enemies) {
                int index = enemies.indexOf(e);
                e.move(e,hero);
                enemiesBodies.get(index).setTransform(e.getX(),e.getY());
        }
    }

    /**
     * Checks whether or not enemies are flagged for delete.
     * If they are, it removes them from the active enemy list
     * and destroys their body.
     */
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

                enemies.remove(enemies.get(i));
                // enemyModelsPool.free(enemies.get(i)); TODO PRECISO DE ARRANJAR ISTO, SENAO POOL NAO ESTA IMPLEMENTADO!!!
             }
        }
    }

    /** Gets player kill count.
     *
     * @return killCount, or the number of enemies the player has killed.
     */
    public float getKillCount() {
        return killCount;
    }

    /** Gets all the bodies.
     *
     * @return Active Enemy Bodies.
     */
    public ArrayList<EnemyBody> getEnemiesBodies() {
        return enemiesBodies;
    }

}
