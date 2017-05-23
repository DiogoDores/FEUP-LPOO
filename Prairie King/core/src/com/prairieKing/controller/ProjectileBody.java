package com.prairieKing.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.model.ProjectileModel;
import com.prairieKing.view.GameStage;

public class ProjectileBody extends EntityBody {

    @Override
    public Object getUserData() {
        return "PROJECTILE";
    }

    public ProjectileBody(World world, ProjectileModel model) {
        super(world, 0, model);
        setHeight(GameStage.PROJECTILE_WIDTH );
        setWidth(GameStage.PROJECTILE_WIDTH );
        createFixture(body, (int) GameStage.PROJECTILE_WIDTH, (int) GameStage.PROJECTILE_WIDTH);
    }
}
