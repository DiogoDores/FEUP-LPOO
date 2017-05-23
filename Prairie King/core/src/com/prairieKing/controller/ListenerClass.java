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

public class ListenerClass implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        EntityModel modelA = (EntityModel) contact.getFixtureA().getBody().getUserData();
        EntityModel modelB = (EntityModel) contact.getFixtureB().getBody().getUserData();

        System.out.print("Contact between " );

        if (modelA != null) {
            modelA.kill();
            System.out.print(modelA.getType());
        }
        if (modelB != null) {
            modelB.kill();
            System.out.println(" " +modelB.getType());
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
