package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by petre on 20/04/2017.
 */

public class GameStage extends Stage {
    private Texture background;
    private SpriteBatch batch;

    public GameStage() {
        background = new Texture("desertorange.gif");
    }
}
