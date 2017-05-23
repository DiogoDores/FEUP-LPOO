package com.prairieKing.view;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.controller.PrairieKing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.prairieKing.controller.ProjectileBody;
import com.prairieKing.model.EnemyModel;
import com.prairieKing.model.GameLogic;
import com.prairieKing.model.Gun;
import com.prairieKing.model.ProjectileModel;

import java.util.ArrayList;

public class GameStage extends ScreenAdapter {
    private Stage stage;
    private PrairieKing game;
    private Sprite hero, enemyToDraw;
    private SpriteBatch batch;
    private FitViewport view;
    private GameLogic gameLogic;
    private Sprite projectileToDraw;
    private ArrayList<EnemyModel> enemies;
    private Gun gun;
    private AssetManager assetManager;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

 public static final float HERO_WIDTH = PrairieKing.PPM/15;
    public static final float ENEMY_WIDTH = PrairieKing.PPM/15;
    public static final float PROJECTILE_WIDTH = PrairieKing.PPM/35;

    private World world;
    private Box2DDebugRenderer b2dr;
    //private TextureAtlas textureAtlas;
    //private Animation animation;
    //private float elapsedTime = 0f;


    public GameStage(GameLogic gameLogic) {
        this.game = gameLogic.getMyGame();
        batch = new SpriteBatch();
        stage = new Stage();
        gun = gameLogic.getHero().getGun();
        enemies = gameLogic.getAI().getEnemies();
        cam = new OrthographicCamera(game.PPM, game.PPM);
        map = gameLogic.getMap();

        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
        cam.setToOrtho(false, game.PPM,  game.PPM);
        view = new FitViewport(game.PPM, game.PPM, cam);
        renderer = new OrthogonalTiledMapRenderer(map, .2234f);
        loadAssets();

        this.gameLogic = gameLogic;

        world = gameLogic.getWorld();
        b2dr = new Box2DDebugRenderer();
    }


    public void loadAssets() {
        Texture mainSprite = game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class);
        hero = new Sprite(mainSprite,367,96,16,16);
    }

    @Override
    public void render(float delta){
        renderer.setView(cam);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.render();
        b2dr.render(world, cam.combined);

        batch.begin();

        cam.update();
        batch.setProjectionMatrix(cam.combined);

        hero.setSize(game.PPM/15, cam.viewportHeight/15);
        hero.setX(gameLogic.getHero().getX());
        hero.setY(gameLogic.getHero().getY());

        hero.draw(batch);
        drawEnemies();
        drawBullets();

        batch.end();

    }

    public void drawBullets() {
        for (ProjectileModel projectile : gun.getProjectiles()) {
            projectileToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class),261,160,6,6);
            projectileToDraw.setSize(cam.viewportWidth/35,cam.viewportHeight/35);
            projectileToDraw.setX(projectile.getX());
            projectileToDraw.setY(projectile.getY());
            projectileToDraw.draw(batch);
        }
    }

    public void drawEnemies() {
        for (EnemyModel e : enemies)
            if (e.getEnemyType() == "BASIC") {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 304, 80, 16, 16);
                enemyToDraw.setSize(cam.viewportWidth / 15, cam.viewportHeight / 15);
                enemyToDraw.setX(e.getX());
                enemyToDraw.setY(e.getY());
                enemyToDraw.draw(batch);
            }
            else {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class), 352, 64, 16, 16);
                enemyToDraw.setSize(cam.viewportWidth / 15, cam.viewportHeight / 15);
                enemyToDraw.setX(e.getX());
                enemyToDraw.setY(e.getY());
                enemyToDraw.draw(batch);
            }
    }


    @Override
    public void resize(int width, int height){
        cam.viewportWidth =  game.PPM;
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

    public TiledMap getMap(){
        return this.map;
    }

}