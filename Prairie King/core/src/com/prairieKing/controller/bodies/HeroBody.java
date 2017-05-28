package com.prairieKing.controller.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.model.entities.EntityModel;
import com.prairieKing.view.GameStage;

public class HeroBody extends EntityBody {

    @Override
    public Object getUserData() {
        return "HERO";
    }

    public HeroBody(World world, EntityModel model) {
        super(world, 0, model);
        super.setUserData(model);
        setHeight(Constants.HERO_WIDTH);
        setWidth(Constants.HERO_WIDTH);
        createFixture(getBody(), (int) Constants.HERO_WIDTH , (int) Constants.HERO_WIDTH, Constants.CATEGORY_PLAYER,Constants.MASK_PLAYER,(short) 0);

    }
}
