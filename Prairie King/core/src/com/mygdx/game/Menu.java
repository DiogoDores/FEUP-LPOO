package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends ScreenAdapter {
	SpriteBatch batch;
	Texture img;
	private String name;
	private Game myGame;

	public Menu(String string) {
        batch = new SpriteBatch();
        img = new Texture("Face_Pixel.png");
        name = string;
		myGame = new Game() {
			@Override
			public void create() {

			}
		};
      }

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
        super.render(delta);
	}



	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
