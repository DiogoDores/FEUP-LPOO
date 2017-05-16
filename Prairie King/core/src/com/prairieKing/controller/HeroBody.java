package com.prairieKing.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.view.GameStage;

public class HeroBody extends EntityBody{

    public HeroBody(World world, EntityModel model) {
        super(world, model);

        createFixture(body, (int) GameStage.HERO_WIDTH, (int) GameStage.HERO_WIDTH);
    }
}
