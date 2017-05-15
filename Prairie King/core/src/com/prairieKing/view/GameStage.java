package com.prairieKing.view;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class GameStage extends ScreenAdapter {
    private Stage stage;
    private PrairieKing game;
    private Sprite hero, enemyToDraw;
    private SpriteBatch batch;
    private FitViewport view;
    private GameLogic gameLogic;
    private Sprite projectileToDraw;
    private EnemyModel[] enemies;
    private Gun gun;
    private AssetManager assetManager;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera cam;

   /* private World world;
    private Box2DDebugRenderer b2dr;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0f;
    public Box2DDebugRenderer debugRenderer;*/


    public GameStage(GameLogic gameLogic) {
        this.game = gameLogic.getMyGame();
        batch = new SpriteBatch();
        stage = new Stage();
        gun = gameLogic.getHero().getGun();
        enemies = gameLogic.getAI().getEnemies();
        cam = new OrthographicCamera(game.PPM, game.PPM);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Mapas/Map.tmx");
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
        cam.setToOrtho(false, game.PPM,  game.PPM);
        view = new FitViewport(game.PPM, game.PPM, cam);
        renderer = new OrthogonalTiledMapRenderer(map, .2234f);
        loadAssets();

        this.gameLogic = gameLogic;
    }


    public void loadAssets() {
        Texture mainSprite = game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class);
        hero = new Sprite(mainSprite,367,96,16,16);
    }

    @Override
    public void render(float delta) {


        renderer.setView(cam);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.render();

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
        //System.out.println(gun.getProjectiles().size());
        for (ProjectileBody projectile : gun.getProjectiles()) {
            projectileToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class),261,160,6,6);
            projectileToDraw.setSize(cam.viewportWidth/20,cam.viewportHeight/20);
            projectileToDraw.setX(projectile.getX());
            projectileToDraw.setY(projectile.getY());
            //System.out.println(projectile.getPosition().x + " " + projectile.getPosition().y);
            projectileToDraw.draw(batch);
        }
    }

    public void drawEnemies() {

        for (int i = 0 ; i < enemies.length ; i++) {
            if (enemies[i].getType() == "basicWalker") {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class),304,80,16,16);
                enemyToDraw.setSize(cam.viewportWidth/15,cam.viewportHeight/15);
                enemyToDraw.setX((int)enemies[i].getX());
                enemyToDraw.setY((int)enemies[i].getX());
                enemyToDraw.draw(batch);
            }
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

}