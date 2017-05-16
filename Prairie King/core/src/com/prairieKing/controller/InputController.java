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
import com.prairieKing.model.HeroModel;

public class InputController implements InputProcessor {

    private HeroModel hero;
    private HeroBody heroBody;

    public InputController(GameLogic gameLogic) {
        this.hero = gameLogic.getHero();
        this.heroBody = gameLogic.getHeroBody();
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == (Input.Keys.S))
            heroBody.setDown(true);
        else if (keycode == (Input.Keys.W))
            heroBody.setUp(true);
        if (keycode == (Input.Keys.A))
            heroBody.setLeft(true);
        else if (keycode == (Input.Keys.D))
            heroBody.setRight(true);

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
            heroBody.setDown(false);
        else if (keycode == (Input.Keys.W))
            heroBody.setUp(false);
        if (keycode == (Input.Keys.A))
            heroBody.setLeft(false);
        else if (keycode == (Input.Keys.D))
            heroBody.setRight(false);


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
