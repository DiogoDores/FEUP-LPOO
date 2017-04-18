package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PrairieKing  extends ApplicationAdapter {
	
	private Menu mainMenu;
	
	@Override
	public void create () {
		mainMenu = new Menu("MainMenu");
	
	}
	
}
