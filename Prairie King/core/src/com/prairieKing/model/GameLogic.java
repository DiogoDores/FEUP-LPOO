package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.controller.InputController;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.view.GameStage;

public class GameLogic {

    InputController input;
    private AIManager AI;
    PrairieKing myGame;
    HeroModel hero;
    GameStage gameStage;
    World world;

    public GameLogic(PrairieKing game) {
        world = new World(new Vector2(0,100f), true);
        AI = new AIManager(this);
        AI.spawn();
        myGame = game;
        hero = new HeroModel((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);
        gameStage = new GameStage(this);
        input = new InputController(this);
        Gdx.input.setInputProcessor(input);
    }

    public HeroModel getHero() {
        return hero;
    }

    public PrairieKing getMyGame() {
        return myGame;
    }

    public AIManager getAI() {
        return AI;
    }

    public void act() {
        world.step(1/300f,6,2);
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(Body body : bodies){
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }
        gameStage.render(0);
        moveEntities();
    }


    public void moveEntities() {
        hero.move();
        AI.move();
    }


}
