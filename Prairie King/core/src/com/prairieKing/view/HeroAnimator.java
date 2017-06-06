package com.prairieKing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.PrairieKing;

public class HeroAnimator extends Sprite{

    private GameStage gameStage;

    public enum State { STANDING, WALKING_UP, WALKING_DOWN, WALKING_RIGHT, WALKING_LEFT }
    public State currentState;
    private Animation<TextureRegion> walkUp, walkDown, walkRight, walkLeft;
    private float stateTimer;
    private boolean loop;

    private Body body;
    private TextureRegion defPosition;

    public HeroAnimator(GameStage gameStage) {
        super(gameStage.getAtlas().findRegion("heroMovement"));
        this.body = gameStage.getGameLogic().getHeroBody().getBody();

        currentState = State.STANDING;
        stateTimer = 0;
        this.loop = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkDown = new Animation<TextureRegion>(0.2f, frames);
        frames.clear();

        for (int i = 5; i < 9; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkRight = new Animation<TextureRegion>(0.2f, frames);
        frames.clear();

        for (int i = 9; i < 13; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkLeft = new Animation<TextureRegion>(0.2f, frames);
        frames.clear();

        for (int i = 13; i < 17; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 1, 1, 16, 16));

        walkUp = new Animation<TextureRegion>(0.2f, frames);

        defPosition = new TextureRegion(getTexture(), 1, 1, 16, 16);
        setBounds(0, 0, 16 / (PrairieKing.PPM /40), 16 / (PrairieKing.PPM /40));
        setRegion(defPosition);

        this.gameStage = gameStage;
    }

    public void update(){
        setPosition(body.getPosition().x, body.getPosition().y);
        setRegion(getFrame());
    }

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
            default:
                region = defPosition;
        }

        this.loop = true;
        return region;
    }

    public State getState(){

        State tempState;

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
