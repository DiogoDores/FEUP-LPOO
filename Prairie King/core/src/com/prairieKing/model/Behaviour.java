package com.prairieKing.model;

public interface Behaviour {
    public void move(EnemyModel e, Hero h); // Chases hero
    public void attack(EnemyModel e, Hero h); // In case a enemy requires this
    //public void confused(Enemy e); // Random Movements
}
