package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
    Hero hero;
    GameStage gameStage;

    Box2
    public GameLogic(PrairieKing game) {
        AI = new AIManager(this);
        AI.spawn();
        myGame = game;
        hero = new Hero(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        gameStage = new GameStage(this);
        input = new InputController(this);
        Gdx.input.setInputProcessor(input);

    }

    public Hero getHero() {
        return hero;
    }

    public PrairieKing getMyGame() {
        return myGame;
    }

    public AIManager getAI() {
        return AI;
    }



    //
    public void draw() {
       moveEntities();
       gameStage.render(0);
    }

    public void moveEntities() {
        hero.move();
        AI.move();
    }


}
