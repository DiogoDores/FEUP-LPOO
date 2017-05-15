package com.prairieKing.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;


public class EnemyBody extends EntityBody {



    public EnemyBody(World world, EntityModel model) {
        super(world, model);

        createFixture(body, (int)PrairieKing.PPM/32, (int)PrairieKing.PPM/32);
    }
}
