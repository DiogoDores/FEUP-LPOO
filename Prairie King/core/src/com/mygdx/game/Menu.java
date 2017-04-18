package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends ScreenAdapter {
	SpriteBatch batch;
	Texture img;
	private String name;

	public Menu(String string) {
		name = string;
	}

	@Override
	public void render(float delta) {
	// TODO Auto-generated method stub
	super.render(delta);
	}	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
