package com.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.controller.entities.HeroController;
import com.model.GameLogic;
import com.model.Gun;

/** Class that receives all the inputs and invokes the appropriate
 * methods to handle that information.
 */
public class InputHandler implements InputProcessor {

    private HeroController hero;
    private Gun gun;

    /**
     * Constructor for the InputHandler, which handles all the input the player
     * inserts.
     * @param gameLogic This variable is required to extract the positions of the hero and gun,
     *                  as well as to invoke some of their methods when certain keys are pressed.
     */
    public InputHandler(GameLogic gameLogic) {
        this.hero = gameLogic.getHero();
        this.gun = gameLogic.getGun();
    }


    /**Is triggered when a key is pressed.
     *
     * @param keycode Keycode that was just pressed.
     * @return Return whether input was successfully handled.
     */
    @Override
    public boolean keyDown(int keycode) {
        handleInput(keycode,true);
        return true;

    }

    /**Is triggered when a key is released.
     *
     * @param keycode Keycode that was just released.
     * @return Return whether input was successfully handled.
     */
    @Override
    public boolean keyUp(int keycode) {
        handleInput(keycode,false);
        return true;
    }

    /** Determines the appropriate response, based on a keycode and a value.
     *
     * @param keycode Keycode that was just pressed or released.
     * @param value What is used to set variables on the respective
     *              classes.
     */
    private void handleInput(int keycode, boolean value) {
        if (keycode == (Input.Keys.S))
            hero.setDown(value);
        else if (keycode == (Input.Keys.W))
            hero.setUp(value);
        if (keycode == (Input.Keys.A))
            hero.setLeft(value);
        else if (keycode == (Input.Keys.D))
            hero.setRight(value);

        if (keycode == (Input.Keys.DOWN))
            gun.setDownB(value);
        else if (keycode == (Input.Keys.UP))
            gun.setUpB(value);
        if (keycode == (Input.Keys.LEFT))
            gun.setLeftB(value);
        else if (keycode == (Input.Keys.RIGHT))
            gun.setRightB(value);
    }

    /**Is triggered when a key is typed, but is not needed.
     *
     * @param character Character that was just typed.
     * @return Return whether input was successfully handled.
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    /**
     *  Intended for mobile, not needed.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Intended for mobile, not needed.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /** Intended for mobile, not needed.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /** Not necessary, mouse is not a part of the game.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**Not necessary, mouse is not a part of the game.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
