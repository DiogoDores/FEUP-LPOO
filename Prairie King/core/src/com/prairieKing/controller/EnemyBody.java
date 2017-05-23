package com.prairieKing.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.view.GameStage;


public class EnemyBody extends EntityBody {
    @Override
    public Object getUserData() {
        System.out.println("Informação sobre Enemy");
        return super.getUserData();
    }

    public EnemyBody(World world, EntityModel model) {
        super(world, model);
        createFixture(body, (int) GameStage.ENEMY_WIDTH ,(int) GameStage.ENEMY_WIDTH);
    }
}
