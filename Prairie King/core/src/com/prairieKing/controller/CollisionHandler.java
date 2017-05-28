package com.prairieKing.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.prairieKing.model.entities.EntityModel;

public class CollisionHandler implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        EntityModel modelA = (EntityModel) contact.getFixtureA().getBody().getUserData();
        EntityModel modelB = (EntityModel) contact.getFixtureB().getBody().getUserData();

        handleCollisions(modelA, modelB);
    }

    public void handleCollisions(EntityModel m1, EntityModel m2) {

        if (m1 != null && m2 != null) {

            if ((m1.getType() == "PROJECTILE" && m2.getType() == "ENEMY") || (m1.getType() == "ENEMY" && m2.getType() == "PROJECTILE")) {
                m1.kill();
                m2.kill();
            }

            if ((m1.getType() == "HERO" && m2.getType() == "ENEMY") || (m1.getType() == "ENEMY" && m2.getType() == "HERO")) {
                m1.kill();
                m2.kill();
            }
        }

        else if (m1 == null) {
            if (m2.getType() == "PROJECTILE") {
                m2.kill();
            }
        }

        else if (m2 == null) {
            if (m1.getType() == "PROJECTILE") {
                m1.kill();
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
