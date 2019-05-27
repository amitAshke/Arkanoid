package visuals.sprites;

import biuoop.DrawSurface;

/**
 * The interface is used to make sure that every sprite in the game has the same functions.
 */
public interface Sprite {
    /**
     * The function tell the sprite to draw itself using the "DrawSurface" variable.
     *
     * @param d a "DrawSurface" variable used to draw the sprite.
     */
    void drawOn(DrawSurface d);

    /**
     * The function notify the sprite that a unit of time has passed.
     *
     * @param dt a unit of time.
     */
    void timePassed(double dt);
}
