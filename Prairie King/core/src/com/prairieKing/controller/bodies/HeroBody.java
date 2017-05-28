package com.prairieKing.controller.bodies;

import com.badlogic.gdx.physics.box2d.World;
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
        setHeight(GameStage.HERO_WIDTH);
        setWidth(GameStage.HERO_WIDTH);
        createFixture(getBody(), (int) GameStage.HERO_WIDTH , (int) GameStage.HERO_WIDTH);
    }
}
