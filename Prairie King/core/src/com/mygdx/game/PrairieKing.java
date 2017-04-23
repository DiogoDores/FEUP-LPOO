package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PrairieKing  extends Game {
    Menu menu;
    GameStage gameScreen;
    public AssetManager myAssetManager;

    public PrairieKing() {

        //gameScreen = new GameScreen();
        //setScreen(gameScreen);

    }

    @Override
    public void create() {
       // setScreen(new Menu("MainMenu"));

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }


}
