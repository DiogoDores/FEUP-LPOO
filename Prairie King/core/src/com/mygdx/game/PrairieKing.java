package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PrairieKing extends Game {

    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();

        setScreen(new Menu("MainMenu",this));
    }

    AssetManager getAssetManager() {
        return assetManager;
    }
}
