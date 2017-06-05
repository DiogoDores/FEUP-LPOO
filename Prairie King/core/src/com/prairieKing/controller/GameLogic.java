package com.prairieKing.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.model.bodies.HeroBody;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.AI.AIManager;
import com.prairieKing.controller.entities.HeroWin;
import com.prairieKing.controller.entities.EntityController;
import com.prairieKing.controller.entities.HeroController;
import com.prairieKing.view.GameStage;

public class GameLogic {

    private InputHandler input;
    private PowerupSpawner powerupSpawner;
    private AIManager AI;
    private PrairieKing prairieKing;
    private HeroController hero;
    private HeroBody heroBody;
    private GameStage gameStage;
    private World world;
    private Gun gun;

    private TmxMapLoader mapLoader;
    private TiledMap map;


    private float highScore;
    private float timeSinceBeginning;

    public GameLogic(PrairieKing game) {
        world = new World(new Vector2(0, 0), true);
        AI = new AIManager(this);
        prairieKing = game;
        hero = new HeroController(PrairieKing.PPM / 2, PrairieKing.PPM / 2);

        highScore = 0;
        timeSinceBeginning = 0;

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Mapas/Map.tmx");

        gun = new Gun(this);

        input = new InputHandler(this);
        Gdx.input.setInputProcessor(input);
        world.setContactListener(new CollisionHandler());
        heroBody = new HeroBody(world, hero);

        gameStage = new GameStage(this);

        powerupSpawner = new PowerupSpawner(this);

        createBodies();
    }

    public void resetEverything() {
        world = new World(new Vector2(0, 0), true);
        AI = new AIManager(this);
        hero = new HeroController(PrairieKing.PPM / 2, PrairieKing.PPM / 2);
        gun = new Gun(this);

        timeSinceBeginning = 0;
        highScore = 0;

        powerupSpawner = new PowerupSpawner(this);

        gameStage = new GameStage(this);
        input = new InputHandler(this);
        Gdx.input.setInputProcessor(input);
        world.setContactListener(new CollisionHandler());
        heroBody = new HeroBody(world, hero);

        gameStage = new GameStage(this);

        createBodies();
    }

    public void createBodies() {
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body bod;

        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / PrairieKing.PPM * 22.34f, (rect.getY() + rect.getHeight() / 2) / PrairieKing.PPM * 22.34f);

            bod = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2 / PrairieKing.PPM * 22.34f, rect.getHeight() / 2 / PrairieKing.PPM * 22.34f);
            fDef.shape = shape;
            bod.createFixture(fDef);
        }

    }

    public HeroController getHero() {
        return hero;
    }

    public HeroBody getHeroBody() {
        return heroBody;
    }

    public PrairieKing getPrairieKing() {
        return prairieKing;
    }

    public AIManager getAI() {
        return AI;
    }

    public void act() {
        world.step(1 / 300f, 0, 2);

        gun.update();

        timeSinceBeginning += Gdx.graphics.getDeltaTime();
        highScore = AI.getKillCount() * 20 + timeSinceBeginning * 7;

        powerupSpawner.update();

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {

            EntityController model = ((EntityController) body.getUserData());

            if (model != null && body != null)
                checkLose(model);

            if (model != null) {
                if (model.getType() == "ENEMY" || model.getType() == "HERO")
                    body.setTransform(model.getX(), model.getY(), 0);
                else
                    model.setPosition(body.getPosition().x, body.getPosition().y);
            }
        }

        AI.checkEnemies();
        powerupSpawner.checkPowerups();
        gun.checkBullets();
        gameStage.render(0);

        moveEntities();
    }


    public void moveEntities() {
        hero.move();
        AI.spawn();
        AI.move();
    }

    public void checkLose(EntityController model) {
        if (model.isFlaggedForDelete()) {
            if (model.getType() == "HERO") {
                PrairieKing.currentState = 2;
                prairieKing.setHighScore((int) highScore);
                resetEverything();
                return;
            }
        }

    }

    public void win() {
        prairieKing.setHighScore((int) highScore);
        this.hero = new HeroWin(hero.getX(), hero.getY());
        this.heroBody = new HeroBody(world, hero);
        gameStage.hasWon();
    }

    public World getWorld() {
        return world;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public TiledMap getMap() {
        return map;
    }

    public PowerupSpawner getPowerupSpawner() {
        return powerupSpawner;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public Gun getGun() {
        return gun;
    }



}
