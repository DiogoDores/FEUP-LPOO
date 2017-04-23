package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by petre on 20/04/2017.
 */

public class LoseScreen extends ScreenAdapter{

    SpriteBatch batch;
    Texture img;



    public LoseScreen() {
        batch = new SpriteBatch();
        img = new Texture("Face_Pixel.png");

    }

    @Override
    public void render(float delta) {

        super.render(delta);
    }
}
