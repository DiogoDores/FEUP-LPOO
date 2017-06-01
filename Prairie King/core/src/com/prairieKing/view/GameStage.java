package com.prairieKing.view;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
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
import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.entities.PowerupModel;
import com.prairieKing.model.entities.ProjectileModel;

import java.util.ArrayList;

public class GameStage extends ScreenAdapter {
    private Stage stage;
    private PrairieKing game;
    private Sprite hero, enemyToDraw, powerupToDraw, projectileToDraw;
    private SpriteBatch batch;
    private FitViewport view;
    private GameLogic gameLogic;

    private ArrayList<EnemyModel> enemies;
    private Gun gun;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

    private World world;
    private Box2DDebugRenderer b2dr;
    private TextureAtlas atlas;

    private Animator animator;

    private Sound sound;

    public GameStage(GameLogic gameLogic) {
        this.game = gameLogic.getPrairieKing();
        this.gameLogic = gameLogic;
        batch = new SpriteBatch();
        stage = new Stage();
        gun = gameLogic.getGun();
        enemies = gameLogic.getAI().getEnemies();
        cam = new OrthographicCamera(game.PPM, game.PPM);
        map = gameLogic.getMap();

        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.setToOrtho(false, game.PPM, game.PPM);
        view = new FitViewport(game.PPM, game.PPM, cam);
        renderer = new OrthogonalTiledMapRenderer(map, .2234f);
        loadAssets();

        atlas = new TextureAtlas("Sprites/Entities.pack");
        animator = new Animator(this);

        world = gameLogic.getWorld();
        b2dr = new Box2DDebugRenderer();

    }


    public void loadAssets() {
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/pop.mp3"));
        Texture mainSprite = game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class);
        hero = new Sprite(mainSprite, 367, 96, 16, 16);
    }

    @Override
    public void render(float delta) {

        animator.update(delta);
        renderer.setView(cam);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.render();
        b2dr.render(world, cam.combined);

        batch.begin();

        cam.update();
        batch.setProjectionMatrix(cam.combined);

        animator.draw(batch);
        drawEnemies();
        drawBullets();
        drawPowerups();

        batch.end();

    }

    public void drawBullets() {
        for (ProjectileModel projectile : gun.getProjectiles()) {
            projectileToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 261, 160, 6, 6);
            projectileToDraw.setSize(Constants.PROJECTILE_WIDTH, Constants.PROJECTILE_WIDTH);
            projectileToDraw.setX(projectile.getX());
            projectileToDraw.setY(projectile.getY());
            projectileToDraw.draw(batch);
        }
    }

    public void drawPowerups() {
        ArrayList<PowerupModel> powerups = gameLogic.getPowerupSpawner().getPowerupModels();
        for (int i = 0; i < powerups.size(); i++) {
            if (powerups.get(i).getPowerupType() == "GUN SPEED")
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 192, 160, 16, 16);
            else if (powerups.get(i).getPowerupType() == "GUN SHOTGUN")
                powerupToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 256, 160, 16, 16);
            powerupToDraw.setSize(Constants.POWERUP_WIDTH, Constants.POWERUP_WIDTH);
            powerupToDraw.setX(powerups.get(i).getX());
            powerupToDraw.setY(powerups.get(i).getY());
            powerupToDraw.draw(batch);
        }
    }


    public void drawEnemies() {
        for (EnemyModel e : enemies) {
            if (e.getEnemyType() == "BASIC") {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 304, 80, 16, 16);
            } else if (e.getEnemyType() == "FLYING") {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 352, 64, 16, 16);
            }
                else if(e.getEnemyType() == "TOUGH")
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 416, 64, 16, 16);
            enemyToDraw.setSize(Constants.ENEMY_WIDTH, Constants.ENEMY_WIDTH);
            enemyToDraw.setX(e.getX());
            enemyToDraw.setY(e.getY());
            enemyToDraw.draw(batch);

        }
    }


    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = game.PPM;
        cam.viewportHeight = game.PPM;
        view.update(width, height);
        cam.update();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        batch.dispose();
        stage.dispose();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }
}