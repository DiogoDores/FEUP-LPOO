package com.prairieKing.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;

public class Projectile {

    private Vector2 position, velocity;

    private Circle circle;

    public Projectile() {
        this.position = new Vector2();
        this.velocity = new Vector2();
    }

    public Vector2 getPosition() {return position;}
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
