package com.prairieKing.controller.bodies;


import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.model.entities.ProjectileModel;
import com.prairieKing.view.GameStage;

public class ProjectileBody extends EntityBody {

    public ProjectileBody(World world, ProjectileModel model) {
        super(world, 0, model);
        createFixture((int) Constants.PROJECTILE_WIDTH, (int) Constants.PROJECTILE_WIDTH, Constants.CATEGORY_PROJECTILE, Constants.MASK_PROJECTILE, (short) 0);

    }
}
