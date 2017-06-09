package com.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.PrairieKing;

/** Class responsible for the output of the win scenario.
 */
public class WinScreen extends ScreenAdapter {

    private PrairieKing game;
    private Sprite menu;
    private SpriteBatch batch;
    private FitViewport view;

    private BitmapFont font;

    private int highScore;

    /** Instantiates WinScreen and loads all necessary assets.
     *
     * @param game Needs this to access highScore and AssetManager
     */
    public WinScreen(PrairieKing game) {
        this.game = game;
        batch = new SpriteBatch();
        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        highScore = game.getHighScore();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixeled.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 37;
        font = generator.generateFont(parameter);
        generator.dispose();
        menu = new Sprite (game.getAssetManager().get("menus/winscreen.png", Texture.class));
    }

    /** Override render.
     *
     * @param delta Value passed.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();

        menu.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        menu.draw(batch);
        font.draw(batch, ""+ highScore, Gdx.graphics.getWidth()/2.2f - font.getSpaceWidth()/2 , Gdx.graphics.getHeight()/7);
        batch.end();

    }

    /** After a new game, needs to update HighScore.
     *
     * @param x New HighScore.
     */
    public void updateHighScore(int x) {
        highScore = x;
    }

    /** Dispose when finished.
     */
    @Override
    public void dispose() {
        batch.dispose();
        game.dispose();
    }

}