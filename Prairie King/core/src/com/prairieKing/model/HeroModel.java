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

    private PolygonShape shape;
    private FixtureDef fixtureDef;
    private BodyDef bodyDef;
    private Body body;

    public HeroModel(float x, float y) {
        super(x,y);
        this.x = x;
        this.y = y;
        left = false; right = false; up = false; down = false;
        this.lives = 3;
        gun = new Gun();
      //  setBody(world);
    }

    private void setBody(World world) {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.x,this.y);
        shape = new PolygonShape();
        shape.setAsBox(10,10);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void move() {
        body.applyForce(100.0f, 0.0f, this.x, this.y, true);
        float x = this.x, y = this.y;
        if (left)
            x = (x-(100*Gdx.graphics.getDeltaTime())) ;
        if (right)
            x = (x+(100*Gdx.graphics.getDeltaTime())) ;
        if (up)
            y = (y+(100*Gdx.graphics.getDeltaTime())) ;
        if (down)
            y = (y-(100*Gdx.graphics.getDeltaTime())) ;

        setPosition(x,y);

        shoot();
    }

    public void shoot() {
        int x = 0, y = 0;
        if(leftB)
            x = x - 100;
        if(rightB)
            x = x + 100;
        if (upB)
            y = y + 100;
        if (downB)
            y = y-100;

        if ((x == 0 && y != 0) || (x!= 0 && y == 0) || (x != 0 && y != 0)) {
            Vector2 bulletDirection = new Vector2(x,y);
            Vector2 currPos = new Vector2(this.x, this.y);
            gun.shoot(currPos, bulletDirection);
        }
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
