package com.prairieKing.model;

public interface Behaviour {
    public void move(EnemyModel e, HeroModel h); // Chases hero
    public void attack(EnemyModel e, HeroModel h); // In case a enemy requires this
    //public void confused(Enemy e); // Random Movements
}
