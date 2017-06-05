package com.prairieKing.controller.entities;

public class ProjectileController extends EntityController {

    float x, y;

    /** Constructor for a new projectile, spawning on position (X,Y).
     *
     * @param x X coordinate of the spawn location.
     * @param y Y coordinate of the spawn location.
     */
    public ProjectileController(float x, float y) {
        super(x, y);
        this.x = x; this.y = y;
        super.setType("PROJECTILE");
    }

}
