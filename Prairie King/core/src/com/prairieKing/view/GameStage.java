package com.prairieKing.view;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.prairieKing.Constants;
import com.prairieKing.PrairieKing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.controller.entities.EnemyController;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.controller.entities.PowerupController;
import com.prairieKing.controller.entities.ProjectileController;

import java.util.ArrayList;

/**
 * Class responsible for the output of all the information.
 */
public class GameStage extends ScreenAdapter {
    private Stage stage;
    private PrairieKing game;
    private Sprite powerupToDraw, projectileToDraw;
    private Sprite woman, ending, kissing, transition;
    private SpriteBatch batch;
    private FitViewport view;
    private GameLogic gameLogic;

    private boolean hasWon;

    private ArrayList<EnemyController> enemies;
    private Gun gun;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

    private Box2DDebugRenderer b2dr;
    private TextureAtlas atlas;

    private Texture mainSprite, background;

    private HeroAnimator animateHero;
    private EnemyAnimator animateEnemy;

    private int i = 0;

    private float animation;
    private Music music;

    /** Constructor for the main screen.
     *
     * @param gameLogic Current active game.
     */
    public GameStage(GameLogic gameLogic) {
        animation = 0;
        this.game = gameLogic.getPrairieKing();
        this.gameLogic = gameLogic;
        batch = new SpriteBatch();
        stage = new Stage();
        gun = gameLogic.getGun();
        enemies = gameLogic.getAI().getEnemies();
        cam = new OrthographicCamera(PrairieKing.PPM, PrairieKing.PPM);
        map = gameLogic.getPrairieKing().getMap();
        hasWon = false;
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.setToOrtho(false, PrairieKing.PPM, PrairieKing.PPM);
        view = new FitViewport(PrairieKing.PPM / Constants.RATIO, PrairieKing.PPM, cam);
        renderer = new OrthogonalTiledMapRenderer(map, .2234f);
        loadAssets();

        atlas = new TextureAtlas("Sprites/Entities.pack");
        animateHero = new HeroAnimator(this);
        animateEnemy = new EnemyAnimator(this);

        b2dr = new Box2DDebugRenderer();

    }

    /** Loads all necessary game assets.
     */
    public void loadAssets() {
        music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/finalSong.mp3"));
        mainSprite = game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class);
        background = game.getAssetManager().get("Sprites/BlockBackground.png", Texture.class);

        loadEndAssets();
    }

    /** Loads all necessary ending assets.
     */
    private void loadEndAssets() {
        woman = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 304, 96, 16, 16);
        woman.setSize(Constants.HERO_WIDTH * 1.2f, Constants.HERO_WIDTH * 1.2f);
        woman.setX(PrairieKing.PPM / 2);
        woman.setY(PrairieKing.PPM / 2 - 10);
        ending = new Sprite(game.getAssetManager().get("Mapas/MapaFinal.png", Texture.class));
        ending.setSize(PrairieKing.PPM / Constants.RATIO, PrairieKing.PPM);
        ending.setX(-42);
        kissing = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 422, 155, 22, 21);
        kissing.setSize(1.7f * Constants.HERO_WIDTH, 1.6f * Constants.HERO_WIDTH);

        transition = new Sprite(game.getAssetManager().get("Sprites/TransitionToWin.png", Texture.class));
        transition.setSize(PrairieKing.PPM / Constants.RATIO, PrairieKing.PPM * 5);
        transition.setX(-42);
    }

    /** Render every frame.
     *
     * @param delta Time since last render.
     */
    @Override
    public void render(float delta) {
        renderer.setView(cam);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.render();

        batch.begin();

        cam.update();
        batch.setProjectionMatrix(cam.combined);

        if (hasWon) {

            if (gameLogic.getHero().getState() == 0) {
                animateHero.update();
                animateHero.draw(batch);
            } else {

                if (gameLogic.getHero().getState() == 1 || gameLogic.getHero().getState() == 1.5f) {
                    isGoingToWife();
                } else if (gameLogic.getHero().getState() == 2) {
                    waitsForKiss();
                } else if (gameLogic.getHero().getState() == 3 || gameLogic.getHero().getState() == 4) {
                    isKissing();
                    if (gameLogic.getHero().getState() == 4) {
                        animation -= 50.0f * Gdx.graphics.getDeltaTime();
                        if (animation <= -400)
                            animation = -400;
                        transition.setY(animation);
                        transition.draw(batch);
                    }
                } else if (gameLogic.getHero().getState() == 5) {
                    transition.draw(batch);
                    PrairieKing.currentState = 3;
                    float volume = gameLogic.getPrairieKing().decreaseVolume(music, 0.000001f);

                    while (volume > 0) {
                        volume = gameLogic.getPrairieKing().decreaseVolume(music, 0.01f);
                    }

                    gameLogic.resetEverything();
                }

            }
        } else {
            animateHero.update();
            animateHero.draw(batch);
            drawEnemies();
            drawBullets();
            drawPowerups();

            Sprite black = new Sprite(background);
            black.setSize(90, 40);
            black.setX(-90);
            black.setY(30);
            black.draw(batch);
            black.setX(100);
            black.setY(0);
            black.setSize(900, 200);
            black.draw(batch);

            drawHud();

        }
        batch.end();
    }

    /** Assets to draw when hero is going to wife (ENDING).
     */
    private void isGoingToWife() {
        music.play();
        music.setVolume(0.5f);
        ending.draw(batch);
        woman.draw(batch);

        animateHero.update();
        animateHero.draw(batch);
    }

    /** Assets to draw when hero is kissing wife (ENDING).
     */
    private void isKissing() {
        ending.draw(batch);
        kissing.setX(gameLogic.getHero().getX());
        kissing.setY(gameLogic.getHero().getY());
        kissing.draw(batch);
    }

    /** Assets to draw when hero is waiting for wife's kiss.
     */
    private void waitsForKiss() {
        ending.draw(batch);
        woman.draw(batch);

        Sprite h = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 352, 128, 16, 16);
        h.setSize(Constants.HERO_WIDTH * 1.1f, Constants.HERO_WIDTH * 1.1f);
        h.setX(gameLogic.getHero().getX());
        h.setY(gameLogic.getHero().getY());
        h.draw(batch);
    }

    /** Draws all the elements of the hud, including lives and active powerup.
     */
    private void drawHud() {
        Sprite gunHolder = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 166, 134, 22, 22);
        gunHolder.setSize(17, 17);
        gunHolder.setX(-30);
        gunHolder.setY(80);
        gunHolder.draw(batch);

        Sprite currentPowerUp;
        if (gun.getTypeGun() != "NORMAL") {
            if (gun.getTypeGun() == "SHOTGUN")
                currentPowerUp = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 256, 160, 16, 16);
            else if (gun.getTypeGun() == "WHEEL")
                currentPowerUp = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 176, 160, 16, 16);
            else
                currentPowerUp = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 192, 160, 16, 16);

            currentPowerUp.setSize(9, 9);
            currentPowerUp.setX(-26);
            currentPowerUp.setY(84);

            currentPowerUp.draw(batch);
        }

        Sprite heartToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 97, 162, 16, 16);
        for (int i = 0; i < gameLogic.getHero().getLives() - 1; i++) {
            float x = i % 3;
            heartToDraw.setSize(Constants.HEART_WIDTH, Constants.HEART_WIDTH);
            heartToDraw.setX( 110 + x * Constants.HEART_WIDTH);
            heartToDraw.setY(10 + (i / 3 * Constants.HEART_WIDTH));
            heartToDraw.draw(batch);
        }

    }

    /** Displays all active Projectiles.
     */
    private void drawBullets() {
        for (ProjectileController projectile : gun.getProjectiles()) {
            projectileToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 261, 160, 6, 6);
            projectileToDraw.setSize(Constants.PROJECTILE_WIDTH, Constants.PROJECTILE_WIDTH);
            projectileToDraw.setX(projectile.getX());
            projectileToDraw.setY(projectile.getY());
            projectileToDraw.draw(batch);
        }
    }

    /** Displays all powerups scattered across the map.
     */
    private void drawPowerups() {
        ArrayList<PowerupController> powerups = gameLogic.getPowerupSpawner().getPowerupModels();
        for (int i = 0; i < powerups.size(); i++) {
            if (powerups.get(i).getPowerupType().equals("GUN SPEED"))
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 192, 160, 16, 16);
            else if (powerups.get(i).getPowerupType().equals("GUN SHOTGUN"))
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 256, 160, 16, 16);
            else if (powerups.get(i).getPowerupType().equals("GUN WHEEL"))
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 176, 160, 16, 16);
            else if (powerups.get(i).getPowerupType().equals("HERO LIFE") )
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 272, 160, 16, 16);
            else if (powerups.get(i).getPowerupType().equals("HERO SPEED"))
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 240, 160, 15, 15);
            powerupToDraw.setSize(Constants.POWERUP_WIDTH, Constants.POWERUP_WIDTH);
            powerupToDraw.setX(powerups.get(i).getX());
            powerupToDraw.setY(powerups.get(i).getY());
            powerupToDraw.draw(batch);
        }
    }

    /** Draw all enemies on the screen.
     */
    private void drawEnemies() {
        for (EnemyController e : enemies) {
            animateEnemy.update(i, e);
            animateEnemy.draw(batch);

            i++;
        }
        i = 0;
    }

    /** In case the windows is resized.
     *
     * @param width New width.
     * @param height New height.
     */
    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = PrairieKing.PPM;
        cam.viewportHeight = PrairieKing.PPM * Constants.RATIO;
        view.update(width, height);
        cam.update();
    }

    /** On program exit.
     */
    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        batch.dispose();
        stage.dispose();
    }

    /** Returns TextureAtlas.
     *
     * @return atlas.
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }

    /** Gets the current GameLogic instance.
     *
     * @return gameLogic.
     */
    public GameLogic getGameLogic() {
        return gameLogic;
    }

    /** If player has won, important to read new Hero's state.
     */
    public void hasWon() {
        animateHero = new HeroAnimator(this);
        hasWon = true;
    }

    /** Returns whether or not player has won, used for volume decrease
     * on PrairieKing.
     *
     * @return hasWon.
     */
    public boolean getHasWon() {
        return hasWon;
    }

}