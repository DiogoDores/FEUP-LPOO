package com.prairieKing.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by petre on 02/05/2017.
 */

public class Projectile {

    private Vector2 position, velocity;

    private Circle circle;

    public Projectile(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }
}
