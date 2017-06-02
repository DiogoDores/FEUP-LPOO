package com.prairieKing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.bodies.EnemyBody;

import java.util.ArrayList;

/**
 * Created by Diogo on 01/06/2017.
 */

public class EnemyAnimator extends Animator{
    private GameStage gameStage;

    public enum State { STANDING, WALKING_UP, WALKING_DOWN, WALKING_RIGHT, WALKING_LEFT }
    public State currentState;
    private Animation<TextureRegion> walk;
    private float stateTimer;

    private ArrayList<EnemyBody> bodyList;
    private TextureRegion defPosition;

    public EnemyAnimator(GameStage gameStage) {
        super(gameStage, 1);
        this.bodyList = gameStage.getGameLogic().getAI().getEnemiesBodies();

        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 2; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("movement", 2), i * 16 + 1, 0, 16, 16));

        walk = new Animation<TextureRegion>(0.4f, frames);
        frames.clear();

        setBounds(0, 0, 16 / (PrairieKing.PPM /40), 16 / (PrairieKing.PPM /40));

        this.gameStage = gameStage;
    }

    public void update(){

        stateTimer += Gdx.graphics.getDeltaTime();

        //setPosition(bodyList.get(i).getBody().getPosition().x, bodyList.get(i).getBody().getPosition().y);
        setRegion(walk.getKeyFrame(stateTimer, true));
    }
}
