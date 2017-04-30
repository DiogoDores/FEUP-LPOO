package com.prairieKing.model;

public interface Strategy {
    public void move(Enemy e, Hero h); // Chases hero
    public void attack(Enemy e, Hero h); // In case a enemy requires this
    public void confused(Enemy e); // Random Movements
}
