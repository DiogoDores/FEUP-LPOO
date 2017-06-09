package com.prairieKing.controller.entities;

/** The Default Entity for all controllers. All controllers extend
 * from this class, and all the bodies have some kind of extended
 * EntityController as their Data.
 */
public class EntityController {

    private boolean flaggedForDelete;

    private float x, y;
    private String type;

    /** Constructor for a default Entity Controller.
     *
     * @param x X coordinate to Spawn.
     * @param y Y coordinate to Spawn.
     */
    public EntityController(float x, float y) {
        this.x = x;
        this.y = y;
        flaggedForDelete = false;
    }

    /** Get current X coordinate.
     *
     * @return X coordinate.
     */
    public float getX() {
        return x;
    }

    /** Get current Y coordinate.
     *
     * @return Y coordinate.
     */
    public float getY() {
        return y;
    }

    /** Sets the current position whenever it needs to be updated.
     *
     * @param x New X coordinate.
     * @param y New Y coordinate.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /** Sets EntityModel Type, be it "ENEMY", "HERO", or other.
     *
     * @param type Type of the Model.
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Gets EntityModel Type, be it "ENEMY", "HERO", or other.
     *
     * @return Type of the Model.
     */
    public String getType() {
        return type;
    }

    /** When a model is destroyed, this sets it as flagged for
     * deletion, and in the next render, both the models and it's
     * respective bodies will be deleted in the appropriate place.
     */
    public void kill() {
        flaggedForDelete = true;
    }

    /** Set flaggedForDeleteTrue.
     */
    public void resurrect() {
        flaggedForDelete = false;
    }

    /** Returns whether this model and its associated body is flagged
     * for deletion.
     * @return flaggedForDelete
     */
    public boolean isFlaggedForDelete() {
        return flaggedForDelete;
    }

    /** Necessary for Powerups.
     */
    public void activate() { }
}
