package com.prairieKing.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;
import com.prairieKing.model.HeroModel;
import com.prairieKing.view.GameStage;

public class HeroBody extends EntityBody {

    @Override
    public Object getUserData() {
        System.out.println("Informação sobre Enemy");
        return super.getUserData();
    }

    public HeroBody(World world, EntityModel model) {
        super(world, model);
        setHeight(GameStage.HERO_WIDTH );
        setWidth(GameStage.HERO_WIDTH );
        createFixture(body, (int) GameStage.HERO_WIDTH , (int) GameStage.HERO_WIDTH);
    }
}
