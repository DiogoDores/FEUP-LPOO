package com.prairieKing.controller.bodies;


import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.entities.ProjectileModel;
import com.prairieKing.view.GameStage;

public class ProjectileBody extends EntityBody {

    public ProjectileBody(World world, ProjectileModel model) {
        super(world, 0, model);
        super.setUserData(model);
        setHeight(GameStage.PROJECTILE_WIDTH );
        setWidth(GameStage.PROJECTILE_WIDTH );
        createFixture(getBody(), (int) GameStage.PROJECTILE_WIDTH, (int) GameStage.PROJECTILE_WIDTH);
    }
}
