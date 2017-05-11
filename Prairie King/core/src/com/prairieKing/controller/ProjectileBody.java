package com.prairieKing.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.model.ProjectileModel;

public class ProjectileBody extends EntityBody {

    public ProjectileBody(World world, ProjectileModel model) {
        super(world, model);


        createFixture(body, Gdx.graphics.getWidth()/64, Gdx.graphics.getWidth()/36);
    }
}
