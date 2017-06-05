package com.prairieKing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.PrairieKing;
import com.prairieKing.controller.bodies.EnemyBody;
import com.prairieKing.model.entities.EnemyModel;

import java.util.ArrayList;

/**
 * Created by Diogo on 01/06/2017.
 */

public class EnemyAnimator extends Sprite{

    private Animation<TextureRegion> basic, flying, tough, death;
    private float stateTimer;

    private ArrayList<EnemyBody> bodyList;
    private GameStage gameStage;

    public EnemyAnimator(GameStage gameStage) {
        this.bodyList = gameStage.getGameLogic().getAI().getEnemiesBodies();

        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 2; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyMovement"), i * 16, 0, 16, 16));

        basic = new Animation<TextureRegion>(0.9f, frames);
        frames.clear();

        for (int i = 2; i < 4; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyMovement"), i * 16, 0, 16, 16));

        flying = new Animation<TextureRegion>(0.9f, frames);
        frames.clear();

        for (int i = 4; i < 6; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyMovement"), i * 16, 0, 16, 16));

        tough = new Animation<TextureRegion>(2f, frames);
        frames.clear();

        for (int i = 0; i < 4; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyDeath"), i * 16, 0, 16, 16));

        death = new Animation<TextureRegion>(0.9f, frames);

        setBounds(1, 1, 16 / (PrairieKing.PPM /40), 16 / (PrairieKing.PPM /40));

        this.gameStage = gameStage;
    }

    public void update(int i, EnemyModel enemy){

        stateTimer += Gdx.graphics.getDeltaTime();

        setPosition(bodyList.get(i).getBody().getPosition().x - 0.5f, bodyList.get(i).getBody().getPosition().y);

        if(!enemy.isFlaggedForDelete()) {
            if (enemy.getEnemyType() == "BASIC")
                setRegion(basic.getKeyFrame(stateTimer, true));
            else if (enemy.getEnemyType() == "FLYING")
                setRegion(flying.getKeyFrame(stateTimer, true));
            else if (enemy.getEnemyType() == "TOUGH")
                if(enemy.getBehaviour().getTimeToStop() > 0)
                    setRegion(tough.getKeyFrame(stateTimer, true));
                else
                    setRegion(tough.getKeyFrame(stateTimer));
        } else {
            setRegion(death.getKeyFrame(stateTimer));
        }
    }
}
