package com.prairieKing.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class ProjectileModel extends EntityModel {

    float x, y;

    public ProjectileModel(float x, float y) {
        super(x, y);
        this.x = x; this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }



}
