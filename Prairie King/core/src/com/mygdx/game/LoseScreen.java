package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.xml.soap.Text;

public class LoseScreen extends ScreenAdapter {
    private OrthographicCamera camera;

    private String name;
    private PrairieKing game;
    Sprite img;
    SpriteBatch batch;
    FitViewport view;


    public LoseScreen(String name, PrairieKing game) {
        this.name = name;
        this.game = game;
        batch = new SpriteBatch();
        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        game.getAssetManager().load("Menus/LoseScreen.png", Texture.class);
        img = new Sprite(new Texture("Menus/LoseScreen.png"));

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        img.setSize(view.getWorldWidth(),view.getWorldHeight());
        img.draw(batch);
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