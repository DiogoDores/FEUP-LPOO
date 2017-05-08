package com.prairieKing.controller;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;

public class EntityBody {

    final Body body;

    EntityBody(World world, EntityModel model) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());

        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setTransform(float x, float y) {
        body.setTransform(x, y, 0);
    }

    public void setLinearVelocity(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    public Object getUserData() {
        return body.getUserData();
    }

    final void createFixture(Body body, int width, int height) {
        // Transform pixels into meters, center and invert the y-coordinate

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(width,height); // CUIDADO COM ESTES VALORES

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        body.createFixture(fixtureDef);

        polygon.dispose();
    }
}
