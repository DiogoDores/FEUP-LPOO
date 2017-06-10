package com.controller.AI;

import com.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.controller.entities.EnemyController;
import com.controller.entities.FlyingEnemy;
import com.model.bodies.EnemyBody;
import com.PrairieKing;
import com.controller.entities.BasicWalker;
import com.model.GameLogic;
import com.controller.entities.HeroController;
import com.controller.entities.ToughEnemy;

import java.util.ArrayList;

/**
 * All the responsabilites for difficulty, spawn,
 * and active enemies are here.
 */
public class AIManager {

    private GameLogic gameLogic;
    private float MAX_ENEMY_NUMBER = 6;
    private ArrayList<EnemyController> enemies = new ArrayList<>();
    private ArrayList<EnemyBody> enemyBodies = new ArrayList<>();

    private int killCount;
    private boolean hasIncreased;
    private float timeToSpawn;
    private char lastSpawned = 'n', last2spawned = 's';

    private boolean hasWon;
    private float resetTime;

    private int activeNumber;

    /**
     * Constructor for AIManager.
     *
     * @param gameLogic GameLogic is necessary to access hero's position.
     */
    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        killCount = 0;
        hasIncreased = false;
        hasWon = false;
        activeNumber = 0;
        timeToSpawn = .2f;
    }

    /**
     * Increases difficulty.
     */
    private void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER + 1;
        if (killCount % 100 == 0) {
            MAX_ENEMY_NUMBER -= 4;
        }
    }

    /**
     * Returns a vector with random values holding the position
     * to spawn.
     *
     * @param direction Initial direction the enemy will follow.
     * @return Vector holding coordinates.
     */
    private Vector2 randomizeSpawn(char direction) {
        float x, y;
        float placeRandom = MathUtils.random(-3.0f, 3.0f);

        if (direction == 'n') {
            x = (int) (PrairieKing.PPM / 2 + placeRandom);
            y = (int) PrairieKing.PPM + 10;
        } else if (direction == 's') {
            x = (int) (PrairieKing.PPM / 2 + placeRandom);
            y = -10;
        } else if (direction == 'e') {
            x = (int) PrairieKing.PPM + 10;
            y = (int) (PrairieKing.PPM / 2 + placeRandom);
        } else {
            x = -10;
            y = (int) (PrairieKing.PPM / 2 + placeRandom);
        }

        return new Vector2(x, y);

    }

    /**
     * Spawns an enemy when it is necessary, and checks the Win condition.
     */
    public void spawn() {
        resetTime -= Gdx.graphics.getDeltaTime();
        if (killCount > 400) {
            if (activeNumber == 0 && !hasWon) {
                gameLogic.win();
                hasWon = true;
            }
        } else {
            if (killCount % 20 == 0 && killCount != 0 && !hasIncreased) {
                hasIncreased = true;
                increaseDifficulty();
            }

            if (activeNumber < MAX_ENEMY_NUMBER && timeToSpawn <= 0.0f && resetTime <= 0) {
                EnemyController e = summonEnemy();
                if (e != null) {
                    activeNumber++;
                    enemies.add(e);
                    enemyBodies.add(new EnemyBody(gameLogic.getWorld(), e));
                    timeToSpawn = MathUtils.random(5.0f / MAX_ENEMY_NUMBER, 7.0f / MAX_ENEMY_NUMBER);

                }
            }
            timeToSpawn -= Gdx.graphics.getDeltaTime();
        }
    }

    /**
     * Summons an Enemy.
     *
     * @return new Enemy to be spawned.
     */
    private EnemyController summonEnemy() {
        int random;
        String spawnPlaces = "nsew";
        if (killCount < 100)
            random = MathUtils.random(1);
        else
            random = MathUtils.random(2);
        int randomSpawn = MathUtils.random(3);
        while (spawnPlaces.charAt(randomSpawn) == lastSpawned && spawnPlaces.charAt(randomSpawn) == last2spawned) {
            randomSpawn = MathUtils.random(3);
        }

        last2spawned = lastSpawned;
        lastSpawned = spawnPlaces.charAt(randomSpawn);

        Vector2 position = randomizeSpawn(spawnPlaces.charAt(randomSpawn));

        if (random == 0 && killCount <= 450) {
            return new BasicWalker(position.x, position.y, spawnPlaces.charAt(randomSpawn));
        } else if (random == 1 && killCount < 250) {
            return new FlyingEnemy(position.x, position.y, spawnPlaces.charAt(randomSpawn));
        } else if (random == 2 && killCount >= 100 && killCount <= 450) {
            return new ToughEnemy(position.x, position.y, spawnPlaces.charAt(randomSpawn));
        }
        return null;
    }

    /**
     * Returns the active Enemy list for the GameStage.
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
                e.move(e, hero);
                enemyBodies.get(index).setTransform(e.getX(), e.getY());
            }
    }

    /**
     * Checks whether or not enemies are flagged for delete.
     * If they are, it removes them from the active enemy list
     * and destroys their body.
     */
    public void checkEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isFlaggedForDelete() && enemies.get(i).getAnimationDeath() <= 0) {
                for (int j = 0; j < enemyBodies.size(); j++) {
                    if (enemyBodies.get(j).getUserData() == enemies.get(i)) {
                        enemyBodies.get(j).destroy();
                        enemyBodies.remove(enemyBodies.get(j));
                        activeNumber--;
                        killCount++;
                        hasIncreased = false;
                    }
                }
                enemies.remove(enemies.get(i));
            }
        }
    }

    /**
     * Gets player kill count.
     *
     * @return killCount, or the number of enemies the player has killed.
     */
    public float getKillCount() {
        return killCount;
    }

    /**
     * Gets all the bodies.
     *
     * @return Active Enemy Bodies.
     */
    public ArrayList<EnemyBody> getEnemiesBodies() {
        return enemyBodies;
    }

    public void removeEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).permadeath();
        }
        resetTime = Constants.DELAY_TIME_ON_COLLISION_WITH_HERO;
    }

}
