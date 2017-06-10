package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.PrairieKing;
import com.controller.entities.EnemyController;

import java.util.ArrayList;

/** Class responsible for all of the enemy's animations.
 */
public class EntityAnimator extends Sprite{

    private Animation<TextureRegion> basic, flying, tough, death;
    private Animation<TextureRegion> wife;
    private float stateTimer;
    private GameStage gameStage;

    private ArrayList<EnemyController> enemyControllers;

    /**
     * Constructor of the Enemy Animator. It needs a GameStage instance for accessing the enemy's body.
     *
     * @param gameStage GameStage instance.
     */
    public EntityAnimator(GameStage gameStage) {
        this.gameStage = gameStage;
        this.enemyControllers = gameStage.getGameLogic().getAI().getEnemies();

        stateTimer = 0;

        loadAnimations();

        setBounds(1, 1, 16 / (PrairieKing.PPM /40), 16 / (PrairieKing.PPM /40));
    }

    /**
     * Loads every frame into an array list
     */
    private void loadAnimations() {

        Array<TextureRegion> frames = new Array<>();

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

        for (int i = 0; i < 6; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("enemyDeath"), i * 16, 0, 16, 16));

        death = new Animation<>(0.2f, frames);
        frames.clear();

        for (int i = 0; i < 5; i++)
            frames.add(new TextureRegion(gameStage.getAtlas().findRegion("wife"), i * 16, 0, 16, 16));

        wife = new Animation<>(0.7f, frames);
    }

    /** Updates the enemy's body position and the sprite to use.
     */
    public void update(int i, EnemyController enemy){

        setPosition(enemyControllers.get(i).getX() - 0.5f, enemyControllers.get(i).getY());

        stateTimer += Gdx.graphics.getDeltaTime();

        if(!enemy.isFlaggedForDelete() ) {
            if (enemy.getEnemyType().equals("BASIC"))
                setRegion(basic.getKeyFrame(stateTimer, true));
            else if (enemy.getEnemyType().equals("FLYING"))
                setRegion(flying.getKeyFrame(stateTimer, true));
            else if (enemy.getEnemyType().equals("TOUGH")) {
                if (enemy.getBehaviour().getTimeToStop() > 0)
                    setRegion(tough.getKeyFrame(stateTimer, true));
                else
                    setRegion(tough.getKeyFrame(stateTimer));
            }
        } else {
            setRegion(death.getKeyFrame(stateTimer));
        }
    }

    public void updateWife(boolean isKissing){
        stateTimer += Gdx.graphics.getDeltaTime();
        if(isKissing)
            setRegion(wife.getKeyFrame(stateTimer));
        else
            setRegion(wife.getKeyFrame(stateTimer, true));
        setX(PrairieKing.PPM / 2);
        setY(PrairieKing.PPM / 2 - 10);
    }
}
