package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PrairieKing  extends Game {
    private Batch batch;
    private Texture img;
   private Menu mainMenu;

    @Override
    public void create () {
        setScreen(new Menu("MainMenu"));
    }


}
