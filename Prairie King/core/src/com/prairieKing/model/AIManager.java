package com.prairieKing.model;

import com.badlogic.gdx.Gdx;

public class AIManager {

    private GameLogic gameLogic; // Precisa disto para saber as posições do herói
    private int MAX_ENEMY_NUMBER = 1; // Can be changed as levels increase
    private Enemy[] enemies = new Enemy[MAX_ENEMY_NUMBER];

    public AIManager(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void increaseDifficulty() {
        MAX_ENEMY_NUMBER = MAX_ENEMY_NUMBER + 2;
    }

    public void spawn() {
        enemies[0] = (new Enemy(Gdx.graphics.getWidth() / 2, 16, "Basic_Walker"));
        //enemies[1] = (new Enemy(Gdx.graphics.getWidth() / 2, -16, "Basic_Walker"));
        //enemies[2] = (new Enemy(Gdx.graphics.getWidth() / 2, -16, "Basic_Walker"));
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void move() {
        if (enemies != null)
            for (int i = 0; i < enemies.length; i++) {
                if (enemies[i] != null) {
                    int x = enemies[i].getX();
                    int y = enemies[i].getY();
                    if (x > gameLogic.getHero().getX())
                        enemies[i].setX(x - 1);
                    else if (x < gameLogic.getHero().getX())
                        enemies[i].setX(x + 1);
                    if (y > gameLogic.getHero().getY())
                        enemies[i].setY(y - 1);
                    else if (y < gameLogic.getHero().getY())
                        enemies[i].setY(y + 1);

                }

            }

    }

}
