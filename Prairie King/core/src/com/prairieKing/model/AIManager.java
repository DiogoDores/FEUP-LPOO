package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.prairieKing.controller.EnemyBody;
import com.prairieKing.controller.PrairieKing;

public class AIManager {

    private GameLogic gameLogic; // Precisa disto para saber as posições do herói
    private int MAX_ENEMY_NUMBER = 1; // Can be changed as levels increase
    private EnemyModel[] enemies = new EnemyModel[MAX_ENEMY_NUMBER];
    private EnemyBody[] enemyBodies = new EnemyBody[MAX_ENEMY_NUMBER];

    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER + 2;
    }

    public void spawn() {
        enemies[0] = (new BasicWalker((int) PrairieKing.PPM/2, 40));
        enemyBodies[0] = new EnemyBody(gameLogic.getWorld(), enemies[0]);

    }

    public EnemyModel[] getEnemies() {
        return enemies;
    }

    public void move() {
        HeroModel hero = gameLogic.getHero();
        if (enemies != null)
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    enemies[i].move(enemies[i], hero);
                    enemyBodies[i].setTransform(enemies[i].getX(), enemies[i].getY());
                }
            }

    }

}
