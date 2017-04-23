package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by petre on 20/04/2017.
 */

public class GameStage extends Stage {
    static final int VIEWPORT_WIDTH = 4;
    static final float PIXEL_TO_METER = 0.22f / 200;
    private final World world;
    Texture img;
    SpriteBatch batch;
    String name;

    GameStage(PrairieKing game) {
        // Set the viewport
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new FitViewport(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio));

        // Load the textures
        game.getAssetManager().load("Menus/Menu1.png", Texture.class);


        img = new Texture("Menus/Menu1.png");
        batch = new SpriteBatch();

        world = new World(new Vector2(0, -3), true);

    }

    @Override
    public void draw() {
        super.draw();
        batch.begin();
        batch.draw(img,0,0);
        batch.end();

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Update camera

        // Step the world
        world.step(delta, 6, 2);
    }

}
