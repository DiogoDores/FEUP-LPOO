package com.prairieKing.controller;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.prairieKing.view.*;
import com.prairieKing.model.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.prairieKing.model.GameLogic;
import com.prairieKing.view.GameStage;
import com.prairieKing.view.LoseScreen;
import com.prairieKing.view.Menu;

public class PrairieKing extends Game {
    int currentState;
    Menu menu;
    LoseScreen loseScreen;
    GameStage gameStage;
    int highScore;
    GameLogic gameLogic;

    public static final float PPM = 1000f;

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        loadAssets();

        currentState = 0;
        menu = new Menu(this);
        setScreen(menu);
        loseScreen = new LoseScreen("LoseScreen", this);
        gameLogic = new GameLogic(this);

        gameStage = new GameStage(gameLogic);
    }

    public void loadAssets() {
        assetManager.load("Menus/LoseScreen.png", Texture.class);
        assetManager.load("Sprites/MainSpriteSheet.png", Texture.class);
        assetManager.load("Mapas/Map.png", Texture.class);
        assetManager.load("Menus/Menu1.png", Texture.class);
        assetManager.finishLoading();
        assetManager.update();
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
            setScreen(gameStage);
            gameLogic.act();
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

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
