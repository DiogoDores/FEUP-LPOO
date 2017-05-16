package com.prairieKing.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.model.ProjectileModel;
import com.prairieKing.view.GameStage;

public class ProjectileBody extends EntityBody {

    @Override
    public Object getUserData() {
        return super.getUserData();
    }

    public ProjectileBody(World world, ProjectileModel model) {
        super(world, model);


        createFixture(body, (int) GameStage.PROJECTILE_WIDTH, (int) GameStage.PROJECTILE_WIDTH);
    }
}
