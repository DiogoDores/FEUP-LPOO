package com.prairieKing.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;
import com.prairieKing.Constants;
import com.prairieKing.PrairieKing;
import com.prairieKing.model.bodies.ProjectileBody;
import com.prairieKing.controller.entities.HeroController;
import com.prairieKing.controller.entities.ProjectileController;
import com.prairieKing.model.powerups.GunPowerups;

import java.util.ArrayList;
import java.util.List;

/**
 * Houses all the properties of the hero's gun, as well as
 * keeping track of the bullets.
 */
public class Gun {

    private ArrayList<ProjectileBody> projectilesBodies = new ArrayList<>();
    private ArrayList<ProjectileController> projectiles = new ArrayList<>();
    private ArrayList<GunPowerups> powerups = new ArrayList<>();
    private Pool<ProjectileController> pool;
    private World world;
    private float timeLeftToShoot;
    private float DELAY_TIME_SHOOT = 0.14f;

    private float SPEED;
    private String typeGun;
    private HeroController hero;
    private int shape;
    private int BULLET_SPEED = 4;

    private boolean leftB, rightB, upB, downB; // Gun Methods

    private Sound sound;

    /**
     * Constructor for a Gun, needs gameLogic to add bodies and
     * extract information about the hero's position.
     *
     * @param gameLogic Current instance of gameLogic.
     */
    public Gun(GameLogic gameLogic) {
        typeGun = "NORMAL";
        shape = 1;
        timeLeftToShoot = .15f;
        this.world = gameLogic.getWorld();
        this.hero = gameLogic.getHero();
        SPEED = 1;
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3"));
        pool = new Pool<ProjectileController>() {
            @Override
            protected ProjectileController newObject() {
                return new ProjectileController(-1, -1);
            }
        };
    }

    /**
     * Called every frame to update all its variables and methods.
     */
    public void update() {
        timeLeftToShoot -= SPEED * Gdx.graphics.getDeltaTime() / 2;
        checkPowerups();
        checkBullets();
        directionShoot();
    }

    /** Sets the direction of the projectile, and then shoots it, depending
     * on its shape.
     */
    private void directionShoot() {
        if (timeLeftToShoot <= 0) {
            int vX = 0, vY = 0;
            if (leftB)
                vX = vX - (int) PrairieKing.PPM * BULLET_SPEED;
            if (rightB)
                vX = vX + (int) PrairieKing.PPM * BULLET_SPEED;
            if (upB)
                vY = vY + (int) PrairieKing.PPM * BULLET_SPEED;
            if (downB)
                vY = vY - (int) PrairieKing.PPM * BULLET_SPEED;

            if (vX != 0 || vY != 0) {

                float posX = hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2 + vX / 140.0f;
                float posY = hero.getY() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2 + vY / 140.0f;

                if (shape == 3) {
                    shotgunShoot(posX, posY, vX, vY);

                } else if (shape == 9) {
                    wheelShoot(hero.getX(), hero.getY(), vX, vY);
                } else {
                    shoot(posX, posY, vX / 1.4f, vY / 1.4f);
                    timeLeftToShoot = DELAY_TIME_SHOOT;
                }
            }
        }
    }


    /** Checks whether projectile is flagged for deletion, if so
     *  remove it and destroy its associated body.
     */
    public void checkBullets() {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i) != null) {
                if (projectiles.get(i).isFlaggedForDelete()) {
                    for (int j = 0; j < projectilesBodies.size(); j++) {
                        if (projectilesBodies.get(j).getUserData() == projectiles.get(i)) {
                            projectilesBodies.get(j).destroy();
                            projectilesBodies.remove(j);
                        }
                    }
                    projectiles.remove(i);
                }
            }
        }
    }

    /** Checks whether powerup has worn off, and if so remove it
     *  from powerup list and remove its effect.
     */
    private void checkPowerups() {
        for (int i = 0; i < powerups.size(); i++) {
            powerups.get(i).update();
            if (powerups.get(i).getEffectTime() <= 0) {
                powerups.get(i).removeEffect();
                powerups.remove(i);
            }
        }
    }

    /** Add powerup to active powerup list.
     *
     * @param powerup Powerup to be added.
     */
    public void addPowerup(GunPowerups powerup) {
        powerups.add(powerup);
    }

    /** Fires projectile on coordinate, with set speed.
     *
     * @param posX X coordinate of spawn.
     * @param posY Y coordinate of spawn.
     * @param vX X coordinate of velocity.
     * @param vY Y coordinate of velocity.
     */
    public void shoot(float posX, float posY, float vX, float vY) {
        sound.play();

        ProjectileController p = pool.obtain();
        ProjectileBody pb = new ProjectileBody(world, p);

        pb.setTransform(posX, posY);
        pb.setLinearVelocity(vX, vY);

        projectiles.add(p);
        projectilesBodies.add(pb);
    }

    /** Shoots as a shotgun.
     *
     * @param posX X coordinate of spawn.
     * @param posY Y coordinate of spawn.
     * @param vX X coordinate of velocity.
     * @param vY Y coordinate of velocity.
     */
    private void shotgunShoot(float posX, float posY, float vX, float vY) {
        float vx1, vx2, vy1, vy2;

        float angle = MathUtils.atan2(vY, vX);
        angle *= MathUtils.radiansToDegrees;

        float difference = 20;

        vx1 = MathUtils.cosDeg(angle - difference) * (int) PrairieKing.PPM * BULLET_SPEED;
        vy1 = MathUtils.sinDeg(angle - difference) * (int) PrairieKing.PPM * BULLET_SPEED;
        vx2 = MathUtils.cosDeg(angle + difference) * (int) PrairieKing.PPM * BULLET_SPEED;
        vy2 = MathUtils.sinDeg(angle + difference) * (int) PrairieKing.PPM * BULLET_SPEED;

        shoot(posX, posY, vx1, vy1);
        shoot(posX, posY, vx2, vy2);
        if (vX != 0 && vY != 0)
            shoot(posX, posY, vX / 1.4f, vY / 1.4f);
        else
            shoot(posX, posY, vX, vY);
        timeLeftToShoot = DELAY_TIME_SHOOT;
    }

    /** Shoots in all directions.
     *
     * @param posX X coordinate of spawn.
     * @param posY Y coordinate of spawn.
     * @param vX X coordinate of velocity.
     * @param vY Y coordinate of velocity.
     */
    private void wheelShoot(float posX, float posY, float vX, float vY) {
        float speed;
        if (vX != 0)
            speed = vX / 1.5f;
        else
            speed = vY / 1.5f;

        speed = Math.abs(speed);

        float xLeft = posX - Constants.PROJECTILE_WIDTH / 2, xMiddle = hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2, xRight = posX + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;
        float yBottom = posY - Constants.PROJECTILE_WIDTH / 2, yMiddle = posY + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2,
                yTop = posY + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;

        shoot(xRight, yMiddle, speed, 0);
        shoot(xMiddle, yTop, 0, speed);
        shoot(xLeft, yMiddle, -speed, 0);
        shoot(xMiddle, yBottom, 0, -speed);
        shoot(xRight, yTop, speed / 1.4f, speed / 1.4f);
        shoot(xRight, yBottom, speed / 1.4f, -speed / 1.4f);
        shoot(xLeft, yTop, -speed / 1.4f, speed / 1.4f);
        shoot(xLeft, yBottom, -speed / 1.4f, -speed / 1.4f);

        timeLeftToShoot = DELAY_TIME_SHOOT;
    }

    /** Sets whether or not left key is pressed.
     *
     * @param leftB Left key pressed.
     */
    public void setLeftB(boolean leftB) {
        this.leftB = leftB;
    }

    /** Returns all active projectiles.
     *
     * @return projetciles.
     */
    public List<ProjectileController> getProjectiles() {
        return projectiles;
    }

    /** Sets whether or not right key is pressed.
     *
     * @param rightB Right key pressed.
     */
    public void setRightB(boolean rightB) {
        this.rightB = rightB;
    }

    /** Sets whether or not up key is pressed.
     *
     * @param upB Up key pressed.
     */
    public void setUpB(boolean upB) {
        this.upB = upB;
    }

    /** Sets whether or not down key is pressed.
     *
     * @param downB Down key pressed.
     */
    public void setDownB(boolean downB) {
        this.downB = downB;
    }

    /** Returns whether left key is pressed.
     *
     * @return leftB.
     */
    public boolean getLeftB() {
        return this.leftB;
    }

    /** Returns whether right key is pressed.
     *
     * @return rightB.
     */
    public boolean getRightB() {
        return this.rightB;
    }

    /** Returns whether up key is pressed.
     *
     * @return upB.
     */
    public boolean getUpB() {
        return this.upB;
    }

    /** Returns whether down key is pressed.
     *
     * @return downB.
     */
    public boolean getDownB() {
        return this.downB;
    }

    /** Sets fireRateSpeed.
     *
     * @param speed New Speed.
     */
    public void setSpeed(float speed) {
        SPEED = speed;
    }

    /** Sets fireRateSpeed.
     *
     * @return New Speed.
     */
    public float getSpeed() {
        return SPEED;
    }

    /** Sets the shape of the bullet spread.
     *
     * @param shape Spread shape.
     */
    public void setShape(int shape) {
        this.shape = shape;
    }

    /** Returns type gun.
     *
     * @return Type Gun.
     */
    public String getTypeGun() {
        return typeGun;
    }

    /** Sets type gun.
     *
     * @param typeGun Sets type gun.
     */
    public void setTypeGun(String typeGun) {
        this.typeGun = typeGun;
    }
}