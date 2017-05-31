package com.prairieKing.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.Constants;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.controller.bodies.ProjectileBody;
import com.prairieKing.model.entities.HeroModel;
import com.prairieKing.model.entities.ProjectileModel;
import com.prairieKing.model.powerups.FireRateGunPowerup;
import com.prairieKing.model.powerups.GunPowerups;

import java.util.ArrayList;
import java.util.List;


public class Gun {

    private ArrayList<ProjectileBody> projectilesBodies = new ArrayList<ProjectileBody>();
    private ArrayList<ProjectileModel> projectiles = new ArrayList<ProjectileModel>();
    private ArrayList<GunPowerups> powerups = new ArrayList<>();
    private Pool<ProjectileModel> pool;
    private World world;
    private float timeToShoot;
    private float SPEED;
    private boolean test;
    private HeroModel hero;
    private int shape;

    private boolean leftB, rightB, upB, downB; // Gun Methods



    public Gun(GameLogic gameLogic) {
        test = false;
        shape = 1;
        timeToShoot = .5f;
        this.world = gameLogic.getWorld();
        this.hero = gameLogic.getHero();
        SPEED = 1;
        pool = new Pool<ProjectileModel>() {
            @Override
            protected ProjectileModel newObject() {
                return new ProjectileModel(-1, -1);
            }
        };
    }

    public void update() {
        timeToShoot -= SPEED * Gdx.graphics.getDeltaTime() / 2;
        directionShoot();
    }

    public void directionShoot() {
        int x = 0, y = 0;
        if (leftB)
            x = x - (int) PrairieKing.PPM * 2;
        if (rightB)
            x = x + (int) PrairieKing.PPM * 2;
        if (upB)
            y = y + (int) PrairieKing.PPM * 2;
        if (downB)
            y = y - (int) PrairieKing.PPM * 2;

        if ((x == 0 && y != 0) || (x != 0 && y == 0) || (x != 0 && y != 0)) {
            if (rightB) {
                if (upB)
                    shoot(hero.getX() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2, hero.getY() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2, x, y);
                else if (downB)
                    shoot(hero.getX() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2, hero.getY() - Constants.PROJECTILE_WIDTH / 2, x, y);
                else
                    shoot(hero.getX() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2, hero.getY() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2, x, y);
            }
            if (leftB) {
                if (upB)
                    shoot(hero.getX() - Constants.PROJECTILE_WIDTH / 2, hero.getY() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2, x, y);
                else if (downB)
                    shoot(hero.getX() - Constants.PROJECTILE_WIDTH / 2, hero.getY() - Constants.PROJECTILE_WIDTH / 2, x, y);
                else
                    shoot(hero.getX() - Constants.PROJECTILE_WIDTH / 2, hero.getY() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2, x, y);
            } else if (upB && !rightB && !leftB)
                shoot(hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2, hero.getY() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2, x, y);
            else if (downB && !rightB && !leftB)
                shoot(hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2, hero.getY() - Constants.PROJECTILE_WIDTH / 2, x, y);
        }
    }  // AQUI chamo o shoot com valores bonitos


    public  void shoot(float posX, float posY, float vX, float vY) {

        checkPowerups();

        if (timeToShoot <= 0) {
            if (shape == 1) {
                timeToShoot = 0.2f;

                ProjectileModel p = pool.obtain();
                ProjectileBody pb = new ProjectileBody(world, p);

                pb.setTransform(posX, posY);
                pb.setLinearVelocity(vX, vY);

                projectiles.add(p);
                projectilesBodies.add(pb);
            }
            else {
                for (int i = 0; i < shape; i++) {

                }
            }
        }
    }

    public List<ProjectileModel> getProjectiles() {
        return projectiles;
    }

    public void checkBullets() {
        for (int i = 0; i < projectiles.size() ; i++) {
            if (projectiles.get(i) != null) {
                if (projectiles.get(i).isFlaggedForDelete()) {
                    for (int j = 0 ; j < projectilesBodies.size() ; j++) {
                        if (projectilesBodies.get(j).getUserData() == projectiles.get(i)) {
                            projectilesBodies.get(j).destroy();
                            projectilesBodies.remove(j);
                        }
                    }
                    projectiles.remove(i);
                }
            }
        }
    }

    public void setSpeed(float speed) {
        SPEED = speed;
    }

    public float getSPEED() {
        return SPEED;
    }

    public void setShape() {
        // TODO não sei como fazer bem isto por agora tbh
    }

    public void checkPowerups() {
        for (int i = 0; i < powerups.size() ; i++) {
            powerups.get(i).update();
            if (powerups.get(i).getEffectTime() <= 0) {
                powerups.get(i).removeEffect();
                powerups.remove(i);
            }
        }
    }

    public void addPowerup(GunPowerups powerup) {
        powerups.add(powerup);
    }


    public void setLeftB(boolean leftB) {
        this.leftB = leftB;
    }

    public void setRightB(boolean rightB) {
        this.rightB = rightB;
    }

    public void setUpB(boolean upB) {
        this.upB = upB;
    }

    public void setDownB(boolean downB) {
        this.downB = downB;
    }
}