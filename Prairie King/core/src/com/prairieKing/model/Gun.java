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


public class Gun {

    private ArrayList<ProjectileBody> projectilesBodies = new ArrayList<ProjectileBody>();
    private ArrayList<ProjectileController> projectiles = new ArrayList<ProjectileController>();
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


    public Gun(GameLogic gameLogic) {
        typeGun = "NORMAL";
        shape = 1;
        timeLeftToShoot = .15f;
        this.world = gameLogic.getWorld();
        this.hero = gameLogic.getHero();
        SPEED = 1;
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/pop.mp3"));
        pool = new Pool<ProjectileController>() {
            @Override
            protected ProjectileController newObject() {
                return new ProjectileController(-1, -1);
            }
        };
    }

    public void update() {
        timeLeftToShoot -= SPEED * Gdx.graphics.getDeltaTime() / 2;
        checkPowerups();
        directionShoot();
    }

    public void directionShoot() {
        if (timeLeftToShoot <= 0) {
            int x = 0, y = 0;
            if (leftB)
                x = x - (int) PrairieKing.PPM * BULLET_SPEED;
            if (rightB)
                x = x + (int) PrairieKing.PPM * BULLET_SPEED;
            if (upB)
                y = y + (int) PrairieKing.PPM * BULLET_SPEED;
            if (downB)
                y = y - (int) PrairieKing.PPM * BULLET_SPEED;

            float posX = 0, posY = 0, vX = x, vY = y;
            if ((x == 0 && y != 0) || (x != 0 && y == 0) || (x != 0 && y != 0)) {
                if (rightB) {
                    posX = hero.getX() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;
                    if (upB) {
                        posY = hero.getY() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;
                    } else if (downB) {
                        posY = hero.getY() - Constants.PROJECTILE_WIDTH / 2;
                    } else {
                        posY = hero.getY() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2;
                    }
                }
                if (leftB) {
                    posX = hero.getX() - Constants.PROJECTILE_WIDTH / 2;
                    if (upB) {
                        posY = hero.getY() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;
                    } else if (downB) {
                        posY = hero.getY() - Constants.PROJECTILE_WIDTH / 2;
                    } else {
                        posY = hero.getY() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2;
                    }
                } else if (upB && !rightB && !leftB) {
                    posX = hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2;
                    posY = hero.getY() + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;
                } else if (downB && !rightB && !leftB) {
                    posX = hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2;
                    posY = hero.getY() - Constants.PROJECTILE_WIDTH / 2;
                }


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

    public void shoot(float posX, float posY, float vX, float vY) {

        sound.play();

        ProjectileController p = pool.obtain();
        ProjectileBody pb = new ProjectileBody(world, p);

        pb.setTransform(posX, posY);
        pb.setLinearVelocity(vX, vY);

        projectiles.add(p);
        projectilesBodies.add(pb);


    }

    public List<ProjectileController> getProjectiles() {
        return projectiles;
    }

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

    public void checkPowerups() {
        for (int i = 0; i < powerups.size(); i++) {
            powerups.get(i).update();
            if (powerups.get(i).getEffectTime() <= 0) {
                powerups.get(i).removeEffect();
                powerups.remove(i);
            }
        }
    }

    public void addPowerup(GunPowerups powerup) {
        powerups.add(powerup);
    }

    public void shotgunShoot(float posX, float posY, float vX, float vY) {
        float vx1 = 0, vx2 = 0, vy1 = 0, vy2 = 0;
        if (vX > 0) {
            if (vY > 0) {
                vx1 = MathUtils.cosDeg(70) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(70) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(20) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(20) * (int) PrairieKing.PPM * BULLET_SPEED;
            } else if (vY < 0) {
                vx1 = MathUtils.cosDeg(-20) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(-20) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(-70) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(-70) * (int) PrairieKing.PPM * BULLET_SPEED;
            } else {
                vx1 = MathUtils.cosDeg(25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(-25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(-25) * (int) PrairieKing.PPM * BULLET_SPEED;
            }
        } else if (vX < 0) {
            if (vY > 0) {
                vx1 = MathUtils.cosDeg(180 - 70) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(180 - 70) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(180 - 20) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(180 - 20) * (int) PrairieKing.PPM * BULLET_SPEED;
            } else if (vY < 0) {
                vx1 = MathUtils.cosDeg(180 + 20) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(180 + 20) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(180 + 70) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(180 + 70) * (int) PrairieKing.PPM * BULLET_SPEED;
            } else {
                vx1 = MathUtils.cosDeg(180 - 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(180 - 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(180 + 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(180 + 25) * (int) PrairieKing.PPM * BULLET_SPEED;
            }
        } else {
            if (vY > 0) {
                vx1 = MathUtils.cosDeg(90 + 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(90 + 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(90 - 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(90 - 25) * (int) PrairieKing.PPM * BULLET_SPEED;
            } else if (vY < 0) {
                vx1 = MathUtils.cosDeg(270 - 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy1 = MathUtils.sinDeg(270 - 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vx2 = MathUtils.cosDeg(270 + 25) * (int) PrairieKing.PPM * BULLET_SPEED;
                vy2 = MathUtils.sinDeg(270 + 25) * (int) PrairieKing.PPM * BULLET_SPEED;
            }
        }

        shoot(posX, posY, vx1, vy1);
        shoot(posX, posY, vx2, vy2);
        if (vX != 0 && vY != 0)
            shoot(posX, posY, vX / 1.4f, vY / 1.4f);
        else
            shoot(posX, posY, vX, vY);
        timeLeftToShoot = DELAY_TIME_SHOOT;

    }

    public void wheelShoot(float posX, float posY, float vX, float vY) {
        float speed;
        if (vX != 0)
            speed = vX/1.5f;
        else
            speed = vY/1.5f;

        speed = Math.abs(speed);

        float xLeft = posX - Constants.PROJECTILE_WIDTH/2, xMiddle =  hero.getX() + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2, xRight = posX + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;
        float yBottom = posY - Constants.PROJECTILE_WIDTH / 2, yMiddle = posY + Constants.HERO_WIDTH / 2 - Constants.PROJECTILE_WIDTH / 2,
                yTop =  posY + Constants.HERO_WIDTH - Constants.PROJECTILE_WIDTH / 2;

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

    public boolean getLeftB() {
        return this.leftB;
    }

    public boolean getRightB() {
        return this.rightB;
    }

    public boolean getUpB() {
        return this.upB;
    }

    public boolean getDownB() {
        return this.downB;
    }

    public void setSpeed(float speed) {
        SPEED = speed;
    }

    public float getSPEED() {
        return SPEED;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public String getTypeGun() {
        return typeGun;
    }

    public void setTypeGun(String typeGun) {
        this.typeGun = typeGun;
    }
}