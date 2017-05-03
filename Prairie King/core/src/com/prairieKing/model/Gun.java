package com.prairieKing.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.List;


public class Gun {

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    Pool<Projectile> pool;

    public Gun() {
        //TODO Instanciar isto aqui, avançar com projecteis
        //pool = Pool(100);
    }

    public void shoot(Vector2 position, Vector2 velocity) {
       /* Projectile p = pool.obtain();

        p.setPosition(position);
        p.setVelocity(velocity);
        p.display();*/
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
