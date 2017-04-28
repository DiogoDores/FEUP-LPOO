package com.prairieKing.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.controller.PrairieKing;

public class LoseScreen extends ScreenAdapter {
    private OrthographicCamera camera;

    private String name;
    private PrairieKing game;
    Sprite menu;
    SpriteBatch batch;
    FitViewport view;


    public LoseScreen(String name, PrairieKing game) {
        this.name = name;
        this.game = game;
        batch = new SpriteBatch();
        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        loadAssets();
    }

    private void loadAssets() {
        menu = new Sprite (game.getAssetManager().get("Menus/LoseScreen.png", Texture.class));
        //menu = new Sprite (new Texture("Menus/LoseScreen.png"));
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
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