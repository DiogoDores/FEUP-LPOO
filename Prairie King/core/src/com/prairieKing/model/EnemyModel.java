package com.prairieKing.model;

public class EnemyModel extends EntityModel implements Behaviour {

    private char currentDirection = 'n';
    private String type = null;
    private float x, y;

    public EnemyModel(float x, float y) {
        super(x,y);
        this.x = x;
        this.y = y;
        setType("ENEMY");
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


}
