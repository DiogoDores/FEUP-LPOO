package com.prairieKing.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.controller.bodies.EntityBody;
import com.prairieKing.model.entities.EntityModel;

/**
 * Created by petre on 28/05/2017.
 */

public class PowerupBody extends EntityBody {

    public PowerupBody(World world, long addr, EntityModel model) {
        super(world, addr, model);
    }
}
