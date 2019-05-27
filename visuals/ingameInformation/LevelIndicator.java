package visuals.ingameInformation;

import geometry.Point;
import visuals.sprites.Sprite;
import biuoop.DrawSurface;

/**
 * The class represents the sprite that show what level is displayed.
 */
public class LevelIndicator implements Sprite {
    private Point position;
    private String levelName;

    /**
     * A constructor function that sets the sprites position and the level's name.
     *
     * @param pos  the position of the sprite.
     * @param name the name of the level.
     */
    public LevelIndicator(Point pos, String name) {
        this.position = pos;
        this.levelName = name;
    }

    /**
     * The function draw the sprite according ot it's location and the name of the level.
     *
     * @param d a "DrawSurface" variable used to draw the sprite.
     */
    public void drawOn(DrawSurface d) {
        d.drawText((int) position.getX(), (int) position.getY(), "Level Name: " + this.levelName, 11);
    }

    /**
     * The function let's the sprite know that a unit of time has passed.
     *
     * @param dt a unit of time.
     */
    public void timePassed(double dt) {
    }
}
