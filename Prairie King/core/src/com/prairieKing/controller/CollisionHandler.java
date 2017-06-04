package com.prairieKing.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.prairieKing.model.entities.EntityModel;

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
    public void handleCollisions(EntityModel m1, EntityModel m2) {
        if (m1 != null && m2 != null) {
            if ((m1.getType() == "PROJECTILE" && m2.getType() == "ENEMY") || (m1.getType() == "ENEMY" && m2.getType() == "PROJECTILE")
                    || (m1.getType() == "HERO" && m2.getType() == "ENEMY") || (m1.getType() == "ENEMY" && m2.getType() == "HERO")) {
                m1.kill();
                m2.kill();
            }
            if ((m1.getType() == "POWERUP" && m2.getType() == "HERO") || (m2.getType() == "POWERUP" && m1.getType() == "HERO")) {
                if (m1.getType() == "POWERUP") {
                    m1.activate();
                } else
                    m2.activate();
            }
        } else if (m1 == null) {
            if (m2.getType() == "PROJECTILE") {
                m2.kill();
            }
        } else if (m2 == null) {
            if (m1.getType() == "PROJECTILE") {
                m1.kill();
            }
        }
    }

    /** Not necessary, but implemented.
     *
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {}

    /** Not necessary, but implemented.
     *
     * @param contact
     * @param oldManifold
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    /** Not necessary, but implemented.
     *
     * @param contact
     * @param impulse
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}