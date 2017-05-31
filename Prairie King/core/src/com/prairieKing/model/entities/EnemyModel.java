package com.prairieKing.model.entities;

import com.prairieKing.model.AI.Behaviour;

public class EnemyModel extends EntityModel implements Behaviour {

    private String enemyType;
    private int health;

    private char currentDirection = 'n';
    private String type = null;
    private float x, y;

    public EnemyModel(float x, float y, char initialDirection) {
        super(x,y);
        health = 1;
        this.x = x;
        this.y = y;
        super.setType("ENEMY");
    }


    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void move(EnemyModel e, HeroModel h) {

    }

    @Override
    public void attack(EnemyModel e, HeroModel h) {

    }

    @Override
    public void kill() {
        --health;
        if (health == 0)
            super.kill();
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setPosition(float x, float y) {
        //System.out.println("Antes tinha os valores de x e y "+ this.x + " , " +this.y);
        //System.out.println("Sou modificado com os valores de x e y "+ x + " , " +y);
        this.x = x;
        this.y = y;
        //System.out.println("Valores de agorade x e y "+ this.x + " , " +this.y);
    }

    public char getCurrentDirection() {
        return currentDirection;
    }


    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public void initialBehaviour(char direction) {

    }
}
