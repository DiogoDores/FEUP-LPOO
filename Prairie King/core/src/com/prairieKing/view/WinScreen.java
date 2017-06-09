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

/** Class responsible for the output of the win scenario.
 */
public class WinScreen extends ScreenAdapter {

    private PrairieKing game;
    private Sprite menu;
    private SpriteBatch batch;
    private FitViewport view;

    private BitmapFont font;

    private int highScore;

    public WinScreen(PrairieKing game) {
        this.game = game;
        batch = new SpriteBatch();
        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        highScore = game.getMaxHighScore();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pixeled.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 37;
        font = generator.generateFont(parameter);
        generator.dispose();
        menu = new Sprite (game.getAssetManager().get("Menus/WinScreen.png", Texture.class));
    }

    /**
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();

        menu.setSize(view.getWorldWidth(),view.getWorldHeight());
        menu.draw(batch);


        font.draw(batch, ""+ highScore, view.getScreenWidth()/2 - font.getSpaceWidth()/2 , view.getScreenHeight()/7);
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