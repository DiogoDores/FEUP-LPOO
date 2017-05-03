package com.prairieKing.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

    private Vector2 position, velocity;

    private Circle circle;

    public Projectile(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void display() {
        System.out.println(velocity.toString());
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
