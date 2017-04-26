package com.mygdx.game;

import java.util.Vector;

/**
 * Created by petre on 26/04/2017.
 */

public class AIManager {

    private int MAX_ENEMY_NUMBER = 13; // Can be changed as levels increase
    private Vector<Enemy> enemies;

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER +2;
    }

}
