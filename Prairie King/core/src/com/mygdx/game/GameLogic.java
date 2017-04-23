package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class GameLogic extends Stage {

    PrairieKing myGame;
    Hero hero;
    GameStage gameStage;

    public GameLogic(PrairieKing game) {
        myGame = game;
        hero = new Hero(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        gameStage = new GameStage(this);
    }

    public Hero getHero() {
        return hero;
    }

    public PrairieKing getMyGame() {
        return myGame;
    }

    @Override
    public void draw() {
        moveEntities();
        gameStage.render(0);
    }

    public void moveEntities() {
        hero.move();
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
