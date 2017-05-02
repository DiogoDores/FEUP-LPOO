package com.prairieKing.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Hero;

public class InputController implements InputProcessor {

    private GameLogic gameLogic;

    public InputController(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public boolean keyDown(int keycode) {
        Hero hero = gameLogic.getHero();

        if (keycode == (Input.Keys.S))
            hero.setDown(true);
        if (keycode == (Input.Keys.A))
            hero.setLeft(true);
        if (keycode == (Input.Keys.W))
            hero.setUp(true);
        if (keycode == (Input.Keys.D))
            hero.setRight(true);

        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        Hero hero = gameLogic.getHero();

        if (keycode == (Input.Keys.S))
            hero.setDown(false);
        if (keycode == (Input.Keys.A))
            hero.setLeft(false);
        if (keycode == (Input.Keys.W))
            hero.setUp(false);
        if (keycode == (Input.Keys.D))
            hero.setRight(false);


        return true;

    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
