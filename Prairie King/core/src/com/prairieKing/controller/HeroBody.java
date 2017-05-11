package com.prairieKing.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;

public class HeroBody extends EntityBody{

    public HeroBody(World world, EntityModel model) {
        super(world, model);

        createFixture(body, Gdx.graphics.getWidth()/32, Gdx.graphics.getWidth()/18);
    }
}
