package com.prairieKing.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.List;


public class Gun {

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    Pool<Projectile> pool;

    private void shoot(Vector2 position, Vector2 velocity) {
        Projectile newBullet = new Projectile(position, velocity);
        Projectile[] newList = new Projectile[projectiles.size()];

        projectiles.toArray(newList);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}
