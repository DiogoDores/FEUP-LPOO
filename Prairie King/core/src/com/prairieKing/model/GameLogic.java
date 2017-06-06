package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.controller.CollisionHandler;
import com.prairieKing.controller.InputHandler;
import com.prairieKing.model.powerups.PowerupSpawner;
import com.prairieKing.model.bodies.HeroBody;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.AI.AIManager;
import com.prairieKing.controller.entities.HeroWin;
import com.prairieKing.controller.entities.EntityController;
import com.prairieKing.controller.entities.HeroController;
import com.prairieKing.view.GameStage;

/** The main game class. Here the main methods of almost all other functions are called.
 */
public class GameLogic {
    private boolean isForTest;

    private InputHandler input;
    private PowerupSpawner powerupSpawner;
    private AIManager AI;
    private PrairieKing prairieKing;
    private HeroController hero;
    private HeroBody heroBody;
    private GameStage gameStage;
    private World world;
    private Gun gun;

    private float highScore;
    private float timeSinceBeginning;

    /** /** Creates a new Game, and stores the Prairie King instance.

     * @param game Prairie King instance.
     * @param forTest Used for separate View from testing.
     */
    public GameLogic(PrairieKing game, boolean forTest) {
        isForTest = forTest;
        world = new World(new Vector2(0, 0), true);
        AI = new AIManager(this);
        prairieKing = game;
        hero = new HeroController(PrairieKing.PPM / 2, PrairieKing.PPM / 2);

        highScore = 0;
        timeSinceBeginning = 0;

        gun = new Gun(this);

        input = new InputHandler(this);
        Gdx.input.setInputProcessor(input);
        world.setContactListener(new CollisionHandler());
        heroBody = new HeroBody(world, hero);

        if(!forTest) {
            gameStage = new GameStage(this);
            createMapBodies();
        }

        powerupSpawner = new PowerupSpawner(this);


    }

    /** When the player is either killed, or wins and wants to start a new game, this
     *  resets everything to its default values. This way, only one instance of gameLogic is
     *  necessary.
     */
    public void resetEverything() {
        world = new World(new Vector2(0, 0), true);
        AI = new AIManager(this);
        hero = new HeroController(PrairieKing.PPM / 2, PrairieKing.PPM / 2);

        highScore = 0;
        timeSinceBeginning = 0;

        gun = new Gun(this);

        input = new InputHandler(this);
        Gdx.input.setInputProcessor(input);
        world.setContactListener(new CollisionHandler());
        heroBody = new HeroBody(world, hero);
        if (!isForTest) {
            createMapBodies();
            gameStage = new GameStage(this);
        }

        powerupSpawner = new PowerupSpawner(this);
    }

    /** Creates all the bodies regarding the Map Walls.
     */
    private void createMapBodies() {
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body bod;

        for (MapObject object : prairieKing.getMap().getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / PrairieKing.PPM * 22.34f, (rect.getY() + rect.getHeight() / 2) / PrairieKing.PPM * 22.34f);

            bod = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2 / PrairieKing.PPM * 22.34f, rect.getHeight() / 2 / PrairieKing.PPM * 22.34f);
            fDef.shape = shape;
            bod.createFixture(fDef);
        }

    }

    /** Updates every game entity, frame by frame.
     */
    public void act() {
        gun.update();
        powerupSpawner.update();

        timeSinceBeginning += Gdx.graphics.getDeltaTime();
        highScore = AI.getKillCount() * 100 + timeSinceBeginning * 9;

        AI.checkEnemies();
        powerupSpawner.checkPowerups();


        moveEntities();

        stepBodiesAndPhysics();
    }

    /** Steps all bodies, and updates every position (for bodies).
     */
    private void stepBodiesAndPhysics() {
        world.step(1 / 300f, 0, 2);

        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);

        for (Body body : bodies) {

            EntityController model = ((EntityController) body.getUserData());

            if (model != null) {
                checkLose(model);
                if (model.getType().equals("ENEMY") || model.getType().equals("HERO"))
                    body.setTransform(model.getX(), model.getY(), 0);
                else
                    model.setPosition(body.getPosition().x, body.getPosition().y);
            }
        }


    }

    /** Moves all known entities (only for controllers).
     */
    private void moveEntities() {
        hero.move();
        AI.spawn();
        AI.move();
    }

    /** Checks whether the hero has been killed. If so, lose.
     * @param model Possibly HeroModel.
     */
    public void checkLose(EntityController model) {
        if (model.isFlaggedForDelete()) {
            if (model.getType().equals("HERO")) {
                PrairieKing.currentState = 2;
                prairieKing.setHighScore((int) highScore);
                resetEverything();
            }
        }

    }

    /** Is called when the player wins.
     */
    public void win() {
        prairieKing.setHighScore((int) highScore);
        this.hero = new HeroWin(hero.getX(), hero.getY());
        this.heroBody = new HeroBody(world, hero);
        gameStage.hasWon();
    }

    /** Necessary to add new Bodies to the world.
     *
     * @return world.
     */
    public World getWorld() {
        return world;
    }

    /** Returns the active GameStage, important for render in Prairie King.
     *
     * @return GameStage.
     */
    public GameStage getGameStage() {
        return gameStage;
    }

    /** Returns PowerupSpawner, important for GameStage to know if and where are
     * powerups located.
     *
     * @return powerupSpawner.
     */
    public PowerupSpawner getPowerupSpawner() {
        return powerupSpawner;
    }

    /** Returns active Gun, important to GameStage because the Gun class stores all
     * the location of the active projectiles.
     *
     * @return Gun.
     */
    public Gun getGun() {
        return gun;
    }

    /** Returns the HeroModel, so the both GameStage and every Enemy knows exactly
     *  where the hero is at the moment their methods are invoked.
     *
     * @return Active heroModel.
     */
    public HeroController getHero() {
        return hero;
    }

    /** Important for animation.
     *
     * @return heroBody.
     */
    public HeroBody getHeroBody() {
        return heroBody;
    }

    /** Returns the Prairie King instance.
     *
     * @return Prairie King Instance.
     */
    public PrairieKing getPrairieKing() {
        return prairieKing;
    }

    /** Returns the AI to have acess to all the enemies.
     *
     * @return AI
     */
    public AIManager getAI() {
        return AI;
    }



}
