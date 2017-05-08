package com.prairieKing.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.List;


public class Gun {

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private Pool<Projectile> pool;
    private World world;


    public Gun() {
        this.world = world;
        //TODO Instanciar isto aqui, avançar com projecteis
        pool = new Pool<Projectile>() {
            @Override
            protected Projectile newObject() {
                return new Projectile(world, new Vector2(10,10));
            }
        };
    }

    public void shoot(Vector2 position, Vector2 velocity) {
        Projectile p = new Projectile(world, velocity);
        p.setPosition(position.x, position.y);
        projectiles.add(p);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
