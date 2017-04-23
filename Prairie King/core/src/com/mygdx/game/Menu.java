package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Menu extends Stage {
    SpriteBatch batch;
    Texture img;
    private String name;
    private Game myGame;

    @Override
    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();
        img = new Texture("Menus/Menu1.png");

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        super.draw();
    }

    public Menu(String string) {
        name = string;
    }


    @Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
