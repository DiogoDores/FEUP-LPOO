package com.prairieKing.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.prairieKing.model.EntityModel;

/**
 * Created by petre on 16/05/2017.
 */

public class CollisionHandler implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        EntityModel modelA = (EntityModel) contact.getFixtureA().getBody().getUserData();
        EntityModel modelB = (EntityModel) contact.getFixtureB().getBody().getUserData();

        handleCollisions(modelA,modelB);


    }

    public void handleCollisions(EntityModel m1, EntityModel m2) {

        // Ignore collisions between hero and projectile

        if (m1 != null && m2 != null) {
            if (m1.getType() == "PROJECTILE" && m2.getType() == "HERO")
                m1.kill();
            else if (m2.getType() == "PROJECTILE" && m1.getType() == "HERO")
                m2.kill();
            System.out.println("Merda de colisões ignoradas entre " + m1.getType() + " e " + m2.getType());
            return;
        }

        if (m1 != null) {
            if (m1.getType() == "PROJECTILE" || m1.getType() == "ENEMY" || m1.getType() == "HERO")
                m1.kill();
            System.out.println("Depois m1 " + m1.isFlaggedForDelete());


        }
        if (m2 != null) {
            System.out.println("Antes m2 " + m2.isFlaggedForDelete());
            if (m2.getType() == "PROJECTILE" || m2.getType() == "ENEMY" || m1.getType() == "HERO")
                m2.kill();
            System.out.println("Depois m2 " + m2.isFlaggedForDelete());
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
