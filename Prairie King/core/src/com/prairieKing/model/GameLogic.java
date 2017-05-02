package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.controller.InputController;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.view.GameStage;

public class GameLogic extends Stage {

    InputController input;
    private AIManager AI;
    PrairieKing myGame;
    Hero hero;
    GameStage gameStage;

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

    public void resetValues() {
        dispose();
    }

    @Override
    public void draw() {
       moveEntities();
       gameStage.render(0);
    }

    public void moveEntities() {
        hero.move();
        AI.move();
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public boolean keyDown(int keyCode) {
        return super.keyDown(keyCode);
    }

    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);
    }

    @Override
    public Array<Actor> getActors() {
        return super.getActors();
    }
}
