package com.prairieKing.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.PrairieKing;


public class WinScreen extends ScreenAdapter {
    private OrthographicCamera camera;

    private String name;
    private PrairieKing game;
    Sprite menu;
    SpriteBatch batch;
    FitViewport view;

    private BitmapFont font;

    private int highScore;

    public WinScreen(String name, PrairieKing game) {
        this.name = name;
        this.game = game;
        batch = new SpriteBatch();
        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        highScore = game.getMaxHighScore();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pixeled.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 37;
        font = generator.generateFont(parameter);
        generator.dispose();
        loadAssets();
    }

    private void loadAssets() {
        menu = new Sprite (game.getAssetManager().get("Menus/WinScreen.png", Texture.class));
        //menu = new Sprite (new Texture("Menus/LoseScreen.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();

        menu.setSize(view.getWorldWidth(),view.getWorldHeight());
        menu.draw(batch);


        font.draw(batch, ""+ highScore, view.getScreenWidth()/2 , view.getScreenHeight()/7);
        batch.end();

    }

    public void updateHighScore(int x) {
        highScore = x;
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