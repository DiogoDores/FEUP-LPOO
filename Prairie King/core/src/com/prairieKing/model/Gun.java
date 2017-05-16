package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.controller.ProjectileBody;

import java.util.ArrayList;
import java.util.List;


public class Gun {

    private ArrayList<ProjectileBody> projectiles = new ArrayList<ProjectileBody>();private Pool<ProjectileBody> pool;
    private World world;
    private float timeToShoot;


    public Gun(World world) {
        timeToShoot = 1;
        this.world = world;

        //TODO Instanciar isto aqui, avançar com projecteis

        pool = new Pool<ProjectileBody>() {
            @Override
            protected ProjectileBody newObject() {
                return new ProjectileBody(world, new ProjectileModel(-1,-1));
            }
        };
    }

    public void update() {
        timeToShoot -= 0.4f/ (Gdx.graphics.getFramesPerSecond()/2);
    }

    public void shoot(float posX, float posY, float vX, float vY) {

        if (timeToShoot <= 0) {
            ProjectileBody p = pool.obtain();

            p.setTransform(posX, posY);
            p.setLinearVelocity(vX, vY);

            projectiles.add(p);
            timeToShoot = 0.4f;
        }
    }

    public List<ProjectileBody> getProjectiles() {
        return projectiles;
    }
}
