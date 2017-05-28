package com.prairieKing.controller.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.model.entities.EntityModel;
import com.prairieKing.view.GameStage;


public class PowerupBody extends EntityBody {

    public PowerupBody(World world, EntityModel model) {
        super(world, 0, model);
        super.setUserData(model);
        createFixture(getBody(), (int) Constants.POWERUP_WIDTH ,(int) Constants.POWERUP_WIDTH, Constants.CATEGORY_POWERUP, Constants.MASK_POWERUP,(short) 0 );

    }
}
