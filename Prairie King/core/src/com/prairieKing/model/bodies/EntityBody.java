package com.prairieKing.model.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.controller.entities.EntityController;

public class EntityBody extends Body{

    private Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    /**
     * Generates an entire Body for a Model.
     *
     * @param world World to add bodies to.
     * @param addr Address.
     * @param model Model associated with the body, the model stores the game logic.
     */
    public EntityBody(World world, long addr, EntityController model) {
        super(world, addr);
        super.setUserData(model);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.linearDamping = 0.0f;
        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    /**
     * Sets the current transform value of the body to match the model's.
     *
     * @param x X Position.
     * @param y Y Position.
     */
    public void setTransform(float x, float y) {
        body.setTransform(x, y, 0);
    }

    /**
     *  Adds a velocity to the body, used in projectiles.
     * @param x Velocity in the X axis.
     * @param y Velocity in the Y axis.
     */
    public void setLinearVelocity(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    /**
     * Creates the fixture with which the body will collide. It houses the characteristics of
     * the body as well, such as the Collision Filter, to select which bodies this body can
     * collide with.
     * @param width Width of the Fixture.
     * @param height Height of the Fixture.
     * @param category Category Bit of Collision Filter
     * @param mask Mask Bit of Collision Filter.
     * @param group Group Bit of Collision Filter.
     */
    final void createFixture(int width, int height , short category, short mask, short group) {

        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(width/1.5f,height/1.5f, new Vector2(width/2 + 1, height/2 + 1), 0);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;
        fixtureDef.filter.groupIndex = group;

        fixture = body.createFixture(fixtureDef);

        polygon.dispose();
    }

    /** When a model is flagged for deletion, this method is invoked,
     * destroying the body and it's associated fixture.
     */
    public void destroy() {
        body.destroyFixture(fixture);
        body.setUserData(null);
        body = null;
        fixtureDef = null;
    }

    /**
     * Returns body.
     * @return Body in this class.
     */
    public Body getBody() {
        return body;
    }
}
