package com.prairieKing.test;

import com.badlogic.gdx.Gdx;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.AI.AIManager;
import com.prairieKing.controller.entities.EntityController;
import com.prairieKing.controller.entities.HeroController;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest extends GameTest{

    @Test
    public void killHero() {
        PrairieKing prairieKing = new PrairieKing();
        assertEquals(0, PrairieKing.currentState);
        GameLogic game = new GameLogic(prairieKing, true);

        EntityController entity = game.getHero();
        entity.kill();

        assertEquals(0, game.getHero().getLives());
    }

    @Test
    public void addLife() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing, true);
        HeroController hero = game.getHero();

        int lives = game.getHero().getLives() + 1;

        hero.addLife();

        assertEquals(lives, game.getHero().getLives());
    }

    @Test
    public void moveHero() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing, true);
        HeroController hero = game.getHero();

        float x, y;

        x = hero.getX();
        hero.setLeft(true);
        hero.move();

        assertNotEquals(x, hero.getX());

        hero.setLeft(false);
        x = hero.getX();
        hero.setRight(true);
        hero.move();

        assertNotEquals(x, hero.getX());

        hero.setRight(false);
        y = hero.getY();
        hero.setUp(true);
        hero.move();

        assertNotEquals(y, hero.getY());

        hero.setUp(false);
        y = hero.getY();
        hero.setDown(true);
        hero.move();

        assertNotEquals(y, hero.getY());
    }

    @Test
    public void createBullet() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing, true);
        Gun gun = new Gun(game);

        gun.shoot(50, 50, 10, 10);

        assertNotNull(gun.getProjectiles().get(0));
    }

    @Test
    public void deleteBullet() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing, true);
        Gun gun = game.getGun();

        gun.shoot(50, 50, 10, 10);
        gun.getProjectiles().get(0).kill();
        gun.checkBullets();

        assertTrue(gun.getProjectiles().isEmpty());
    }

    @Test
    public void createEnemy() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing, true);
        AIManager enemy = game.getAI();

        float timeToSpawn = 2.0f;

        while(timeToSpawn > 0) {
            timeToSpawn -= Gdx.graphics.getDeltaTime();
            enemy.spawn();
        }

        assertNotNull(enemy.getEnemies().get(0));
    }

    @Test
    public void deleteEnemy() {
        PrairieKing prairieKing = new PrairieKing();
        GameLogic game = new GameLogic(prairieKing, true);
        AIManager enemy = game.getAI();

        float timeToSpawn = 2.0f;

        while (timeToSpawn > 0) {
            timeToSpawn -= Gdx.graphics.getDeltaTime();
            enemy.spawn();
        }

        int size = enemy.getEnemies().size();

        for (int i = 0; i < enemy.getEnemies().size(); i++){
            enemy.getEnemies().get(i).kill();
            enemy.checkEnemies();
        }

        assertNotEquals(size, enemy.getEnemies().size());
    }
}