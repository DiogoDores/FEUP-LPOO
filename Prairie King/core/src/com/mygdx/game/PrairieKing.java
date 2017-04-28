package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PrairieKing extends Game {
    int currentState;
    Menu menu;
    LoseScreen loseScreen;
    GameStage gameStage;
    int highScore;
    GameLogic gameLogic;
    
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 220;
    

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        currentState = 0;
        menu = new Menu("MainMenu",this);
        setScreen(menu);
        loseScreen = new LoseScreen("LoseScreen", this);

        gameLogic = new GameLogic(this);
        gameStage = new GameStage(gameLogic);
    }

    public int getHighScore() {
        return highScore;
    }

    // Utilizada para verficar sempre qual é o render que deve estar ativo.

    @Override
    public void render() {
        if (currentState == 0) { // Main Menu
            setScreen(menu);
            menu.render(0);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 1;
            }
        }
        else if (currentState == 1) { // Game Mode
            gameLogic.act();
            gameLogic.draw();
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 2;
            }
        }
        else {  // Lose Screen
            setScreen(loseScreen);
            loseScreen.render(0);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                currentState = 0;
            }
        }

    }

    AssetManager getAssetManager() {
        return assetManager;
    }
}
