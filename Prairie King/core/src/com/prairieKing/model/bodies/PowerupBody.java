package com.prairieKing.model.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.controller.entities.EntityController;


public class PowerupBody extends EntityBody {

    /**Creates a body for a Powerup, based on a Model, as well as it's fixture.
     *
     * @param world needed for EntityBody's constructor.
     * @param model Model with which the body is going to be associated with.
     */
    public PowerupBody(World world, EntityController model) {
        super(world, 0, model);
        createFixture((int) Constants.POWERUP_WIDTH ,(int) Constants.POWERUP_WIDTH, Constants.CATEGORY_POWERUP, Constants.MASK_POWERUP,(short) 0 );

    }
}
