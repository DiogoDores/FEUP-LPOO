package com.prairieKing.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.entities.HeroModel;

public class InputController implements InputProcessor {

    private HeroModel hero;

    public InputController(GameLogic gameLogic) {
        this.hero = gameLogic.getHero();
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == (Input.Keys.S))
            hero.setDown(true);
        else if (keycode == (Input.Keys.W))
            hero.setUp(true);
        if (keycode == (Input.Keys.A))
            hero.setLeft(true);
        else if (keycode == (Input.Keys.D))
            hero.setRight(true);

        if (keycode == (Input.Keys.DOWN))
            hero.setDownB(true);
        else if (keycode == (Input.Keys.UP))
            hero.setUpB(true);
        if (keycode == (Input.Keys.LEFT))
            hero.setLeftB(true);
       else if (keycode == (Input.Keys.RIGHT))
            hero.setRightB(true);

        return true;

    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == (Input.Keys.S))
            hero.setDown(false);
        else if (keycode == (Input.Keys.W))
            hero.setUp(false);
        if (keycode == (Input.Keys.A))
            hero.setLeft(false);
        else if (keycode == (Input.Keys.D))
            hero.setRight(false);


        if (keycode == (Input.Keys.DOWN))
            hero.setDownB(false);
        else if (keycode == (Input.Keys.UP))
            hero.setUpB(false);
        if (keycode == (Input.Keys.LEFT))
            hero.setLeftB(false);
        else if (keycode == (Input.Keys.RIGHT))
            hero.setRightB(false);


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
