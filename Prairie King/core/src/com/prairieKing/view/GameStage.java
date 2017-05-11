package com.prairieKing.view;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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
import com.prairieKing.model.Enemy;
import com.prairieKing.model.GameLogic;

public class GameStage extends ScreenAdapter {
    private Stage stage;
    private PrairieKing game;
    private Sprite background;
    private Sprite hero, enemyToDraw;
    private SpriteBatch batch;
    private FitViewport view;
    private GameLogic gameLogic;
    private Enemy[] enemies;
    private OrthographicCamera camera;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    public GameStage(GameLogic gameLogic) {
        this.game = gameLogic.getMyGame();
        batch = new SpriteBatch();
        stage = new Stage();
        enemies = gameLogic.getAI().getEnemies();
        camera = new OrthographicCamera();
        view = new FitViewport(Gdx.graphics.getWidth() / game.PPM, Gdx.graphics.getHeight() / game.PPM, camera);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Mapas/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / game.PPM);

        camera.setToOrtho(false, Gdx.graphics.getWidth() / game.PPM, Gdx.graphics.getHeight() / game.PPM);
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / game.PPM, (rect.getY() + rect.getHeight()/2) / game.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/ 2 / game.PPM, rect.getHeight()/ 2 / game.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / game.PPM, (rect.getY() + rect.getHeight()/2) / game.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2 / game.PPM, rect.getHeight()/2 / game.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        loadAssets();

        this.gameLogic = gameLogic;
    }


    public void loadAssets() {
        Texture mainSprite = game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class);
        hero = new Sprite(mainSprite,367,96,16,16);
    }

    @Override
    public void render(float delta) {

        camera.update();
        renderer.setView(camera);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.render();

        b2dr.render(world, camera.combined);

        batch.begin();
        hero.setSize(view.getWorldWidth()/28 / game.PPM,view.getWorldHeight()/28 / game.PPM);
        hero.setX((int) gameLogic.getHero().getX());
        hero.setY((int) gameLogic.getHero().getY());

        hero.draw(batch);
        drawEnemies();
        batch.end();

    }

    public void drawEnemies() {

        for (int i = 0 ; i < enemies.length ; i++) {
            if (enemies[i].getType() == "basicWalker") {
                enemyToDraw = new Sprite(game.getAssetManager().get("Sprites/MainSpriteSheet.png", Texture.class),304,80,16,16);
                enemyToDraw.setSize(view.getWorldWidth()/32 / game.PPM,view.getWorldHeight()/18 / game.PPM);
                enemyToDraw.setX((int)enemies[i].getX());
                enemyToDraw.setY((int)enemies[i].getY());
                enemyToDraw.draw(batch);
            }
        }
    }

    @Override
    public void resize(int width, int height){
        view.update(width,height, false);
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        stage.dispose();
    }

}