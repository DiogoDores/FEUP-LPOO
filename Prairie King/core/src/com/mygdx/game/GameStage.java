package com.mygdx.game;

import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameStage extends Stage implements Screen {
    private OrthographicCamera camera;
    private Stage stage;
    private String name;
    private PrairieKing game;
    Sprite background;
    Sprite hero;
    SpriteBatch batch;
    FitViewport view;
    GameLogic gameLogic;

    public GameStage(GameLogic gameLogic) {
        this.name = name;
        this.game = gameLogic.getMyGame();
        batch = new SpriteBatch();
        stage = new Stage();

        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        game.getAssetManager().load("Mapas/MapaTeste1.png", Texture.class);
        game.getAssetManager().load("Sprites/MainSpriteSheet.png", Texture.class);
        background = new Sprite(new Texture("Mapas/MapaTeste1.png"));
        hero = new Sprite(new Texture("Sprites/MainSpriteSheet.png"),367,96,16,16);

        this.gameLogic = gameLogic;
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        background.setSize(view.getWorldWidth(),view.getWorldHeight());
        hero.setSize(view.getWorldWidth()/32,view.getWorldHeight()/18);
        hero.setX(gameLogic.getHero().getX());
        hero.setY(gameLogic.getHero().getY());

        background.draw(batch);
        hero.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        view.update(width,height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}