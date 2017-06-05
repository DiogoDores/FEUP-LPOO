package com.prairieKing.controller.AI;

import com.prairieKing.controller.entities.EnemyController;
import com.prairieKing.controller.entities.HeroController;

public interface Behavior {

    /** Moves an Enemy.
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    void move(EnemyController e, HeroController h); // Chases hero

    /** Sets the initial movement in direction of the middle of the screen.
     *
     * @param direction Direction in which the enemy moves.
     */
    void initialBehaviour(EnemyController e, char direction);

    /** Important for animation.
     */
    float getTimeToStop();
}
