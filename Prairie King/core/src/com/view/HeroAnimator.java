package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.PrairieKing;

/** Class responsible for all of the hero's animations.
 */
public class HeroAnimator extends Sprite{

    private GameStage gameStage;

    private enum State { STANDING, WALKING_UP, WALKING_DOWN, WALKING_RIGHT, WALKING_LEFT, DEATH }
    private State currentState, previousState;
    private Animation<TextureRegion> walkUp, walkDown, walkRight, walkLeft, death;
    private float stateTimer;
    private boolean loop;

    private Body body;
    private TextureRegion defPosition;

    /**
     * Constructor of the Hero Animator. It needs a GameStage instance for accessing the hero's body.
     *
     * @param gameStage GameStage instance.
     */
    public HeroAnimator(GameStage gameStage) {
        super(gameStage.getAtlas().findRegion("heroMovement"));
        this.body = gameStage.getGameLogic().getHeroBody().getBody();

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        this.loop = true;

        loadAnimations();

        defPosition = new TextureRegion(getTexture(), 1, 1, 16, 16);
        setBounds(0, 0, 16 / (PrairieKing.PPM /40), 16 / (PrairieKing.PPM /40));
        setRegion(defPosition);

        this.gameStage = gameStage;
    }

    /**
     * Loads every frame into an array list
     */
    public void loadAnimations(){
        Array<TextureRegion> frames = new Array<>();

        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkDown = new Animation<>(0.2f, frames);
        frames.clear();

        for (int i = 5; i < 9; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkRight = new Animation<>(0.2f, frames);
        frames.clear();

        for (int i = 9; i < 13; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkLeft = new Animation<>(0.2f, frames);
        frames.clear();

        for (int i = 13; i < 17; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkUp = new Animation<>(0.2f, frames);
        frames.clear();

        for (int i = 38; i < 42; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 8, 0, 16, 16));

        death = new Animation<>(0.25f, frames);
    }

    /** Updates the hero's body position and the sprite to use.
     */
    public void update(){
        setPosition(body.getPosition().x, body.getPosition().y);
        setRegion(getFrame());
    }

    /** Gets the correct sprite to use
     *
     * @return The sprite do use at a certain occasion
     */
    public TextureRegion getFrame(){

        currentState = getState();
        stateTimer += Gdx.graphics.getDeltaTime();

        TextureRegion region;
        switch (currentState){
            case WALKING_UP:
                region = walkUp.getKeyFrame(stateTimer, this.loop);
                break;
            case WALKING_DOWN:
                region = walkDown.getKeyFrame(stateTimer, this.loop);
                break;
            case WALKING_LEFT:
                region = walkLeft.getKeyFrame(stateTimer, this.loop);
                break;
            case WALKING_RIGHT:
                region = walkRight.getKeyFrame(stateTimer, this.loop);
                break;
            case DEATH:
                region = death.getKeyFrame(stateTimer);
                break;
            default:
                region = defPosition;
        }

        if(death.isAnimationFinished(stateTimer))
            gameStage.getGameLogic().getHero().setDeathAnimation(false);

        this.loop = true;

        if(this.currentState != this.previousState)
            stateTimer = 0;
        this.previousState = currentState;
        return region;
    }

    /** Gets the hero's current state
     *
     * @return The hero's current state
     */
    public State getState(){

        State tempState;

        if(gameStage.getGameLogic().getHero().isDeathAnimation()){
            return State.DEATH;
        }

        if(gameStage.getGameLogic().getHero().getDown())
            tempState = State.WALKING_DOWN;
        else if (gameStage.getGameLogic().getHero().getUp())
            tempState = State.WALKING_UP;
        else if (gameStage.getGameLogic().getHero().getLeft())
            tempState = State.WALKING_LEFT;
        else if (gameStage.getGameLogic().getHero().getRight())
            tempState = State.WALKING_RIGHT;
        else
            tempState = State.STANDING;

        if(tempState == State.STANDING){
            this.loop = false;
        } else
            this.gameStage.getGameLogic().getHero().playSound();

        if (gameStage.getGameLogic().getGun().getDownB()) {
            return State.WALKING_DOWN;
        } else if (gameStage.getGameLogic().getGun().getUpB()) {
            return State.WALKING_UP;
        } else if (gameStage.getGameLogic().getGun().getLeftB()) {
            return State.WALKING_LEFT;
        } else if (gameStage.getGameLogic().getGun().getRightB()) {
            return State.WALKING_RIGHT;
        } else
            return tempState;
    }
}