package com.prairieKing.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.view.GameStage;


public class EnemyBody extends EntityBody {


    @Override
    public Object getUserData() {
        setUserData("ENEMY");
        return "ENEMY";
    }

    public EnemyBody(World world, EntityModel model) {
        super(world, 0, model);
        createFixture(body, (int) GameStage.ENEMY_WIDTH ,(int) GameStage.ENEMY_WIDTH);
    }
}
