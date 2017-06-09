package com.prairieKing.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.PrairieKing;

/** Class responsible for the display of the losing screen.
 */
public class LoseScreen extends ScreenAdapter {

    private PrairieKing game;
    private Sprite menu;
    private SpriteBatch batch;
    private FitViewport view;
    private BitmapFont font, fontBack;

    private int highScore;

    /** Lose Screen constructor, instantiates font and loads appropriate image.
     *
     * @param game Needs PrairieKing instance to access AssetManager and HighScore.
     */
    public LoseScreen(PrairieKing game) {
        this.game = game;
        batch = new SpriteBatch();
        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        highScore = game.getHighScore();

        menu = new Sprite (game.getAssetManager().get("Menus/LoseScreen.png", Texture.class));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pixeled.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameterFront = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameterBack = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterFront.size = 37;
        parameterBack.size = 37;
        parameterBack.color = toRGB(85,85,178);
        font = generator.generateFont(parameterFront);
        fontBack = generator.generateFont(parameterBack);
        generator.dispose();

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

        menu.setSize(view.getWorldWidth(),view.getWorldHeight());
        menu.draw(batch);

        fontBack.draw(batch, ""+ highScore, view.getScreenWidth()/2 + view.getScreenWidth()/10.5F, view.getScreenHeight()/2f - view.getScreenHeight()/700f);
        font.draw(batch, ""+ highScore, view.getScreenWidth()/2 + view.getScreenWidth()/10, view.getScreenHeight()/2);
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

    /** Converts three floats to a Color.
     *
     * @param r Red Value.
     * @param g Green Value.
     * @param b Blue Value.
     * @return Color with the three values.
     */
    public Color toRGB(int r, int g, int b) {
        float RED = r / 255.0f;
        float GREEN = g / 255.0f;
        float BLUE = b / 255.0f;
        return new Color(RED, GREEN, BLUE, 1);
    }

}