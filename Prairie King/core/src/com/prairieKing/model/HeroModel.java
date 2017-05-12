package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;

import java.awt.geom.Rectangle2D;

public class HeroModel extends EntityModel {
    private float  x, y;
    private int lives;
    private boolean left, right, up, down;
    private boolean leftB, rightB, upB, downB; // Gun Methods
    private Gun gun;

    public HeroModel(float x, float y) {
        super(x,y);
        this.x = x;
        this.y = y;
        left = false; right = false; up = false; down = false;
        this.lives = 3;

      //  setBody(world);
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void move() {
        gun.update();
        float x = this.x, y = this.y;
        if (left)
            x = (x-(5*Gdx.graphics.getDeltaTime())) ;
        if (right)
            x = (x+(5*Gdx.graphics.getDeltaTime())) ;
        if (up)
            y = (y+(5*Gdx.graphics.getDeltaTime())) ;
        if (down)
            y = (y-(5*Gdx.graphics.getDeltaTime())) ;

        setPosition(x,y);

        shoot();
    }

    public void shoot() {
        int x = 0, y = 0;
        if (leftB)
            x = x - 80*1000;
        if (rightB)
            x = x + 80*1000;
        if (upB)
            y = y + 80*1000;
        if(downB)
            y = y - 80*1000;

        System.out.println("X " + x + " Y "+ y);

        if ((x == 0 && y != 0) || (x!= 0 && y == 0) || (x != 0 && y != 0))
              gun.shoot(this.x, this.y, x,y);

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

    public void setLeftB(boolean leftB) {
        this.leftB = leftB;
    }

    public void setRightB(boolean rightB) {
        this.rightB = rightB;
    }

    public void setUpB(boolean upB) {
        this.upB = upB;
    }

    public void setDownB(boolean downB) {
        this.downB = downB;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public float getY() {
        return y;
    }

    public Gun getGun() { return gun; }

    public int getLives() {
        return lives;
    }

    public int die() {
        lives--;
        if (lives < 0)
            return -1; // Has Died
        return 0;  // Is still alive
    }
}
