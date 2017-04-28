package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage implements Screen {
    private OrthographicCamera camera;
    private Stage stage;
    private String name;
    private PrairieKing game;
    Sprite background;
    Sprite hero;
    SpriteBatch batch;
    private Viewport view;
    GameLogic gameLogic;
    
    private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer orthRender;

    public GameStage(GameLogic gameLogic) {
    	
        this.game = gameLogic.getMyGame();
        batch = new SpriteBatch();
        stage = new Stage();
        
        camera = new OrthographicCamera();

        view = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        view.apply();
        
        //game.getAssetManager().load("Mapas/MapaTeste1.png", Texture.class);
        game.getAssetManager().load("Sprites/TestHero.png", Texture.class);
        //background = new Sprite(new Texture("Mapas/MapaTeste1.png"));
        hero = new Sprite(new Texture("Sprites/TestHero.png"));
        
        mapLoader = new TmxMapLoader();
		map = mapLoader.load("Mapas/LevelOne.tmx");
		orthRender = new OrthogonalTiledMapRenderer(map);
		camera.position.set(0, 0, 0);

        this.gameLogic = gameLogic;

    }
    
    public void update1(float delta){
    	camera.update();
    	orthRender.setView(camera);
    }


    @Override
    public void render(float delta) {
    	update1(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        
        orthRender.render();

        batch.begin();
        
        //batch.setProjectionMatrix(camera.combined);
        //background.setSize(view.getWorldWidth(),view.getWorldHeight());
        hero.setSize(view.getWorldWidth()/32,view.getWorldHeight()/18);
        hero.setX(gameLogic.getHero().getX());
        hero.setY(gameLogic.getHero().getY());

        //background.draw(batch);
        hero.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        view.update(width,height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}