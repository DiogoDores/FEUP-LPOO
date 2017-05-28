package com.prairieKing.model.AI;

import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.HeroModel;

public interface Behaviour {
    public void move(EnemyModel e, HeroModel h); // Chases hero
    public void attack(EnemyModel e, HeroModel h); // In case a enemy requires this
    //public void confused(Enemy e); // Random Movements
}
