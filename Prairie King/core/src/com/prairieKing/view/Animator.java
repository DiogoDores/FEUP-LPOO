package com.prairieKing.view;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Diogo on 31/05/2017.
 */

public abstract class Animator extends Sprite {

    public Animator(GameStage gameStage, int index) {
        super(gameStage.getAtlas().findRegion("movement", index));
    }

    public abstract void update(float delta);
}

