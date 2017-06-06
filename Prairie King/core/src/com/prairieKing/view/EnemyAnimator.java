package com.prairieKing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.prairieKing.PrairieKing;
import com.prairieKing.model.bodies.EnemyBody;
import com.prairieKing.controller.entities.EnemyController;

import java.util.ArrayList;

public class EnemyAnimator extends Sprite{

    private Animation<TextureRegion> basic, flying, tough, death;
    private float stateTimer;

    private ArrayList<EnemyBody> bodyList;

    public EnemyAnimator(GameStage gameStage) {
        this.bodyList = gameStage.getGameLogic().getAI().getEnemiesBodies();

        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 2; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyMovement"), i * 16, 0, 16, 16));

        basic = new Animation<>(0.9f, frames);
        frames.clear();

        for (int i = 2; i < 4; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyMovement"), i * 16, 0, 16, 16));

        flying = new Animation<>(0.9f, frames);
        frames.clear();

        for (int i = 4; i < 6; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyMovement"), i * 16, 0, 16, 16));

        tough = new Animation<>(2f, frames);
        frames.clear();

        for (int i = 0; i < 4; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyDeath"), i * 16, 0, 16, 16));

        death = new Animation<>(0.9f, frames);

        setBounds(1, 1, 16 / (PrairieKing.PPM /40), 16 / (PrairieKing.PPM /40));
    }

    public void update(int i, EnemyController enemy){

        stateTimer += Gdx.graphics.getDeltaTime();

        setPosition(bodyList.get(i).getBody().getPosition().x - 0.5f, bodyList.get(i).getBody().getPosition().y);

        if(!enemy.isFlaggedForDelete()) {
            if (enemy.getEnemyType().equals("BASIC"))
                setRegion(basic.getKeyFrame(stateTimer, true));
            else if (enemy.getEnemyType().equals("FLYING"))
                setRegion(flying.getKeyFrame(stateTimer, true));
            else if (enemy.getEnemyType().equals("TOUGH"))
                if(enemy.getBehaviour().getTimeToStop() > 0)
                    setRegion(tough.getKeyFrame(stateTimer, true));
                else
                    setRegion(tough.getKeyFrame(stateTimer));
        } else {
            setRegion(death.getKeyFrame(stateTimer));
        }
    }
}
