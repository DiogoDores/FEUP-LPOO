package com.prairieKing.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.model.EntityModel;

import javax.print.attribute.standard.PageRanges;

public class HeroBody extends EntityBody{

    private float x, y;
    private boolean left, right, up, down;


   public HeroBody(World world, EntityModel model) {
        super(world, model);
        x = PrairieKing.PPM/2; y = PrairieKing.PPM/2;
        createFixture(body, (int) PrairieKing.PPM/15, (int) PrairieKing.PPM/15);
    }

    public void move() {
        if (left)
            x = x - (int) PrairieKing.PPM*100;
        if (right)
            x = x + (int) PrairieKing.PPM*100;
        if (up)
            y = y + (int) PrairieKing.PPM*100;
        if(down)
            y = y - (int) PrairieKing.PPM*100;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

}
