package com.prairieKing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.controller.PrairieKing;

public class Menu extends ScreenAdapter {
    private PrairieKing game;
    Sprite menu;
    SpriteBatch batch;
    FitViewport view;

    public Menu(PrairieKing game) {
        this.game = game;
        batch = new SpriteBatch();

        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        loadAssets();
    }

    private void loadAssets() {
        menu = new Sprite (game.getAssetManager().get("Menus/Menu1.png", Texture.class));
      //  menu = new Sprite (new Texture("Menus/Menu1.png"));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.graphics.setVSync(true);
        batch.begin();
        menu.setSize(view.getWorldWidth(),view.getWorldHeight());
        menu.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        view.update(width,height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        game.dispose();
     }

}