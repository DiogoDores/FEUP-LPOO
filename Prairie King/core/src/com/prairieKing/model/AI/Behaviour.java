package com.prairieKing.model.AI;

import com.prairieKing.model.entities.EnemyModel;
import com.prairieKing.model.entities.HeroModel;

public interface Behaviour {
    void move(EnemyModel e, HeroModel h); // Chases hero
    void attack(EnemyModel e, HeroModel h); // In case a enemy requires this
    void initialBehaviour(char direction);

    //public void confused(Enemy e); // Random Movements
}
