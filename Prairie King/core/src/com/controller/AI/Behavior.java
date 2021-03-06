package com.controller.AI;

import com.controller.entities.EnemyController;
import com.controller.entities.HeroController;

/** Interface for the Strategy Design Pattern.
 */
public interface Behavior {

    /** Moves an Enemy.
     *
     * @param e Enemy to move.
     * @param h Current Hero.
     */
    void move(EnemyController e, HeroController h); // Chases hero

    /** Sets the initial movement in direction of the middle of the screen.
     *
      * @param e Enemy to be moved.
     * @param direction Direction to the center of the screen.
     */

    void initialBehaviour(EnemyController e, char direction);

    /** Important for animation.
     *
     * @return Animation Time.
     */
    float getTimeToStop();
}
