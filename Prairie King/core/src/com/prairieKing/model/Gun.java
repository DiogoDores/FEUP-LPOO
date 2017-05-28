package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.controller.bodies.ProjectileBody;
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


    public Gun(World world) {
        test = false;
        timeToShoot = .2f;
        this.world = world;
        SPEED = 1;
        pool = new Pool<ProjectileModel>() {
            @Override
            protected ProjectileModel newObject() {
                return new ProjectileModel(-1, -1);
            }
        };
    }

    public void update() {
        timeToShoot -= SPEED * 0.4f / (Gdx.graphics.getDeltaTime() * 1800);
    }

    public void shoot(float posX, float posY, float vX, float vY) {

        // TODO IMPLEMENTAR MANEIRA DE DAR ESTA TRETA

        checkPowerups();

        if (Gdx.input.isKeyJustPressed(Input.Keys.P) && !test) {
            GunPowerups test = new FireRateGunPowerup(this);
            powerups.add(test);
        }

        if (timeToShoot <= 0) {

            timeToShoot = 0.2f;

            ProjectileModel p = pool.obtain();
            ProjectileBody pb = new ProjectileBody(world ,p);

            pb.setTransform(posX, posY);
            pb.setLinearVelocity(vX, vY);

            projectiles.add(p);
            projectilesBodies.add(pb);

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
}
