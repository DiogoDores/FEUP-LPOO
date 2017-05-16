package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.controller.EntityBody;
import com.prairieKing.controller.HeroBody;
import com.prairieKing.controller.InputController;
import com.prairieKing.controller.ListenerClass;
import com.prairieKing.controller.PrairieKing;
import com.prairieKing.controller.ProjectileBody;
import com.prairieKing.view.GameStage;

import java.util.Iterator;

import javax.annotation.processing.SupportedSourceVersion;

public class GameLogic {

    private InputController input;
    private AIManager AI;
    private PrairieKing myGame;
    private HeroModel hero;
    private HeroBody heroBody;
    private GameStage gameStage;
    private World world;
    private Gun gun;

    public GameLogic(PrairieKing game) {
        world = new World(new Vector2(0,0), true);
        gun = new Gun(world);
        gun.update();
        AI = new AIManager(this);
        AI.spawn();
        myGame = game;
        hero = new HeroModel(PrairieKing.PPM/2, PrairieKing.PPM/2);

        hero.setGun(gun);
        gameStage = new GameStage(this);
        input = new InputController(this);
        Gdx.input.setInputProcessor(input);
        world.setContactListener(new ListenerClass());
        heroBody = new HeroBody(world,hero);
    }

    public HeroModel getHero() {
        return hero;
    }

    public PrairieKing getMyGame() {
        return myGame;
    }

    public AIManager getAI() {
        return AI;
    }

    public void act() {
        world.step(1/300f,0,2);
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(Body body : bodies){
            EntityModel test = ((EntityModel) body.getUserData());//.setPosition(body.getPosition().x, body.getPosition().y);
            if(test.getType() == "ENEMY" || test.getType() == "HERO")
                body.setTransform(test.getX(),test.getY(), 0);
            else
                test.setPosition(body.getPosition().x, body.getPosition().y);
        }
        gameStage.render(0);
        moveEntities();
    }


    public void moveEntities() {
        hero.move();
        AI.move();
    }

    public World getWorld() {
        return world;
    }

    public void sweepDeadBodies() {

    }
}
