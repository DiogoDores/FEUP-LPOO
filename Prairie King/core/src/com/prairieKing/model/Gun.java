package com.prairieKing.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.List;


public class Gun {

    private ArrayList<ProjectileModel> projectiles = new ArrayList<ProjectileModel>();
    private Pool<ProjectileModel> pool;
    private World world;


    public Gun() {
        this.world = world;
        //TODO Instanciar isto aqui, avançar com projecteis
        pool = new Pool<ProjectileModel>() {
            @Override
            protected ProjectileModel newObject() {
                return new ProjectileModel(-1,-1);
            }
        };
    }

    public void shoot(Vector2 position, Vector2 velocity) {
        ProjectileModel p = pool.obtain();
        p.setPosition(position.x, position.y);
        projectiles.add(p);
    }

    public List<ProjectileModel> getProjectiles() {
        return projectiles;
    }
}
