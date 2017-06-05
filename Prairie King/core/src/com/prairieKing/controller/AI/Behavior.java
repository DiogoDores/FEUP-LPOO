package com.prairieKing.controller.AI;

import com.prairieKing.controller.entities.EnemyModel;
import com.prairieKing.controller.entities.HeroModel;

public interface Behavior {

    /** Moves an Enemy.
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    void move(EnemyModel e, HeroModel h); // Chases hero

    /** Sets the initial movement in direction of the middle of the screen.
     *
     * @param direction Direction in which the enemy moves.
     */
    void initialBehaviour(EnemyModel e, char direction);

    /** Important for animation.
     */
    float getTimeToStop();
}
