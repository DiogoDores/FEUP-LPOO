package com.prairieKing.controller.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.model.entities.EntityModel;
import com.prairieKing.view.GameStage;

public class PowerupBody extends EntityBody {

    /** Invoked on CollisionHandler, used to get body type.
     *
     * @return string of the body type.
     */
    public PowerupBody(World world, EntityModel model) {
        super(world, 0, model);
        createFixture((int) Constants.POWERUP_WIDTH ,(int) Constants.POWERUP_WIDTH, Constants.CATEGORY_POWERUP, Constants.MASK_POWERUP,(short) 0 );

    }
}
