package com.prairieKing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.PrairieKing;

/** Main Menu class.
 */
public class MainMenu extends ScreenAdapter {
    private PrairieKing game;
    Sprite menu;
    SpriteBatch batch;
    FitViewport view;

    public MainMenu(PrairieKing game) {
        this.game = game;
        batch = new SpriteBatch();

        view = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        menu = new Sprite (game.getAssetManager().get("Menus/Menu1.png", Texture.class));
    }

    /** Override render.
     *
     * @param delta Value passed.
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.graphics.setVSync(true);
        batch.begin();
        menu.setSize(view.getWorldWidth(),view.getWorldHeight());
        menu.draw(batch);
        batch.end();

    }

    /** Dispose when finished.
     */
    @Override
    public void dispose() {
        batch.dispose();
        game.dispose();
     }

}