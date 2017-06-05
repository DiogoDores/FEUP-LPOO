package com.prairieKing.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.prairieKing.controller.entities.EntityModel;

public class CollisionHandler implements ContactListener {

    /**
     * Triggered when Fixtures overlap.
     *
     * @param contact Information about collision.
     */
    @Override
    public void beginContact(Contact contact) {
        EntityModel modelA = (EntityModel) contact.getFixtureA().getBody().getUserData();
        EntityModel modelB = (EntityModel) contact.getFixtureB().getBody().getUserData();

        handleCollisions(modelA, modelB);
    }

    /**
     * Contact has taken place, this decides what should be done, depending
     * on the two fixtures that overlapped.
     *
     * @param m1 Model 1 of collision.
     * @param m2 Model 2 of collision.
     */
    private void handleCollisions(EntityModel m1, EntityModel m2) {
        if (m1 != null && m2 != null) {
            if ((m1.getType().equals("PROJECTILE") && m2.getType().equals("ENEMY")) || (m1.getType().equals("ENEMY") && m2.getType().equals("PROJECTILE"))
                    || (m1.getType().equals("HERO") && m2.getType().equals("ENEMY")) || (m1.getType().equals("ENEMY") && m2.getType().equals("HERO"))) {
                m1.kill();
                m2.kill();
            }
            if ((m1.getType().equals("POWERUP") && m2.getType().equals("HERO")) || (m2.getType().equals("POWERUP") && m1.getType().equals("HERO"))) {
                if (m1.getType().equals("POWERUP")) {
                    m1.activate();
                } else
                    m2.activate();
            }
        } else if (m1 == null) {
            if (m2.getType().equals("PROJECTILE")) {
                m2.kill();
            }
        } else {
            if (m1.getType().equals("PROJECTILE")) {
                m1.kill();
            }
        }
    }

    /** Not necessary, but implemented.
     */
    @Override
    public void endContact(Contact contact) {}

    /** Not necessary, but implemented.
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    /** Not necessary, but implemented.
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}