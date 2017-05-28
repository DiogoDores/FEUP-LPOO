package com.prairieKing.controller.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.controller.bodies.EntityBody;
import com.prairieKing.model.entities.EntityModel;
import com.prairieKing.view.GameStage;

/**
 * Created by petre on 28/05/2017.
 */

public class PowerupBody extends EntityBody {

    public PowerupBody(World world, long addr, EntityModel model) {
        super(world, addr, model);
        setHeight(GameStage.POWERUP_WIDTH);
        setWidth(GameStage.POWERUP_WIDTH );
        createFixture(getBody(), (int) GameStage.POWERUP_WIDTH, (int) GameStage.POWERUP_WIDTH);
    }
}
