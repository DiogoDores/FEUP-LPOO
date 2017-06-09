package com.prairieKing.model.bodies;

import com.badlogic.gdx.physics.box2d.World;
import com.prairieKing.Constants;
import com.prairieKing.controller.entities.EnemyController;
import com.prairieKing.controller.entities.EntityController;

/** Extrends EntityBody, creates the enemy's fixture and sets it's collision filters.
 */
public class EnemyBody extends EntityBody {

    private EntityController e;

    /**Creates a body for a Enemy, based on a Model, as well as it's fixture.
     *
     * @param world needed for EntityBody's constructor.
     * @param model Model with which the body is going to be associated with.
     */
    public EnemyBody(World world, EntityController model) {
        super(world, 0, model);
        e = model;
        createFixture((int) Constants.ENEMY_WIDTH ,(int) Constants.ENEMY_WIDTH, Constants.CATEGORY_ENEMY, Constants.MASK_ENEMY,(short) 0 );
    }

    public EntityController getEnemy() {
        return e;
    }
}
