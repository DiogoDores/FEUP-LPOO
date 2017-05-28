package com.prairieKing.controller.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.entities.EntityModel;
import com.prairieKing.view.GameStage;


public class EnemyBody extends EntityBody {




    public EnemyBody(World world, EntityModel model) {
        super(world, 0, model);
        super.setUserData(model);
        createFixture(getBody(), (int) GameStage.ENEMY_WIDTH ,(int) GameStage.ENEMY_WIDTH);
    }
}
