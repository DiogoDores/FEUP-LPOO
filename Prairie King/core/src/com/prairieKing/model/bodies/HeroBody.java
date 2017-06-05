package com.prairieKing.model.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.controller.entities.EntityController;

public class HeroBody extends EntityBody {

    /**Creates a body for a Hero, based on a Model, as well as it's fixture.
     *
     * @param world needed for EntityBody's constructor.
     * @param model Model with which the body is going to be associated with.
     */
    public HeroBody(World world, EntityController model) {
        super(world, 0, model);
        createFixture((int) Constants.HERO_WIDTH , (int) Constants.HERO_WIDTH, Constants.CATEGORY_PLAYER,Constants.MASK_PLAYER,(short) 0);
    }

}
